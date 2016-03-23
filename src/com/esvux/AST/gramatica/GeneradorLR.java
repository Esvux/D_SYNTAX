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
package com.esvux.AST.gramatica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class GeneradorLR {

    private TreeSet<EstadoLR> estadosLR;
    private Gramatica gram;
    private static Integer llaveEstados = 0;
    private static final String prefijoEstados = "S";

    public GeneradorLR(Gramatica gram) {
        this.estadosLR = new TreeSet<>();
        this.gram = gram;
    }

    private static String getNuevoNombre() {
        return prefijoEstados + llaveEstados++;
    }

    public void construirEstadosLR() {
        Produccion inicio = gram.getRaiz();
        if (inicio == null) {
            //Reportar error al inicio del algoritmo, imposible obtener la producción de inicio
            return;
        }
        gram.cambiarModoLR();
        ItemLR itemAumentado = new ItemLR(inicio, Elemento.ELEM_FINAL);
        EstadoLR inicial = new EstadoLR(getNuevoNombre());
        inicial.add(itemAumentado);
        inicial = cerradura(inicial);
        this.estadosLR.add(inicial);
        for (Iterator<Elemento> itElementos = gram.getElementosLR().values().iterator(); itElementos.hasNext();) {
            EstadoLR nuevo = ir_a(inicial, itElementos.next().getId());
            if (!nuevo.isEmpty()) {
                nuevo.setNombre(getNuevoNombre());
                this.estadosLR.add(nuevo);
            }
        }
        for (Iterator<EstadoLR> iterator = this.estadosLR.iterator(); iterator.hasNext();) {
            EstadoLR next = iterator.next();
            System.out.println("-----------------------------------");
            next.mostrar(gram);
        }
        System.out.println("-----------------------------------");
    }

    private EstadoLR ir_a(EstadoLR I, Integer X) {
        EstadoLR J = new EstadoLR("");
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
        if(J.isEmpty())
            return J;
        return cerradura(J);
    }

    private EstadoLR cerradura(EstadoLR I) {
        EstadoLR J = new EstadoLR(I.getNombre());
        for (Iterator<ItemLR> iterator = I.iterator(); iterator.hasNext();) {
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
