package com.esvux.InterfaceGrafica;

import com.esvux.CompiladorGramatica.ParseException;
import com.esvux.CompiladorGramatica.ParserGramatica;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esvin
 */
public class Principal extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTab_Principal = new javax.swing.JTabbedPane();
        jPanel_acciones = new javax.swing.JPanel();
        jBtn_conexionIDE = new javax.swing.JButton();
        jBtn_compilar = new javax.swing.JButton();
        jBtn_reportes = new javax.swing.JButton();
        jBtn_conexionLEX = new javax.swing.JButton();
        jPanel_archivos = new javax.swing.JPanel();
        jBtn_nuevo = new javax.swing.JButton();
        jBtn_abrir = new javax.swing.JButton();
        jBtn_guardar = new javax.swing.JButton();
        jBtn_guardarComo = new javax.swing.JButton();
        jBtn_buscar = new javax.swing.JButton();
        jBtn_acercaDe = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxt_Consola = new javax.swing.JTextArea();
        jLbl_Estado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTab_Principal.setAutoscrolls(true);

        jBtn_conexionIDE.setBackground(new java.awt.Color(255, 255, 255));
        jBtn_conexionIDE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_IDE.png"))); // NOI18N
        jBtn_conexionIDE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_conexionIDE.setContentAreaFilled(false);
        jBtn_conexionIDE.setOpaque(true);

        jBtn_compilar.setBackground(new java.awt.Color(255, 255, 255));
        jBtn_compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_play.png"))); // NOI18N
        jBtn_compilar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_compilar.setContentAreaFilled(false);
        jBtn_compilar.setOpaque(true);
        jBtn_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_compilarActionPerformed(evt);
            }
        });

        jBtn_reportes.setBackground(new java.awt.Color(255, 255, 255));
        jBtn_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_log.png"))); // NOI18N
        jBtn_reportes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_reportes.setContentAreaFilled(false);
        jBtn_reportes.setOpaque(true);

        jBtn_conexionLEX.setBackground(new java.awt.Color(255, 255, 255));
        jBtn_conexionLEX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_LEX.png"))); // NOI18N
        jBtn_conexionLEX.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_conexionLEX.setContentAreaFilled(false);
        jBtn_conexionLEX.setOpaque(true);

        javax.swing.GroupLayout jPanel_accionesLayout = new javax.swing.GroupLayout(jPanel_acciones);
        jPanel_acciones.setLayout(jPanel_accionesLayout);
        jPanel_accionesLayout.setHorizontalGroup(
            jPanel_accionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtn_compilar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBtn_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBtn_conexionIDE, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBtn_conexionLEX, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_accionesLayout.setVerticalGroup(
            jPanel_accionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_accionesLayout.createSequentialGroup()
                .addComponent(jBtn_compilar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_conexionIDE, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_conexionLEX, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBtn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_nuevo.jpg"))); // NOI18N
        jBtn_nuevo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_nuevoActionPerformed(evt);
            }
        });

        jBtn_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_abrir.jpg"))); // NOI18N
        jBtn_abrir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_abrirActionPerformed(evt);
            }
        });

        jBtn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_guardar.jpg"))); // NOI18N
        jBtn_guardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_guardarActionPerformed(evt);
            }
        });

        jBtn_guardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_guardarComo.jpg"))); // NOI18N
        jBtn_guardarComo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_guardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_guardarComoActionPerformed(evt);
            }
        });

        jBtn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_buscar.jpg"))); // NOI18N
        jBtn_buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtn_acercaDe.setBackground(new java.awt.Color(255, 255, 255));
        jBtn_acercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/esvux/InterfaceGrafica/imgs/icono_SOL.png"))); // NOI18N
        jBtn_acercaDe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtn_acercaDe.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel_archivosLayout = new javax.swing.GroupLayout(jPanel_archivos);
        jPanel_archivos.setLayout(jPanel_archivosLayout);
        jPanel_archivosLayout.setHorizontalGroup(
            jPanel_archivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_archivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jBtn_guardar)
                .addGroup(jPanel_archivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBtn_abrir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtn_nuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jBtn_guardarComo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBtn_buscar)
            .addComponent(jBtn_acercaDe)
        );
        jPanel_archivosLayout.setVerticalGroup(
            jPanel_archivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_archivosLayout.createSequentialGroup()
                .addComponent(jBtn_nuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_abrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_guardarComo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtn_acercaDe)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Consola"));

        jTxt_Consola.setEditable(false);
        jTxt_Consola.setColumns(20);
        jTxt_Consola.setRows(4);
        jTxt_Consola.setTabSize(4);
        jScrollPane1.setViewportView(jTxt_Consola);

        jLbl_Estado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_Estado.setText("Estado: Inactivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 488, Short.MAX_VALUE)
                        .addComponent(jLbl_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbl_Estado)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_archivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTab_Principal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_acciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_archivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_acciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTab_Principal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Variables globales">
    private int cuentaArchivos;
    // </editor-fold>                            
    
    // <editor-fold defaultstate="collapsed" desc="Métodos varios">    
    public Principal() {
        initComponents();
        cuentaArchivos = 0;
        jTab_Principal.add("Sin titulo-"+cuentaArchivos+".ix",new Pestania());
        cuentaArchivos++;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    private void limpiar(){
        jTxt_Consola.setText("");
    }
    
    private void reportar(String mensaje){
        jTxt_Consola.append("\t>>> "+mensaje+"\n");
    }            
// </editor-fold>                        
    
    // <editor-fold defaultstate="collapsed" desc="Acciones sobre archivos">
    private void jBtn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_nuevoActionPerformed
        jTab_Principal.add("Sin titulo-"+cuentaArchivos+".ix",new Pestania());
        cuentaArchivos++;
    }//GEN-LAST:event_jBtn_nuevoActionPerformed

    private void jBtn_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_abrirActionPerformed
        String nuevoNombre = ((Pestania)jTab_Principal.getSelectedComponent()).abrir();
        if(nuevoNombre!=null)
            jTab_Principal.setTitleAt(jTab_Principal.getSelectedIndex(), nuevoNombre);
    }//GEN-LAST:event_jBtn_abrirActionPerformed

    private void jBtn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_guardarActionPerformed
        String nuevoNombre = ((Pestania)jTab_Principal.getSelectedComponent()).guardar();
        if(nuevoNombre!=null)
            jTab_Principal.setTitleAt(jTab_Principal.getSelectedIndex(), nuevoNombre);
    }//GEN-LAST:event_jBtn_guardarActionPerformed

    private void jBtn_guardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_guardarComoActionPerformed
        String nuevoNombre = ((Pestania)jTab_Principal.getSelectedComponent()).guardarComo();
        if(nuevoNombre!=null)
            jTab_Principal.setTitleAt(jTab_Principal.getSelectedIndex(), nuevoNombre);

    }//GEN-LAST:event_jBtn_guardarComoActionPerformed
    // </editor-fold>

    
    private void jBtn_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_compilarActionPerformed
        try {
            limpiar();
            reportar("Iniciando proceso de compilación... "+new Date().toString());
            String str[] = ((Pestania)jTab_Principal.getSelectedComponent()).getText().split("%%");
            if(str.length < 3 || str.length > 4){
                reportar("Error sintáctico, error en las delimitaciones de las secciones del archivo, %%");
                return;
            }
            String textoGramatica = str[1];
            ParserGramatica parser = new ParserGramatica(new java.io.StringReader(textoGramatica));
            parser.GRAMATICA();
            reportar("Exito!!!");
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtn_compilarActionPerformed

    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn_abrir;
    private javax.swing.JButton jBtn_acercaDe;
    private javax.swing.JButton jBtn_buscar;
    private javax.swing.JButton jBtn_compilar;
    private javax.swing.JButton jBtn_conexionIDE;
    private javax.swing.JButton jBtn_conexionLEX;
    private javax.swing.JButton jBtn_guardar;
    private javax.swing.JButton jBtn_guardarComo;
    private javax.swing.JButton jBtn_nuevo;
    private javax.swing.JButton jBtn_reportes;
    private javax.swing.JLabel jLbl_Estado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_acciones;
    private javax.swing.JPanel jPanel_archivos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTab_Principal;
    private javax.swing.JTextArea jTxt_Consola;
    // End of variables declaration//GEN-END:variables
}

// <editor-fold defaultstate="collapsed" desc="Plantilla">
// </editor-fold>                        
