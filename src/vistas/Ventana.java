/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import elementos.Camera;
import elementos.Container;
import elementos.GameObject;
import elementos.Player;
import elementos.World;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.applet.AppletAudioClip;

/**
 *
 * @author ASUS
 */
public class Ventana extends javax.swing.JFrame implements KeyListener,Container{
    
    private World world;
    private AudioClip soundtrack;
    private Camera camera;
    private int width;
    private int height;
    private int seg;
    
    public Ventana(int width, int height, String name,World world,Camera camera){
        this.width = width;
        this.world = world;
        this.height = height;
        this.camera = camera;
        inicializarFrame(name);
        crearVistaMenu();
        world.setContainer(this);
//        soundtrack = java.applet.Applet.newAudioClip(getClass().getResource("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\JavaAvengersV2\\src\\Sounds\\music.wav"));
//        soundtrack.play();
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
                generarSonidoFondo();
                soundtrack.loop();
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
    
    public void keyPressed(KeyEvent evt){
        int key = evt.getKeyCode();
        
        if(key == KeyEvent.VK_W) world.keyPressed(1, true);
        if(key == KeyEvent.VK_S) world.keyPressed(2, true);
        if(key == KeyEvent.VK_A) world.keyPressed(3, true);
        if(key == KeyEvent.VK_D) world.keyPressed(4, true);
        
        
//        if(key == KeyEvent.VK_ESCAPE){
//            ventana.getFrame().setVisible(false);
//            ventana.crearVistaMenu();
//        }
    }
    
    @Override
    public void keyReleased(KeyEvent evt){
        int key = evt.getKeyCode();
        
        if(key == KeyEvent.VK_W) world.keyPressed(1, false);
        if(key == KeyEvent.VK_S) world.keyPressed(2, false);
        if(key == KeyEvent.VK_A) world.keyPressed(3, false);
        if(key == KeyEvent.VK_D) world.keyPressed(4, false);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    
    public void generarSonidoFondo() {
        soundtrack = java.applet.Applet.newAudioClip(getClass().getResource("/Sounds/music.wav"));
        
    }

    @Override
    public void refresh() {
        DieView dieView = new DieView(this, true);
        dieView.setLocationRelativeTo(this);
        dieView.setResizable(false);
        dieView.setVisible(true);
        
    }
    
}
