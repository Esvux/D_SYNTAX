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
public class ItemLR implements Comparable<ItemLR> {

    private Integer encabezado;
    private ArrayList<Integer> derivacion;
    private TreeSet<Integer> anticipacion;
    private Integer puntero;
    private Boolean fueRecorrido;

    public ItemLR(Produccion produccion, Elemento anticipacion) {
        this.encabezado = produccion.getEncabezado();
        this.puntero = 0;
        this.derivacion = produccion.getDerivacion();
        this.anticipacion = new TreeSet<>();
        this.anticipacion.add(anticipacion.getId());
        this.fueRecorrido = false;
    }
    
    public ItemLR(Produccion produccion, TreeSet<Integer> anticipacion){
        this.encabezado = produccion.getEncabezado();
        this.puntero = 0;
        this.derivacion = produccion.getDerivacion();
        this.anticipacion = anticipacion;
        this.fueRecorrido = false;
    }

    public ItemLR(ItemLR copia) {
        this.encabezado = copia.encabezado;
        this.puntero = copia.puntero;
        this.derivacion = copia.derivacion;
        this.anticipacion = copia.anticipacion;
        this.fueRecorrido = false;
    }

// <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Integer getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Integer encabezado) {
        this.encabezado = encabezado;
    }

    public ArrayList<Integer> getDerivacion() {
        return derivacion;
    }

    public void setDerivacion(ArrayList<Integer> derivacion) {
        this.derivacion = derivacion;
    }

    public TreeSet<Integer> getAnticipacion() {
        return anticipacion;
    }

    public void setAnticipacion(TreeSet<Integer> anticipacion) {
        this.anticipacion = anticipacion;
    }

    public Integer getPuntero() {
        return puntero;
    }

    public void correrPuntero() {
        this.puntero = puntero + 1;
    }   
    
    public ArrayList<Integer> getBeta(){
        ArrayList<Integer> beta = new ArrayList<>();
        for (int i=puntero+1; i<derivacion.size(); i++) {
            beta.add(derivacion.get(i));
        }
        return beta;
    }

    public Boolean fueRecorrido() {
        return fueRecorrido;
    }

    public void marcarComoRecorrido() {
        this.fueRecorrido = true;
    }    
    
    public void desmarcarComoRecorrido(){
        this.fueRecorrido = false;
    }
    
// </editor-fold>      
    
    @Override
    public int compareTo(ItemLR o) {
        //Compara los encabezados
        if (this.encabezado < o.encabezado) {
            return -1;
        }
        if (this.encabezado > o.encabezado) {
            return +1;
        }
        //Compara los punteros
        if (this.puntero < o.puntero) {
            return -1;
        }
        if (this.puntero > o.puntero) {
            return +1;
        }
        //Compara los componentes de derivacion
        Iterator<Integer> thisDer = this.derivacion.iterator();
        Iterator<Integer> oDer = o.derivacion.iterator();
        while (oDer.hasNext() && thisDer.hasNext()) {
            Integer oNext = oDer.next();
            Integer thisNext = thisDer.next();
            if(thisNext < oNext)
                return -1;
            if(thisNext > oNext)
                return +1;
        }
        if(thisDer.hasNext())
            return +1;
        if(oDer.hasNext())
            return -1;
        //Compara los simbolos de anticipación
        Iterator<Integer> thisAnt = this.anticipacion.iterator();
        Iterator<Integer> oAnt = o.anticipacion.iterator();
        while (oAnt.hasNext() && thisAnt.hasNext()) {
            Integer oNext = oAnt.next();
            Integer thisNext = thisAnt.next();
            if(thisNext < oNext)
                return -1;
            if(thisNext > oNext)
                return +1;
        }
        if(thisAnt.hasNext())
            return +1;
        if(oAnt.hasNext())
            return -1;
        return 0;
    }

    
}
