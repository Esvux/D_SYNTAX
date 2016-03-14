package com.esvux.AST;

/**
 *
 * @author Esvin
 */
public class ElementoGramatical {

    public static final int PREC_DEFAULT = 0;
    public static final String EPSILON = "__";
    public static final String TIPO_DEFAULT = "null", TIPO_INT = "int", TIPO_CHAR = "char", TIPO_DOUBLE = "double", TIPO_STRING = "string", TIPO_BOOL = "bool";
    public static final int ASOC_DER = 20, ASOC_IZQ = 30, ASOC_DEFAULT = ASOC_IZQ;
    private String etiqueta;
    private Integer precedencia;
    private String tipo;
    private Integer asociatividad;

    public ElementoGramatical(String etiqueta, Integer precedencia, String tipo, Integer asociatividad) {
        this.etiqueta = etiqueta;
        this.precedencia = precedencia;
        this.tipo = tipo;
        this.asociatividad = asociatividad;
    }

    public ElementoGramatical(String etiqueta, Integer precedencia, String tipo) {
        this.etiqueta = etiqueta;
        this.precedencia = precedencia;
        this.tipo = tipo;
        this.asociatividad = ASOC_DEFAULT;
    }

    public ElementoGramatical(String etiqueta, Integer precedencia) {
        this.etiqueta = etiqueta;
        this.precedencia = precedencia;
        this.tipo = TIPO_DEFAULT;
        this.asociatividad = ASOC_DEFAULT;
    }

    public ElementoGramatical(String etiqueta) {
        this.etiqueta = etiqueta;
        this.precedencia = PREC_DEFAULT;
        this.tipo = TIPO_DEFAULT;
        this.asociatividad = ASOC_DEFAULT;
    }

    public ElementoGramatical() {
        this.etiqueta = EPSILON;
        this.precedencia = PREC_DEFAULT;
        this.tipo = TIPO_DEFAULT;
        this.asociatividad = ASOC_DEFAULT;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
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

}
