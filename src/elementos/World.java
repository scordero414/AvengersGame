/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import keyElements.KeyInput;
import vistas.Ventana;

/**
 *
 * @author ASU  S
 */
public class World extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean isRunning;
    private Handler handler;
    private Map map;
    
    public World() {
        Ventana ventana = new Ventana(1280, 960, "AvengersGame",this);
        start();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));         
        this.map = new Map(handler,0, 0);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World mundo = new World();
        
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }
    
    public void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    public void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //draw
        this.map.render(g);
        handler.render(g);
        
        g.dispose();
        bs.show();
    }

   

    
}
