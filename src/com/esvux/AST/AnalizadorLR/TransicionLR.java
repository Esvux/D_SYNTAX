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

/**
 *
 * @author Esvin
 */
public class TransicionLR {

    private Integer origen, destino, transicion;

    public TransicionLR(Integer origen, Integer destino, Integer transicion) {
        this.origen = origen;
        this.destino = destino;
        this.transicion = transicion;
    }

    public void mostrar(Gramatica gram) {
        System.out.println("ir_a("
                + EstadoLR.PREFIJO + origen + ","
                + gram.getElemento(transicion).getPresentacion() + ")->"
                + EstadoLR.PREFIJO + destino);
    }

    public String getDOT(Gramatica gram) {
        return EstadoLR.PREFIJO + origen + "->"
             + EstadoLR.PREFIJO + destino + "[label=\""+gram.getElemento(transicion).getPresentacion()+"\"];";
    }

    public Integer getOrigen() {
        return origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public Integer getTransicion() {
        return transicion;
    }
    
}
