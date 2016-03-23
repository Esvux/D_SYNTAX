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
package com.esvux.AST.codigo;

/**
 *
 * @author Esvin
 */
public class Variable {
    
    private static final int GLOBAL = 0;
    private String nombre;
    private String tipo;
    private int ambito;
    private Object valor;

    public Variable(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = GLOBAL;
        this.valor = null;
    }

// <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }    
// </editor-fold>                        
    
}
