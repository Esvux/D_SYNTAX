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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// </editor-fold>                        
package com.esvux.AST.AnalizadorLR;

import com.esvux.AST.Gramatica.Gramatica;
import com.esvux.AST.Gramatica.Produccion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class TablaAnalisisLR {

    private Gramatica gramatica;
    private TreeSet<EstadoLR> estadosLR;
    private ArrayList<TransicionLR> transicionesLR;
    private AccionLR acciones[][];
    private int cantEstados, cantSimbolos;

    public TablaAnalisisLR(Gramatica gramatica, TreeSet<EstadoLR> estadosLR, ArrayList<TransicionLR> transicionesLR) {
        this.gramatica = gramatica;
        this.estadosLR = estadosLR;
        this.transicionesLR = transicionesLR;
        this.cantSimbolos = gramatica.getElementosLR().size();
        this.cantEstados = estadosLR.size();
        this.acciones = new AccionLR[cantSimbolos][cantEstados];
        materializarTabla();
    }

    private void materializarTabla() {
        //Primero, todas las celdas son error...
        for (int i = 0; i < cantSimbolos; i++) {
            for (int j = 0; j < cantEstados; j++) {
                this.acciones[i][j] = AccionLR.ERROR;
            }
        }
        materializarDesplazamientos();
        materializarReducciones();
    }

    private void materializarDesplazamientos() {
        Iterator<TransicionLR> itTransiciones = this.transicionesLR.iterator();
        while (itTransiciones.hasNext()) {
            TransicionLR transicion = itTransiciones.next();
            if(this.acciones[transicion.getTransicion()][transicion.getOrigen()].noEsError()){
                System.err.println("Se presentó un conflicto de tipo "+acciones[transicion.getTransicion()][transicion.getOrigen()]+"-"+"DESPLAZAMIENTO.");
            }
            this.acciones[transicion.getTransicion()][transicion.getOrigen()] = AccionLR.desplazamiento(transicion.getDestino());
        }
    }

    private void materializarReducciones() {
        Iterator<EstadoLR> itEstados = this.estadosLR.iterator();
        while (itEstados.hasNext()) {
            EstadoLR estado = itEstados.next();
            Iterator<ItemLR> itItems = estado.iterator();
            while (itItems.hasNext()) {
                ItemLR item = itItems.next();
                if (item.getPuntero() == item.getDerivacion().size()) {
                    Iterator<Integer> itAntic = item.getAnticipacion().iterator();
                    while (itAntic.hasNext()) {
                        Integer antic = itAntic.next();
                        if(this.acciones[antic][estado.getId()].noEsError()){
                            System.err.println("Se presentó un conflicto de tipo "+acciones[antic][estado.getId()]+"-"+"REDUCCION.");
                        }                            
                        if (gramatica.getProduccion(item.getNumProduccion()).esAumentada()) {
                            this.acciones[antic][estado.getId()] = AccionLR.ACEPTACION;
                        } else {
                            this.acciones[antic][estado.getId()] = AccionLR.reduccion(item.getNumProduccion());
                        }
                    }
                }
            }
        }
    }

    public void mostrar() {
        System.out.print("Estados");
        for (int i = 0; i < cantSimbolos; i++) {
            System.out.print("," + gramatica.getElemento(i).getPresentacion());
        }
        System.out.println();
        for (int j = 0; j < cantEstados; j++) {
            System.out.print(j);
            for (int i = 0; i < cantSimbolos; i++) {
                System.out.print("," + this.acciones[i][j].getPresentacion());
            }
            System.out.println();
        }
    }

}
