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
import mouseElements.MouseInput;
import vistas.GameView;
import vistas.MenuView;
import vistas.Ventana;

/**
 *
 * @author ASU  S
 */
public class World extends Canvas implements Runnable,Container{
    
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean isRunning;
    private Handler handler;
    private LectorMapa lectorMapa;
    private Camera camera;
    public static int LEVEL = 0;
    private boolean inGame;
    private Container container;
    
    public World() throws IOException  {
        this.container = container;
        camera = new Camera(0, 0);
        handler = new Handler();
        Ventana ventana = new Ventana(1280, 960, "AvengersGame",this,camera);
        this.lectorMapa = new LectorMapaTxt();
        Map map1 = lectorMapa.leerMapa(); 
        map1.setContainer(this);
        handler.addMap(map1);
        
        start();
        this.addKeyListener(ventana);
        this.addMouseListener(new MouseInput(handler, camera,this));
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
        camera.tick(handler.getMap().getPlayerOfMap());
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
        handler.render(g);
        
        g2d.translate(camera.getX(), camera.getY());
        for (int i = 0; i < (handler.getGameObjectsOfMap()).size(); i++) {
            GameObject tempObject = handler.getGameObjectsOfMap().get(i);
            if(tempObject instanceof Player){
                handler.getMap().getPlayerOfMap().drawLifeLine(g,(getWidth()/2),getHeight()-60);
                handler.getMap().getPlayerOfMap().drawAmmoLine(g,(getWidth()/2),getHeight()-30);
                handler.getMap().getPlayerOfMap().drawInventary(g,0,0);
            }
        }
        
        g.dispose();
        bs.show();
    }

    public void keyPressed(int estateMove,boolean decision){
        handler.getMap().keyPressed(estateMove, decision);
    }

    public void keyReleased(int estateMove,boolean decision){
        handler.getMap().keyReleased(estateMove, decision);
    }
    
    public void mousePressed(boolean decision) {
        handler.getMap().mousePressed(decision);
    }
    
    public void mouseReleased(boolean decision) {
        handler.getMap().mouseReleased(decision);
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public void refresh() {
        container.refresh();
    }

    public void setContainer(Container container) {
        this.container = container;
    }
    
    
    
}
