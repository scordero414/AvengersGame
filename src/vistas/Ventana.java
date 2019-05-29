/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import elementos.World;
import java.awt.Dimension;
import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Ventana extends javax.swing.JFrame{
    
    private World world;
    private int width;
    private int height;
    private int seg;
    
    public Ventana(int width, int height, String name,World world){
        this.width = width;
        this.world = world;
        this.height = height;
        world.setVentana(this);
        inicializarFrame(name);
        crearVistaMenu();
    }

    public void inicializarFrame(String name){
        setName(name);
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        add(world);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
    }
    
    public void decidirBotonesMenu(MenuView menuView) {
        switch(menuView.getEstado()) {
            case 1:
                setVisible(true);
                LoadView loadView = new LoadView(this, false);
                
            break;
            case 2:
                InstructionsView instructionsView = new InstructionsView(this, true);
                decidirBotonesInstrucciones(instructionsView);
            break;
            case 3:
                System.exit(0);
            break;
        }
    }
    
    public void decidirBotonesInstrucciones(InstructionsView instructionsView) {
        switch(instructionsView.getEstado()) {
            case 1:
                crearVistaMenu();
            break;
        }
    }
    
    public void crearVistaMenu() {
       MenuView menuView = new MenuView(this, true);
       decidirBotonesMenu(menuView);
    }

    public void setMundo(World world) {
        this.world = world;
    }

    public JFrame getFrame() {
        return this;
    }
    
    
    
    
}
