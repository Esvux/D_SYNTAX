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
import java.util.Iterator;

/**
 *
 * @author Esvin
 */
public class EstadoLR extends java.util.TreeSet<ItemLR> implements Comparable<EstadoLR> {

    private Integer id;
    public static final String PREFIJO="S";

    public EstadoLR(Integer id) {
        super();
        this.id = id;
    }

    public String getNombre() {
        return PREFIJO+id;
    }

    @Override
    public int compareTo(EstadoLR o) {
        if (this.size() < o.size()) {
            return -1;
        }
        if (this.size() > o.size()) {
            return +1;
        }
        //Se asume que los dos estados tienen la misma cantidad de elementos...
        Iterator<ItemLR> thisItItemsLR = this.iterator();
        //Se inicia el recorrido sobre los elementos de este estado..
        while (thisItItemsLR.hasNext()) {
            //Elemento actual...
            ItemLR temporal = thisItItemsLR.next();
            //Se obtienen los elementos del otro estado...
            Iterator<ItemLR> oItItemsLR = o.iterator();            
            int buscado = 20;
            while (oItItemsLR.hasNext()) {
                //La variable 'buscado' almacena la comparación entre estados...
                buscado = temporal.compareTo(oItItemsLR.next());
                if (buscado == 0) {
                    //Si los estados son iguales, termina el ciclo...
                    break;
                }
            }
            //Si al terminar el ciclo 'buscado' no es 0, entonces los estados son diferentes...
            if (buscado != 0) {
                return buscado;
            }
        }
        return 0;
    }

    public void mostrar(Gramatica gram) {
        String cadena = "Estado: " + this.getNombre() + "\n";
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

    public String getDOT(Gramatica gram) {        
        String cadena = this.getNombre()+"[label=<<table border=\"0\" cellborder=\"0\" cellpadding=\"3\" bgcolor=\"white\">\n";
        cadena += "<tr><td bgcolor=\"black\" align=\"center\" colspan=\"2\"><font color=\"white\" size=\"+10\">"+"Estado: " + this.getNombre() + "</font></td></tr>\n";
        Iterator<ItemLR> itItems = this.iterator();
        while (itItems.hasNext()) {
            ItemLR next = itItems.next();
            cadena += "<tr><td align=\"left\">"+gram.getElemento(next.getEncabezado()).getPresentacion() + "&#8212;&gt;";
            for (int i = 0; i < next.getDerivacion().size(); i++) {
                if (i == next.getPuntero()) {
                    cadena += "&middot;";
                }
                cadena += gram.getElemento(next.getDerivacion().get(i)).getPresentacion() + " ";
            }
            if (next.getPuntero().compareTo(next.getDerivacion().size()) == 0) {
                cadena += "&middot;";
            }
            cadena += ", &#123;";
            for (Iterator<Integer> iterator = next.getAnticipacion().iterator(); iterator.hasNext();) {
                cadena += gram.getElemento(iterator.next()).getPresentacion() + (iterator.hasNext() ? "," : "");
            }
            cadena += "&#125;</td></tr>\n";
        }
        return cadena + "</table>>];\n";
    }

    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }

}
