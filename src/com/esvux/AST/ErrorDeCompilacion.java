package com.esvux.AST;

/**
 *
 * @author Esvin
 */
public class ErrorDeCompilacion {
    private Integer fila, columna;
    private String descripcion;

    public ErrorDeCompilacion() {
        this.fila = -1;
        this.columna = -1;
        this.descripcion = "Error no reconocido.";
    }

    public ErrorDeCompilacion(Integer fila, Integer columna, String descripcion) {
        this.fila = fila;
        this.columna = columna;
        this.descripcion = descripcion;
    }    
    
}
