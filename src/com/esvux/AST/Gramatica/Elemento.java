package com.esvux.AST.Gramatica;

import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class Elemento {

    public static final int PREC_DEFAULT = 0;
    public static final String EPSILON = "ε";
    public static final String FINAL = "$";
    public static final String TIPO_DEFAULT = "null", TIPO_INT = "int", TIPO_CHAR = "char", TIPO_DOUBLE = "double", TIPO_STRING = "string", TIPO_BOOL = "bool";
    public static final int ASOC_DER = 20, ASOC_IZQ = 30, ASOC_DEFAULT = ASOC_IZQ;
    public static final Elemento ELEM_EPSILON = nuevoElemento(EPSILON);    
    public static final Elemento ELEM_FINAL = nuevoElemento(FINAL);
    private Integer id;
    private String etiqueta;
    private Integer precedencia;
    private String tipo;
    private Integer asociatividad;
    private Boolean esTerminal;
    private TreeSet<Integer> primeros;
    private Boolean esAumentado;
    private Boolean esMarcador;
    
// <editor-fold defaultstate="collapsed" desc="Factory">
    public static Elemento nuevoElemento(String etiqueta, Integer precedencia, String tipo, Integer asociatividad) {
        Elemento nuevo = new Elemento(etiqueta, precedencia, tipo, asociatividad);
        return nuevo;
    }
    
    public static Elemento nuevoElemento(String etiqueta, Integer precedencia, String tipo) {
        Elemento nuevo = new Elemento(etiqueta, precedencia, tipo, ASOC_DEFAULT);
        return nuevo;
    }
    
    public static Elemento nuevoElemento(String etiqueta, Integer precedencia) {
        Elemento nuevo = new Elemento(etiqueta, precedencia, TIPO_DEFAULT, ASOC_DEFAULT);
        return nuevo;
    }
    
    public static Elemento nuevoElemento(String etiqueta) {
        Elemento nuevo = new Elemento(etiqueta, PREC_DEFAULT, TIPO_DEFAULT, ASOC_DEFAULT);
        return nuevo;
    }       
    
    public static Elemento nuevoElemento(){
        Elemento nuevo = new Elemento(EPSILON, PREC_DEFAULT, TIPO_DEFAULT, ASOC_DEFAULT);
        return nuevo;
    }

    private Elemento(String etiqueta, Integer precedencia, String tipo, Integer asociatividad) {
        this.etiqueta = etiqueta.toLowerCase();
        this.precedencia = precedencia;
        this.tipo = tipo;
        this.asociatividad = asociatividad;
        this.esTerminal = true;
        this.id = Gramatica.llaveElementos;
        this.primeros = new TreeSet<>();
        this.esAumentado = false;
        this.esMarcador = false;
        Gramatica.llaveElementos++;
    }
//</editor-fold>
    
    
// <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta.toLowerCase();
    }

    public Integer getPrecedencia() {
        return precedencia;
    }

    public void setPrecedencia(Integer precedencia) {
        if (precedencia == null) {
            precedencia = PREC_DEFAULT;
        }
        this.precedencia = precedencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null) {
            tipo = TIPO_DEFAULT;
        }
        this.tipo = tipo.toLowerCase();
    }

    public Integer getAsociatividad() {
        return asociatividad;
    }

    public void setAsociatividad(String asociatividad) {
        if (asociatividad == null) {
            this.asociatividad = ASOC_DEFAULT;
        } else if (asociatividad.equalsIgnoreCase("Asociatividad.izq")) {
            this.asociatividad = ASOC_IZQ;
        } else if (asociatividad.equalsIgnoreCase("Asociatividad.der")) {
            this.asociatividad = ASOC_DER;
        }
    }
    
    public void marcarComoMarcador(){
        this.esMarcador = true;
    }
    
    public void marcarComoNoTerminal(){
        this.esTerminal = false;
    }

    public void marcarComoTerminal(){
        this.esTerminal = true;
    }
        
    public void marcarComoAumentado(){
        this.esAumentado = true;
    }
    
    public Boolean esTerminal(){
        return this.esTerminal;
    }
    
    public Boolean esNoTerminal(){
        return !this.esTerminal;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public String getPresentacion(){
        if(esTerminal)
            return this.etiqueta;
        return this.etiqueta.toUpperCase();
    }
    
    public void addPrimeros(Integer i){
        this.primeros.add(i);
    }
    
    public void addPrimeros(TreeSet<Integer> i){
        this.primeros.addAll(i);
    }
    
    public Boolean esMarcador(){
        return this.esMarcador;
    }
    
    public Boolean esAumentado(){
        return this.esAumentado;
    }
    
    public TreeSet<Integer> getPrimeros() {
        return primeros;
    }    
//</editor-fold>

    
}
