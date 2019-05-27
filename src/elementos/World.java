/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import IOelements.LectorMapa;
import IOelements.LectorMapaTxt;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import keyElements.KeyInput;
import mouseElements.MouseInput;
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
    private LectorMapa lectorMapa;
    private Camera camera;
    public static int LEVEL = 0;
    
    public World() throws IOException  {
        Ventana ventana = new Ventana(1280, 960, "AvengersGame",this);
        
        handler = new Handler();
        camera = new Camera(0, 0);
        this.lectorMapa = new LectorMapaTxt(handler);
        
        Map map1;
        System.out.println("Hola");
        map1 = lectorMapa.leerMapa();        
        handler.addMap(map1);
        start();
        this.addKeyListener(new KeyInput(handler,ventana));
        this.addMouseListener(new MouseInput(handler, camera));
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            World world = new World();
        } catch (IOException ex) {
            System.out.println("Hj");
        }
        
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
            JOptionPane.showMessageDialog(null, "Paailas las tyangas");
        }
    }

    public void tick() {
        for (int i = 0; i < (handler.getGameObjectsOfMap()).size(); i++) {
            GameObject tempObject = handler.getGameObjectsOfMap().get(i);
            if(tempObject instanceof Player){
                camera.tick(tempObject);
            }
        }
        if(!handler.getMaps().isEmpty())
            handler.getMaps().get(0).tick();
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //draw
        g2d.translate(-camera.getX(), -camera.getY());
        
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1600);
        handler.getMaps().get(0).render(g);
        handler.render(g);
        
        g2d.translate(camera.getX(), camera.getY());
        for (int i = 0; i < (handler.getGameObjectsOfMap()).size(); i++) {
            GameObject tempObject = handler.getGameObjectsOfMap().get(i);
            if(tempObject instanceof Player){
                handler.getMaps().get(0).getPlayerOfMap().drawLifeLine(g,(getWidth()/2),getHeight()-60);
                handler.getMaps().get(0).getPlayerOfMap().drawAmmoLine(g,(getWidth()/2),getHeight()-30);
            }
        }
        
        
        g.dispose();
        bs.show();
    }

   

    
}
