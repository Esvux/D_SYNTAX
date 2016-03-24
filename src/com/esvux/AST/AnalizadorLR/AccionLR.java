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

/**
 *
 * @author Esvin
 */
public class AccionLR {

    public enum TIPO_ACCION{ACEPTAR,REDUCIR,DESPLAZAR,ERROR};
    public static final AccionLR ACEPTACION = new AccionLR(TIPO_ACCION.ACEPTAR, 0);
    public static final AccionLR ERROR = new AccionLR(TIPO_ACCION.ERROR, 0);
    private TIPO_ACCION accion;
    private Integer valor;

    public static AccionLR desplazamiento(Integer destino){
        return new AccionLR(TIPO_ACCION.DESPLAZAR, destino);
    }
    
    public static AccionLR reduccion(Integer produccion){
        return new AccionLR(TIPO_ACCION.REDUCIR, produccion);
    }
    
    private AccionLR(TIPO_ACCION accion, Integer valor) {
        this.accion = accion;
        this.valor = valor;
    }
    
    public String getPresentacion(){
        if(this.accion==TIPO_ACCION.ACEPTAR)
            return "Aceptar";
        if(this.accion==TIPO_ACCION.DESPLAZAR)
            return "D"+this.valor;
        if(this.accion==TIPO_ACCION.REDUCIR)
            return "R"+this.valor;        
        return "-";
    }

    public Boolean noEsError(){
        return this.accion != TIPO_ACCION.ERROR; 
    }
    
    public TIPO_ACCION getAccion() {
        return accion;
    }

    public Integer getValor() {
        return valor;
    }
    
}
