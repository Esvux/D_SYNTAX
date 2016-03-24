// <editor-fold defaultstate="collapsed" desc="Licencia de software">
/*
 * Copyright (C) 2016 Esvin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; witherr even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// </editor-fold>                        
package com.esvux.AST.AnalizadorLR;

import com.esvux.AST.Gramatica.Elemento;
import com.esvux.AST.Gramatica.Gramatica;
import com.esvux.AST.Gramatica.Produccion;
import com.esvux.InterfaceGrafica.files.manejoArchivos;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class TablaAnalisisLR {

    private static Gramatica GRAMATICA;
    private static TreeSet<EstadoLR> ESTADOS_LR;
    private static ArrayList<TransicionLR> TRANSICIONES_LR;
    private AccionLR acciones[][];
    private int cantEstados, cantSimbolos;
    private StringBuilder informeConflictos;

    public TablaAnalisisLR(Gramatica gramatica, TreeSet<EstadoLR> estadosLR, ArrayList<TransicionLR> transicionesLR) {
        GRAMATICA = gramatica;
        ESTADOS_LR = estadosLR;
        TRANSICIONES_LR = transicionesLR;
        this.cantSimbolos = gramatica.getElementosLR().size() - 1;
        this.cantEstados = estadosLR.size();
        this.acciones = new AccionLR[cantSimbolos][cantEstados];
        this.informeConflictos = new StringBuilder();
        materializarTabla();
    }

    private void materializarTabla() {
        //Primero, todas las celdas son error...
        for (int i = 0; i < cantSimbolos; i++) {
            for (int j = 0; j < cantEstados; j++) {
                this.acciones[i][j] = AccionLR.ERROR;
            }
        }
        materializarReducciones();
        materializarDesplazamientos();
    }

    private void materializarDesplazamientos() {
        Iterator<TransicionLR> itTrans = this.TRANSICIONES_LR.iterator();
        while (itTrans.hasNext()) {
            TransicionLR trans = itTrans.next();
            AccionLR accion = this.acciones[trans.getTransicion()][trans.getOrigen()];
            if (accion.noEsError()) {
                informeConflictos.append("Conflicto en S");
                informeConflictos.append(trans.getOrigen());
                informeConflictos.append(" entre: ");
                informeConflictos.append(accion.getAccion());
                informeConflictos.append("-DESPLAZAR.\n");
                if (accion.getAccion() == AccionLR.TIPO_ACCION.REDUCIR) {
                    Produccion produccion = GRAMATICA.getProduccion(accion.getValor());
                    Elemento desplazamiento = GRAMATICA.getElemento(trans.getTransicion());
                    informeConflictos.append("\t  Reducir: ");
                    informeConflictos.append(produccion);
                    informeConflictos.append('\n');
                    informeConflictos.append("\tDesplazar: ");
                    informeConflictos.append(desplazamiento.getPresentacion());
                    informeConflictos.append('\n');
                    if (solucionarConflicto(produccion, desplazamiento) == AccionLR.TIPO_ACCION.DESPLAZAR) {
                        this.acciones[trans.getTransicion()][trans.getOrigen()] = AccionLR.desplazamiento(trans.getDestino());
                    }
                }
            }
            this.acciones[trans.getTransicion()][trans.getOrigen()] = AccionLR.desplazamiento(trans.getDestino());
        }
    }

    private void materializarReducciones() {
        Iterator<EstadoLR> itEstados = this.ESTADOS_LR.iterator();
        while (itEstados.hasNext()) {
            EstadoLR estado = itEstados.next();
            Iterator<ItemLR> itItems = estado.iterator();
            while (itItems.hasNext()) {
                ItemLR item = itItems.next();
                if (item.getPuntero() == item.getDerivacion().size()) {
                    Iterator<Integer> itAntic = item.getAnticipacion().iterator();
                    while (itAntic.hasNext()) {
                        Integer antic = itAntic.next();
                        AccionLR accion = this.acciones[antic][estado.getId()];
                        if (accion.noEsError()) {
                            informeConflictos.append("Conflicto en S");
                            informeConflictos.append(estado.getId());
                            informeConflictos.append(" entre: ");
                            informeConflictos.append(accion.getAccion());
                            informeConflictos.append("-DESPLAZAR.\n");
                            if (accion.getAccion() == AccionLR.TIPO_ACCION.REDUCIR) {
                                Produccion produccion = GRAMATICA.getProduccion(accion.getValor());
                                Elemento desplazamiento = GRAMATICA.getElemento(antic);
                                informeConflictos.append("\t  Reducir: ");
                                informeConflictos.append(produccion);
                                informeConflictos.append('\n');
                                informeConflictos.append("\tDesplazar: ");
                                informeConflictos.append(desplazamiento.getPresentacion());
                                informeConflictos.append('\n');
                                if (solucionarConflicto(produccion, desplazamiento) == AccionLR.TIPO_ACCION.REDUCIR) {
                                    this.acciones[antic][estado.getId()] = AccionLR.reduccion(item.getNumProduccion());
                                }
                            }
                        } else if (GRAMATICA.getProduccion(item.getNumProduccion()).esAumentada()) {
                            this.acciones[antic][estado.getId()] = AccionLR.ACEPTACION;
                        } else {
                            this.acciones[antic][estado.getId()] = AccionLR.reduccion(item.getNumProduccion());
                        }
                    }
                }
            }
        }
    }

    private AccionLR.TIPO_ACCION solucionarConflicto(Produccion produccion, Elemento desplazado) {
        Iterator<Elemento> it = produccion.getLadoDer().iterator();
        Stack<Elemento> stack = new Stack<>();
        while (it.hasNext()) {
            Elemento next = it.next();
            stack.push(next);
        }
        while (!stack.isEmpty()) {
            Elemento deProd = stack.pop();
            if (deProd.esTerminal()) {
                if (deProd.getPrecedencia() > desplazado.getPrecedencia()) {
                    informeConflictos.append(">>> Solucionado (por precedencia) a favor de la reducción de: ");
                    informeConflictos.append(produccion);
                    informeConflictos.append("\n\n");
                    return AccionLR.TIPO_ACCION.REDUCIR;
                } else if (deProd.getPrecedencia() < desplazado.getPrecedencia()) {
                    informeConflictos.append(">>> Solucionado (por precedencia) a favor del desplazamiento de: ");
                    informeConflictos.append(desplazado.getPresentacion());
                    informeConflictos.append("\n\n");
                    return AccionLR.TIPO_ACCION.DESPLAZAR;
                }
            }
        }
        if (desplazado.getAsociatividad() == Elemento.ASOC_IZQ) {
            informeConflictos.append(">>> Solucionado (por asociatividad izquierda) a favor de la reducción de: ");
            informeConflictos.append(produccion);
            informeConflictos.append("\n\n");
            return AccionLR.TIPO_ACCION.REDUCIR;
        } else {
            informeConflictos.append(">>> Solucionado (por asociatividad derecha) a favor del desplazamiento de: ");
            informeConflictos.append(desplazado.getPresentacion());
            informeConflictos.append("\n\n");
            return AccionLR.TIPO_ACCION.DESPLAZAR;
        }
    }

    public void generarCSV() {
        File csv = new File("TablaAS.csv");
        StringBuilder str = new StringBuilder("Estados");
        for (int i = 0; i < cantSimbolos; i++) {
            str.append(",");
            str.append(GRAMATICA.getElemento(i).getPresentacion());
        }
        str.append('\n');
        for (int j = 0; j < cantEstados; j++) {
            str.append(j);
            for (int i = 0; i < cantSimbolos; i++) {
                str.append(",");
                str.append(this.acciones[i][j].getPresentacion());
            }
            str.append('\n');
        }
        manejoArchivos.guardarArchivo(csv, str.toString());
    }

    public AccionLR[][] getAcciones() {
        return acciones;
    }

    public String getInformeConflictos() {
        if (informeConflictos.length() == 0) {
            return "Gramática sin conflictos.";
        }
        return informeConflictos.toString();
    }

}
