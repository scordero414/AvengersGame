/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import elementos.World;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class Ventana {
    
    private World world;
    private JFrame frame;
    private int width;
    private int height;
    
    public Ventana(int width, int height, String name,World world){
        this.width = width;
        this.world = world;
        this.height = height;
        this.frame = new JFrame(name);
        inicializarFrame();
    }

    public void inicializarFrame(){
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.add(world);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setMundo(World world) {
        this.world = world;
    }
    
}
