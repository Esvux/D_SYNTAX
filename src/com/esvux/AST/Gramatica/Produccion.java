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
package com.esvux.AST.Gramatica;

import com.esvux.AST.Nodo;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Esvin
 */
public class Produccion implements Comparable<Produccion> {

    private ArrayList<Nodo> acciones;
    private String ladoIzq;
    private ArrayList<Elemento> ladoDer;
    private Integer id;
    private Boolean esMarcador;
    private Boolean esAumentada;
    private static Gramatica GRAMATICA;

    
// <editor-fold defaultstate="collapsed" desc="Factory">    
    public static void setGRAMATICA(Gramatica gramatica) {
        GRAMATICA = gramatica;
    }

    //Factory para producciones normales...
    public static Produccion nuevaProduccion(ArrayList<String> ladoDer, ArrayList<Nodo> acciones) {
        Produccion produccion = new Produccion(ladoDer, acciones);
        produccion.id = Gramatica.llaveProducciones;
        Gramatica.llaveProducciones++;
        return produccion;
    }

    //Factory para la producción aumentada...
    public static Produccion comoAumentada(String ladoIzq, Elemento ladoDer) {
        Produccion produccion = new Produccion(ladoIzq, ladoDer);
        produccion.id = Gramatica.llaveProducciones;
        Gramatica.llaveProducciones++;
        return produccion;
    }

    //Factory para los marcadores
    public static Produccion comoMarcador(String ladoIzq, ArrayList<Nodo> acciones) {
        Produccion produccion = new Produccion(ladoIzq, acciones);
        produccion.id = Gramatica.llaveProducciones;
        Gramatica.llaveProducciones++;
        return produccion;
    }

    //Producción normal...
    private Produccion(ArrayList<String> ladoDer, ArrayList<Nodo> acciones) {
        this.ladoIzq = null;
        this.ladoDer = new ArrayList<>();
        this.acciones = acciones;
        this.esMarcador = false;
        this.esAumentada = false;
        setLadoDer(ladoDer);
    }

    //Producción expandida...
    private Produccion(String ladoIzq, Elemento ladoDer) {
        this.ladoIzq = ladoIzq.toLowerCase();
        this.ladoDer = new ArrayList<>();
        this.acciones = null;
        this.esMarcador = false;
        this.esAumentada = true;
        this.ladoDer.add(ladoDer);
    }

    //Producción marcador...
    private Produccion(String ladoIzq, ArrayList<Nodo> acciones) {
        this.ladoIzq = ladoIzq.toLowerCase();
        this.ladoDer = new ArrayList<>();
        this.acciones = acciones;
        this.esMarcador = true;
        this.esAumentada = false;
        this.ladoDer.add(Elemento.ELEM_EPSILON);
    }
//</editor-fold>

    
    private void setLadoDer(ArrayList<String> ladoDer) {
        Iterator<String> it = ladoDer.iterator();
        while (it.hasNext()) {
            Elemento elemento = GRAMATICA.getElemento(it.next().toLowerCase());
            if (elemento == null) {
                //Reportar error al colocar un elemento que no existe en el lado derecho de una producción.
            } else {
                this.ladoDer.add(elemento);
            }
        }
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getEncabezado() {
        return GRAMATICA.getElemento(ladoIzq).getId();
    }

    public ArrayList<Integer> getDerivacion() {
        ArrayList<Integer> derivacion = new ArrayList<>();
        Iterator<Elemento> it = this.ladoDer.iterator();
        while (it.hasNext()) {
            derivacion.add(it.next().getId());
        }
        return derivacion;
    }

    public String getLadoIzq() {
        return this.ladoIzq;
    }

    public void setLadoIzq(String ladoIzq) {
        this.ladoIzq = ladoIzq.toLowerCase();
    }

    public ArrayList<Elemento> getLadoDer() {
        return ladoDer;
    }

    public void marcarComoMarcador(){
        this.esMarcador = true;
    }

    public Boolean esMarcador(){
        return this.esMarcador;
    }
    
    public Boolean esAumentada(){
        return this.esAumentada;
    }
    
    @Override
    public int compareTo(Produccion o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        String produccion = this.ladoIzq.toUpperCase() + "->";
        Iterator<Elemento> it = this.ladoDer.iterator();
        while (it.hasNext()) {
            produccion += it.next().getPresentacion();
            produccion += it.hasNext() ? "+" : "";
        }
        return produccion;
    }
    
}
