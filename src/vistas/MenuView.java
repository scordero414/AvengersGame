/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;

/**
 *
 * @author dgutierrezd
 */
public class MenuView extends javax.swing.JDialog {

    Ventana ventana;
    
    /**
     * Accion hechas por el usuario en esta vista.
     */
    int estado;
    
    /**
     * Creates new form MenuView
     */
    public MenuView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lPlayNow = new javax.swing.JLabel();
        bPlay = new javax.swing.JButton();
        lExit = new javax.swing.JLabel();
        lInstrucciones = new javax.swing.JLabel();
        bInstructions = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AvengersIcon.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CaptainAmericaShield (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IronMan (1).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, -1));

        lPlayNow.setFont(new java.awt.Font("Chilanka", 1, 18)); // NOI18N
        lPlayNow.setForeground(new java.awt.Color(1, 1, 1));
        lPlayNow.setText("Play Now");
        lPlayNow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lPlayNow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lPlayNowMouseClicked(evt);
            }
        });
        getContentPane().add(lPlayNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 90, 20));

        bPlay.setForeground(new java.awt.Color(145, 150, 153));
        bPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonPlayNow.png"))); // NOI18N
        bPlay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bPlay.setBorderPainted(false);
        bPlay.setContentAreaFilled(false);
        bPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayActionPerformed(evt);
            }
        });
        getContentPane().add(bPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 100, 50));

        lExit.setFont(new java.awt.Font("Chilanka", 1, 18)); // NOI18N
        lExit.setForeground(new java.awt.Color(1, 1, 1));
        lExit.setText("Exit");
        lExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lExitMouseClicked(evt);
            }
        });
        getContentPane().add(lExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, -1, -1));

        lInstrucciones.setFont(new java.awt.Font("Chilanka", 1, 18)); // NOI18N
        lInstrucciones.setForeground(new java.awt.Color(1, 1, 1));
        lInstrucciones.setText("Instructions");
        lInstrucciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lInstrucciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lInstruccionesMouseClicked(evt);
            }
        });
        getContentPane().add(lInstrucciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        bInstructions.setForeground(new java.awt.Color(145, 150, 153));
        bInstructions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonInstructions.png"))); // NOI18N
        bInstructions.setBorderPainted(false);
        bInstructions.setContentAreaFilled(false);
        bInstructions.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bInstructions.setPreferredSize(new java.awt.Dimension(128, 44));
        bInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInstructionsActionPerformed(evt);
            }
        });
        getContentPane().add(bInstructions, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        bExit.setForeground(new java.awt.Color(145, 150, 153));
        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonExit.png"))); // NOI18N
        bExit.setBorderPainted(false);
        bExit.setContentAreaFilled(false);
        bExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bExit.setPreferredSize(new java.awt.Dimension(54, 44));
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        getContentPane().add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, -1, -1));

        jLabel7.setFont(new java.awt.Font("Purisa", 1, 60)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 42, 42));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AvengersTitle_1.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 170));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AvengersPixel.jpg"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        estado = 3;
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayActionPerformed
        estado = 1;
        dispose();
    }//GEN-LAST:event_bPlayActionPerformed

    private void bInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInstructionsActionPerformed
        estado = 2;
        dispose();
    }//GEN-LAST:event_bInstructionsActionPerformed

    private void lPlayNowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lPlayNowMouseClicked
        estado = 1;
        dispose();
    }//GEN-LAST:event_lPlayNowMouseClicked

    private void lInstruccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lInstruccionesMouseClicked
        estado = 2;
        dispose();
    }//GEN-LAST:event_lInstruccionesMouseClicked

    private void lExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lExitMouseClicked
        estado = 3;
        System.exit(0);
    }//GEN-LAST:event_lExitMouseClicked

    public int getEstado() {
        return estado;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bInstructions;
    private javax.swing.JButton bPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lExit;
    private javax.swing.JLabel lInstrucciones;
    private javax.swing.JLabel lPlayNow;
    // End of variables declaration//GEN-END:variables
}
