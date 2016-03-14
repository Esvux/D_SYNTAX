package com.esvux.InterfaceGrafica;

import com.esvux.InterfaceGrafica.files.manejoArchivos;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Esvin
 */
public class Pestania extends JScrollPane{
    private File archivo;
    private JTextArea area;
    
    public Pestania() {
        super();
        archivo = null;
        area = new JTextArea();
        area.setLayout(null);
        area.setMargin(new Insets(5, 20, 5, 5));
        area.setAutoscrolls(true);
        area.setLineWrap(true);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        area.setTabSize(4);
        this.setBorder(null);
        this.setViewportView(area);
    }    
    
    public String abrir(){
        File temporal = manejoArchivos.obtenerRuta(manejoArchivos.ABRIR);
        if(temporal == null)
            return null;
        this.archivo = temporal;
        String contenido = manejoArchivos.abrirArchivo(temporal);
        area.setText(contenido);
        return temporal.getName();
    }
    
    public String guardar(){
        if(this.archivo == null)
            return guardarComo();
        manejoArchivos.guardarArchivo(this.archivo, area.getText());
        return this.archivo.getName();
    }

    public String guardarComo(){
        File temporal = manejoArchivos.obtenerRuta(manejoArchivos.GUARDAR);
        if(temporal == null)
            return null;
        this.archivo = temporal;
        manejoArchivos.guardarArchivo(temporal, area.getText());
        return temporal.getName();
    }

    public String getText(){
        return area.getText();
    }
}
