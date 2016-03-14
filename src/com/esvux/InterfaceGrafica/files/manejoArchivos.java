package com.esvux.InterfaceGrafica.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

/**
 *
 * @author Esvin Gonzalez
 */
public class manejoArchivos {
    
    public static final int GUARDAR = 20, ABRIR = 30;
    
    public static String abrirArchivo(File ruta){
        String texto = "";
        if(ruta!=null){
            System.out.println("Abriendo: "+ruta.toString());
            try {
                FileReader fr = new FileReader(ruta);
                BufferedReader entrada = new BufferedReader(fr);
                String lineaNueva;
                while((lineaNueva = entrada.readLine()) != null){
                    texto += lineaNueva+"\n";
                }
                entrada.close();
            }catch(java.io.FileNotFoundException fnfex){
                System.out.println("Archivo no encontrado: " + fnfex.getMessage());
            }catch(java.io.IOException ioex){
                System.out.println("Archivo protegido: "+ ioex.getMessage());
            }
        }
        return texto;
    }

    public static void guardarArchivo(File ruta, String texto){
        try{
            FileWriter fw = new FileWriter(ruta);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            salida.print(texto);
            salida.close();
            bw.close();
            System.out.println("Guardando: "+ruta.getAbsolutePath());
        }catch(java.io.IOException ioex){
            System.err.println("Error al guardar el archivo: "+ioex.getMessage());
        }
    }

    public static File obtenerRuta(int funcion){
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de SYNTAX","ix","IX");
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileFilter(filtro);
        selectorArchivos.setAcceptAllFileFilterUsed(false);
        int resultado;
        if(funcion==GUARDAR)
            resultado = selectorArchivos.showSaveDialog(null);
        else if(funcion==ABRIR)
            resultado = selectorArchivos.showOpenDialog(null);
        else
            resultado = selectorArchivos.showDialog(null, JFileChooser.APPROVE_SELECTION);
        File dirTemp=null;
        if (resultado == JFileChooser.APPROVE_OPTION){
            dirTemp = selectorArchivos.getSelectedFile();
            return dirTemp;
        }
        return dirTemp;
    }

}
