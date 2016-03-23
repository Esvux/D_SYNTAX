package com.esvux.AST;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Esvin
 */
public class Nodo {
    public static Long ID = 0L;
    public static final String 
            BOOLEANO = "booleano",
            ENTERO = "entero",
            DECIMAL = "decimal",
            CADENA = "cadena",
            CARACTER = "caracter",
            VARIABLE = "variable",
            SINTETIZADO = "atributo sintetizado",
            HEREDADO = "atributo heredado",
            RESULT = "result directo",
            ARREGLO = "arreglo",
            METODO = "llamada a método",
            INCREMENTO = "++",
            DECREMENTO = "--",
            POTENCIA = "^",
            MULTIPLICA = "*",
            DIVIDE = "/",
            SUMA = "+",
            RESTA = "-",
            NOT = "!",
            AND = "&&",
            XOR = "x",
            OR = "||",
            WRITE = "llamada a write",
            ASIGNACION = "asignación";
    private String etiqueta;
    private String valor;
    private String id;
    private ArrayList<Nodo> nodos;

    public Nodo(String etiqueta, String valor, ArrayList<Nodo> nodos) {
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.nodos = nodos;
        ID++;
        this.id = "nodo"+ID.toString();
    }

    public Nodo(String etiqueta, String valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.nodos = new ArrayList<>();
        ID++;
        this.id = "nodo"+ID.toString();
    }

    public Nodo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.valor = null;
        this.nodos = new ArrayList<>();
        ID++;
        this.id = "nodo"+ID.toString();
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void addNodo(Nodo nodo){
        nodos.add(nodo);
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }
    
    public String getDOT(){
        String DOT = "digraph G{\n";
        DOT += "\tnode[shape=\"record\"];\n";
        DOT += this.getNodo();
        DOT += recorrerNodos(this.id, this.nodos);
        DOT += "}";
        return DOT;
    }

    private String getNodo(){
        if(this.valor == null)
            return "\t"+this.id+"[label=\""+this.etiqueta+"\"];\n";
        return "\t"+this.id+"[label=\""+this.etiqueta+"|"+this.valor+"\"];\n";
    }
    
    private String recorrerNodos(String padre, ArrayList<Nodo> nodos) {
        String DOT = "";
        Iterator<Nodo> it = nodos.iterator();
        while(it.hasNext()){
            Nodo nodo = it.next();
            DOT += nodo.getNodo();
            DOT += "\t"+padre + "->" + nodo.id + ";\n";
            DOT += recorrerNodos(nodo.id, nodo.nodos);
        }
        return DOT;
    }
}
