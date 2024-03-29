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
public class Ventana {
    
    private World world;
    private JFrame frame;
    private int width;
    private int height;
    private int seg;
    
    public Ventana(int width, int height, String name,World world){
        this.width = width;
        this.world = world;
        this.height = height;
        this.frame = new JFrame(name);
        inicializarFrame();
        initPanel();
        crearVistaMenu();
    }

    public void inicializarFrame(){
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.add(world);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }
    public void initPanel(){
        
    }
    
    public void decidirBotonesMenu(MenuView menuView) {
        switch(menuView.getEstado()) {
            case 1:
                frame.setVisible(true);
                LoadView loadView = new LoadView(frame, false);
                
            break;
            case 2:
                InstructionsView instructionsView = new InstructionsView(frame, true);
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
       MenuView menuView = new MenuView(frame, true);
       decidirBotonesMenu(menuView);
    }

    public void setMundo(World world) {
        this.world = world;
    }

    public JFrame getFrame() {
        return frame;
    }
    
    
    
    
}
