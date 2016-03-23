package com.esvux.AST.codigo;

import java.util.ArrayList;

/**
 *
 * @author Esvin
 */
public class Metodo {
    
    private String nombre;
    private String tipo;
    private ArrayList<Variable> parametros;
    private ArrayList<Object> sentencias;

    public Metodo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.parametros = new ArrayList<>();
        this.sentencias = new ArrayList<>();
    }    
    
    public Metodo(String nombre) {
        this.nombre = nombre;
        this.tipo = null;
        this.parametros = new ArrayList<>();
        this.sentencias = new ArrayList<>();
    }

    public boolean esFuncion(){
        return this.tipo != null;
    }
    
    public void addSentencia(Object sentencia){
        this.sentencias.add(sentencia);
    }
    
    
    /*
    <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    |                    getters y setters                     |
    <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Variable> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Variable> parametros) {
        this.parametros = parametros;
    }

    public ArrayList<Object> getSentencias() {
        return sentencias;
    }

    public void setSentencias(ArrayList<Object> sentencias) {
        this.sentencias = sentencias;
    }
    
}
