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

import java.util.Iterator;

/**
 *
 * @author Esvin
 */
public class EstadoLR extends java.util.TreeSet<ItemLR> implements Comparable<EstadoLR> {

    private String nombre;
    

    public EstadoLR(String nombre) {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(EstadoLR o) {
        if (this.size() < o.size()) {
            return -1;
        }
        if (this.size() < o.size()) {
            return +1;
        }
        Iterator<ItemLR> itThis = this.iterator();
        while (itThis.hasNext()) {
            ItemLR temporal = itThis.next();
            Iterator<ItemLR> itO = o.iterator();
            int buscado = 20;
            while (itO.hasNext()) {
                buscado = temporal.compareTo(itO.next());
                if (buscado == 0) {
                    break;
                }
            }
            if (buscado != 0) {
                return buscado;
            }
        }
        return 0;
    }

    public void mostrar(Gramatica gram) {
        String cadena = "Estado: " + this.nombre + "\n";
        Iterator<ItemLR> itItems = this.iterator();
        while (itItems.hasNext()) {
            ItemLR next = itItems.next();
            cadena += gram.getElemento(next.getEncabezado()).getPresentacion() + "->";
            for (int i = 0; i < next.getDerivacion().size(); i++) {
                if (i == next.getPuntero()) {
                    cadena += "·";
                }
                cadena += gram.getElemento(next.getDerivacion().get(i)).getPresentacion() + " ";
            }
            if (next.getPuntero().compareTo(next.getDerivacion().size()) == 0) {
                cadena += "·";
            }
            cadena += ", {";
            for (Iterator<Integer> iterator = next.getAnticipacion().iterator(); iterator.hasNext();) {
                cadena += gram.getElemento(iterator.next()).getPresentacion() + (iterator.hasNext() ? "," : "");
            }
            cadena += "}\n";
        }
        System.out.println(cadena);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
