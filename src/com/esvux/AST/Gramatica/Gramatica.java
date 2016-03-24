package com.esvux.AST.Gramatica;

import com.esvux.AST.ErrorDeCompilacion;
import com.esvux.AST.Nodo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Esvin
 */
public class Gramatica {

    private String raiz;
    private HashMap<String, Elemento> elementos;
    private HashMap<Integer, Elemento> elementosLR;
    private TreeSet<Produccion> producciones;
    private ArrayList<ErrorDeCompilacion> errores;
    public static int llaveElementos = 0;
    public static int llaveMarcadores = 0;
    public static int llaveProducciones = 0;

    public Gramatica() {
        elementos = new HashMap<>();
        elementosLR = new HashMap<>();
        producciones = new TreeSet<>();
        errores = new ArrayList<>();
        raiz = null;
        agregarElemento(Elemento.ELEM_EPSILON);
        agregarElemento(Elemento.ELEM_FINAL);
    }

    public void agregarProduccion(Produccion produccion) {
        if (!this.producciones.add(produccion))
            ;//Reportar error al agregar una producción repetida a la gramática
    }

    public String agregarMarcador(ArrayList<Nodo> acciones) {
        String izq = "_M" + llaveMarcadores;
        Produccion marcador = Produccion.comoMarcador(izq, acciones);
        marcador.marcarComoMarcador();
        agregarProduccion(marcador);
        Elemento nuevo = Elemento.nuevoElemento(izq);
        nuevo.marcarComoNoTerminal();
        nuevo.marcarComoMarcador();
        agregarElemento(nuevo);
        llaveMarcadores++;
        return izq;
    }

    public void agregarElemento(Elemento elemento) {
        if (!elementos.containsKey(elemento.getEtiqueta())) {
            elementos.put(elemento.getEtiqueta(), elemento);
        }
        //Reportar error al declarar un elemento con el nombre de uno ya existente.
    }

    public void registrarPrecedencia(ArrayList<String> claves, Integer precedencia, String asociatividad) {
        Iterator<String> it = claves.iterator();
        while (it.hasNext()) {
            String clave = it.next().toLowerCase();
            if (elementos.containsKey(clave)) {
                Elemento temp = elementos.get(clave);
                if (temp.esTerminal()) {
                    temp.setAsociatividad(asociatividad);
                    temp.setPrecedencia(precedencia);
                    elementos.replace(clave, temp);
                }
                //Reportar error al asignar precedencia [y asociatividad] a un NO TERMINAL.
            }
            //Reportar error al asignar precedencia [y asociatividad] a un TERMINAL que no existe.
        }
    }

    public void setRaiz(String raiz) {
        raiz = raiz.toLowerCase();
        if (this.raiz != null) {
            //Reportar error de haber seteado dos veces la raíz de la gramática.
            return;
        }
        if (this.elementos.containsKey(raiz)) {
            this.raiz = aumentarGramatica(raiz);
        }
    }

    public Produccion getRaiz() {
        if (this.raiz == null) {
            //Reportar error de no haber declarado la raíz de la gramática.
            return null;
        }
        Iterator<Produccion> it = producciones.iterator();
        while (it.hasNext()) {
            Produccion produccion = it.next();
            if (produccion.getLadoIzq().equalsIgnoreCase(raiz)) {
                return produccion;
            }
        }
        return null;
    }

    public void agregarError(ErrorDeCompilacion error) {
        errores.add(error);
    }

    public boolean esGramaticaValida() {
        return !(errores.size() > 0);
    }

    public Elemento getElemento(String clave) {
        if (!this.elementos.containsKey(clave)) {
            return null;
        }
        return this.elementos.get(clave);
    }

    public void mostrarProducciones() {
        Iterator<Produccion> it = this.producciones.iterator();
        while (it.hasNext()) {
            Produccion next = it.next();
            System.out.println("\t(" + next.getId() + ") " + next.toString());
        }
    }

    private String aumentarGramatica(String raiz) {
        String aumentada = raiz + "'";
        Elemento aumentado = Elemento.nuevoElemento(aumentada);
        aumentado.marcarComoAumentado();
        aumentado.marcarComoNoTerminal();
        this.agregarElemento(aumentado);
        this.agregarProduccion(Produccion.comoAumentada(aumentada, this.getElemento(raiz)));
        return aumentada;
    }

    public void cambiarModoLR() {
        Iterator<Elemento> it = elementos.values().iterator();
        while (it.hasNext()) {
            Elemento temporal = it.next();
            temporal.addPrimeros(primeros(temporal));
            elementosLR.put(temporal.getId(), temporal);
        }
    }

    private TreeSet<Integer> primeros(Elemento elem) {
        if (elem.esMarcador()) {
            TreeSet<Integer> primeros = new TreeSet<>();
            primeros.add(Elemento.ELEM_EPSILON.getId());
            return primeros;
        }
        if (elem.esAumentado()) {
            TreeSet<Integer> primeros = new TreeSet<>();
            return primeros;
        }
        if (elem.esTerminal()) {
            TreeSet<Integer> primeros = new TreeSet<>();
            primeros.add(elem.getId());
            return primeros;
        }
        TreeSet<Integer> primeros = new TreeSet<>();
        Iterator<Produccion> itProd = this.producciones.iterator();
        while (itProd.hasNext()) {
            Produccion nextProduccion = itProd.next();
            if (!elem.getEtiqueta().equalsIgnoreCase(nextProduccion.getLadoIzq())) {
                continue;
            }
            Iterator<Elemento> itElem = nextProduccion.getLadoDer().iterator();
            Elemento nextElemento = itElem.next();
            if (elem.getEtiqueta().equalsIgnoreCase(nextElemento.getEtiqueta())) {
                continue;
            }
            if (nextElemento.getId().compareTo(Elemento.ELEM_EPSILON.getId()) == 0) {
                primeros.add(nextElemento.getId());
                continue;
            }
            if (nextElemento.esTerminal()) {
                primeros.add(nextElemento.getId());
                continue;
            }
            do {
                if (elem.getEtiqueta().equalsIgnoreCase(nextElemento.getEtiqueta())) {
                    //Rompe la recursividad por la izquierda para el cálculo de los primeros.
                    break;
                }
                TreeSet<Integer> temp = primeros(nextElemento);
                if (temp.remove(Elemento.ELEM_EPSILON.getId())) {
                    primeros.addAll(temp);
                    nextElemento = itElem.next();
                } else {
                    primeros.addAll(temp);
                    break;
                }
            } while (nextElemento != null);
        }
        return primeros;
    }

    public HashMap<Integer, Elemento> getElementosLR() {
        return elementosLR;
    }

    public TreeSet<Produccion> getProducciones() {
        return producciones;
    }

    public ArrayList<Produccion> getProducciones(Integer izq){
        ArrayList<Produccion> izqProd = new ArrayList<>();
        Iterator<Produccion> itProd = this.producciones.iterator();
        while (itProd.hasNext()) {
            Produccion next = itProd.next();
            if(next.getEncabezado().compareTo(izq)==0){
                izqProd.add(next);
            }
        }
        return izqProd;
    }
    
    public Elemento getElemento(Integer key){
        return this.elementosLR.get(key);
    }
    
    public Produccion getProduccion(Integer key){
        Iterator<Produccion> itProd = this.producciones.iterator();
        while (itProd.hasNext()) {
            Produccion next = itProd.next();
            if(next.getId().compareTo(key)==0){
                return next;
            }
        }        
        return null;
    }

}