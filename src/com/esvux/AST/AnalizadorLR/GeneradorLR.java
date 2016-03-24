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

import com.esvux.AST.Gramatica.Elemento;
import com.esvux.AST.Gramatica.Gramatica;
import com.esvux.AST.Gramatica.Produccion;
import com.esvux.Graficas.Graphviz;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class GeneradorLR {

    private TreeSet<EstadoLR> estadosLR;
    private ArrayList<TransicionLR> transicionesLR;
    private TablaAnalisisLR tablaLR;
    private static Gramatica gram;
    private Integer llaveEstados;

    public GeneradorLR(Gramatica gram) {
        this.estadosLR = new TreeSet<>();
        this.transicionesLR = new ArrayList<>();
        this.gram = gram;
        this.llaveEstados = 0;
    }

    public void construirEstadosLR() {
        Produccion inicio = gram.getRaiz();
        if (inicio == null) {
            //Reportar error al inicio del algoritmo, imposible obtener la producción de inicio
            return;
        }
        gram.cambiarModoLR();
        //Item aumentado... que vendría a ser la producción raíz del estado inicial
        ItemLR itemAumentado = new ItemLR(inicio, Elemento.ELEM_FINAL);
        //Nuevo estado vacío...
        EstadoLR inicial = new EstadoLR(llaveEstados++);
        //Agrego el elemento al nuevo estado...
        inicial.add(itemAumentado);
        //Reemplazo el estado inicial por el resultado de aplicar la cerradura sobre él...
        inicial = cerradura(inicial);
        //Agrego el estado inicial a la colección principal de estados...
        this.estadosLR.add(inicial);
        //Declaro una pila de estados para recorrerlos con IR_A...
        Stack<EstadoLR> pilaAuxiliar = new Stack<>();
        //Agrego el estado inicial a una pila para recorrerlo después...
        pilaAuxiliar.push(inicial);
        //Inicia las operaciones IR_A sobre los estados, mientras hayan estados aún en la pila...
        while (!pilaAuxiliar.isEmpty()) {
            EstadoLR origen = pilaAuxiliar.pop();
            //Elementos de la gramática
            Iterator<Elemento> itElementos = gram.getElementosLR().values().iterator();
            while (itElementos.hasNext()) {
                Elemento transicion = itElementos.next();
                EstadoLR nuevo = ir_a(origen, transicion.getId());
                if (!nuevo.isEmpty()) {
                    Iterator<EstadoLR> it = this.estadosLR.iterator();
                    Integer destino = null;
                    while (it.hasNext()) {
                        EstadoLR next = it.next();
                        if (next.compareTo(nuevo) == 0) {
                            destino = next.getId();
                            break;
                        }
                    }
                    if (destino == null) {
                        destino = llaveEstados++;
                    }
                    nuevo.setId(destino);
                    TransicionLR nueva = new TransicionLR(origen.getId(), destino, transicion.getId());
                    this.transicionesLR.add(nueva);
                    if (this.estadosLR.add(nuevo)) {
                        pilaAuxiliar.add(nuevo);
                    }
                }
            }
        }
        dibujarGrafo();
        tablaLR = new TablaAnalisisLR(gram, estadosLR, transicionesLR);
        tablaLR.mostrar();
    }

    private void dibujarGrafo() {
        Graphviz gv = new Graphviz();
        gv.startGraph();
        gv.addln("graph [fontsize=10 rankdir = \"LR\"];");
        gv.addln("ratio = auto;");
        gv.addln("node[style = \"filled, bold\" penwidth = 3 fillcolor = \"white\" fontname = \"Courier New\" shape = \"Mrecord\"];");
        Iterator<EstadoLR> itEstados = this.estadosLR.iterator();
        while (itEstados.hasNext()) {
            EstadoLR next = itEstados.next();
            gv.addln(next.getDOT(gram));
        }
        Iterator<TransicionLR> itTransiciones = this.transicionesLR.iterator();
        while (itTransiciones.hasNext()) {
            TransicionLR next = itTransiciones.next();
            gv.addln(next.getDOT(gram));
        }
        gv.endGraph();
        System.out.println(gv.getDotSource());
        String type = "png";
        File out = new File("ejemplo." + type);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);        
    }

    private EstadoLR ir_a(EstadoLR I, Integer X) {
        EstadoLR J = new EstadoLR(-1);
        for (Iterator<ItemLR> itItems = I.iterator(); itItems.hasNext();) {
            ItemLR next = itItems.next();
            ItemLR nuevo = new ItemLR(next);
            if (nuevo.getPuntero() < nuevo.getDerivacion().size()) {
                if (nuevo.getDerivacion().get(nuevo.getPuntero()).compareTo(X) == 0) {
                    nuevo.correrPuntero();
                    nuevo.desmarcarComoRecorrido();
                    J.add(nuevo);
                }
            }
        }
        if (J.isEmpty()) {
            return J;
        }
        return cerradura(J);
    }

    private EstadoLR cerradura(EstadoLR I) {
        EstadoLR J = new EstadoLR(I.getId());
        Iterator<ItemLR> iterator = I.iterator();
        while (iterator.hasNext()) {
            ItemLR next = iterator.next();
            if (next.fueRecorrido()) {
                continue;
            }
            next.marcarComoRecorrido();
            J.add(next);
            if (next.getPuntero() < next.getDerivacion().size()) {
                Elemento temp = gram.getElemento(next.getDerivacion().get(next.getPuntero()));
                if (temp != null && temp.esNoTerminal()) {
                    Iterator<Produccion> beta = gram.getProducciones(temp.getId()).iterator();
                    TreeSet<Integer> anticipacion = anticipacion(next.getBeta(), next.getAnticipacion());
                    while (beta.hasNext()) {
                        Produccion prod = beta.next();
                        ItemLR nuevo = new ItemLR(prod, anticipacion);
                        J.add(nuevo);
                    }
                    J.addAll(cerradura(J));
                }
            }
        }
        return J;
    }

    private TreeSet<Integer> anticipacion(ArrayList<Integer> Beta, TreeSet<Integer> a) {
        Iterator<Integer> itBeta = Beta.iterator();
        TreeSet<Integer> anticipacion = new TreeSet<>();
        while (itBeta.hasNext()) {
            Elemento temp = gram.getElementosLR().get(itBeta.next());
            //No contiene al elemento epsilon
            if (!temp.getPrimeros().contains(Elemento.ELEM_EPSILON.getId())) {
                anticipacion.addAll(temp.getPrimeros());
                return anticipacion;
            } else {
                //Si contiene epsilon, agregar todos, menos epsilon y continuar 
                //con el siguiente elemento o los de símbolos de anticipación
                Iterator<Integer> antc = temp.getPrimeros().iterator();
                while (antc.hasNext()) {
                    Integer next = antc.next();
                    if (next.compareTo(Elemento.ELEM_EPSILON.getId()) != 0) {
                        anticipacion.add(next);
                    }
                }
            }
        }
        anticipacion.addAll(a);
        return anticipacion;
    }

}
