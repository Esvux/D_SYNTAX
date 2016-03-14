package com.esvux.AST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Esvin
 */
public class Gramatica {

    private String raiz;
    private HashMap<String, ElementoGramatical> terminales;
    private HashMap<String, ElementoGramatical> noTerminales;
    private ArrayList<ErrorDeCompilacion> errores;

    public Gramatica() {
        terminales = new HashMap<>();
        noTerminales = new HashMap<>();
        errores = new ArrayList<>();
        raiz = null;
    }

    public void agregarTerminal(ElementoGramatical terminal) {
        String clave = terminal.getEtiqueta().toLowerCase();
        String claveNoTerminal = terminal.getEtiqueta().toUpperCase();
        if (noTerminales.containsKey(claveNoTerminal)) {
            //Reportar error al declarar un terminal con el nombre de un NO TERMINAL ya existente.
            return;
        }
        terminales.put(clave, terminal);
    }

    public void agregarNoTerminal(ElementoGramatical noTerminal) {
        String clave = noTerminal.getEtiqueta().toUpperCase();
        String claveTerminal = noTerminal.getEtiqueta().toLowerCase();
        if (terminales.containsKey(claveTerminal)) {
            //Reportar error al declarar un terminal con el nombre de un TERMINAL ya existente.
            return;
        }
        noTerminales.put(clave, noTerminal);
    }

    public void registrarPrecedencia(ArrayList<String> claves, Integer precedencia, String asociatividad) {
        Iterator<String> it = claves.iterator();
        while (it.hasNext()) {
            String clave = it.next().toLowerCase();
            if (!terminales.containsKey(clave)) {
                //Reportar error al asignar precedencia [y asociatividad] a un NO TERMINAL que no existe.
                return;
            }
            ElementoGramatical temp = terminales.get(clave);
            temp.setAsociatividad(asociatividad);
            temp.setPrecedencia(precedencia);
            terminales.replace(clave, temp);
        }
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public void agregarError(ErrorDeCompilacion error) {
        errores.add(error);
    }

    public boolean validarGramatica() {
        return !(errores.size() > 0);
    }

}
