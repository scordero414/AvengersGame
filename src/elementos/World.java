/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import IOelements.JSONPlayerReader;
import IOelements.JSONPlayerWriter;
import IOelements.JSONReader;
import IOelements.JSONWriter;
import IOelements.MapReaderTxt;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mouseElements.MouseInput;
import vistas.MenuView;
import vistas.Ventana;
import IOelements.MapReader;

/**
 * Clase control, se inicia el juego.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class World extends Canvas implements Runnable,Container{
    
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean isRunning;
    private Handler handler;
    private MapReader lectorMapa;
    private JSONWriter jsonWriter;
    private JSONReader jsonReader;
    private Camera camera;
    private boolean inGame;
    private Container container;
    /**
     * Rutas de los mapas.
     */
    private final Path rutaMapa1 = Paths.get("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\JavaAvengersV2\\Mapas\\mapa_1.txt");
    private final Path rutaMapa2 = Paths.get("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\JavaAvengersV2\\Mapas\\mapa_2.txt");
    
    public World() {
        this.container = container;
        Ventana ventana = new Ventana(1280, 960, "AvengersGame",this);
        initWorld(ventana);
        start();
    }
    
    /**
     * Se crean los nuevos objetos y se añaden a esta clase.
     * @param ventana
     * @throws IOException 
     */
    public void initWorld(Ventana ventana) {
        camera = new Camera(0, 0);
        jsonWriter = new JSONPlayerWriter();
        jsonReader = new JSONPlayerReader();
        handler = new Handler();
        ventana.setCamera(camera);
        this.lectorMapa = new MapReaderTxt();
        Map map1 = null; 
        Map map2 = null;
        try {
            map1 = lectorMapa.leerMapa(rutaMapa1);
            map2 = lectorMapa.leerMapa(rutaMapa2); 
        } catch (IOException ex) {
            System.out.println("ERROR:  Hay algun problema con la lectura del mapa.");;
        }
        map1.setContainer(this);
        handler.addMap(map1);
        map2.setContainer(this);
        map2.addGameObject(map1.getPlayerOfMap());
        handler.addMap(map2 );
        this.addKeyListener(ventana);
        this.addMouseListener(new MouseInput(handler, camera,this));
    }
    public void deleteWorld(){
        camera = null;
        handler = null;
        //handler.getMaps().clear();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World();
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
    
    /**
     * Se indica que esta corriendo el juego, y
     * se crea un nuevo hilo.
     */
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

    /**
     * Se indica el movimiento principalmente de la camara.
     */
    public void tick() {
        if( handler != null){
           handler.tick(); 
           if(camera != null){
                camera.tick(handler.getMap().getPlayerOfMap());
                
            }
           if(handler.getMap().isNextLevel()){
               Handler.setLEVEL(Handler.LEVEL+1);
               System.out.println("Next level");
           }
        }
        
    }

    /**
     * Se crean las graficas del juego.
     */
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
        
        if(camera != null && handler != null){
            
            handler.render(g);

            g2d.translate(camera.getX(), camera.getY());
            for (int i = 0; i < (handler.getGameObjectsOfMap()).size(); i++) {
                GameObject tempObject = handler.getGameObjectsOfMap().get(i);
                if(tempObject instanceof Player){
                    handler.getMap().getPlayerOfMap().drawLifeLine(g,(getWidth()/2),getHeight()-60);
                    handler.getMap().getPlayerOfMap().drawAmmoLine(g,(getWidth()/2),getHeight()-30);
                    handler.getMap().getPlayerOfMap().drawInventary(g,0,0);
                    handler.getMap().getPlayerOfMap().drawScore(g,getWidth()-115,0);
                }
            }
        }
        g.dispose();
        bs.show();
    }

    public void keyPressed(int estateMove,boolean decision){
        if(handler != null){
            handler.getMap().keyPressed(estateMove, decision);
        }
    }

    public void keyReleased(int estateMove,boolean decision){
        if(handler != null){
            handler.getMap().keyReleased(estateMove, decision);
        }
    }
    public void mousePressed(boolean decision) {
        if(handler != null){
            handler.getMap().mousePressed(decision);
        }
        
    }
    
    public void mouseReleased(boolean decision) {
        if(handler != null){
            handler.getMap().mouseReleased(decision);
        }
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

    

    public Handler getHandler() {
        return handler;
    }


    public void setJsonReader(JSONPlayerReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public JSONWriter getJsonWriter() {
        return jsonWriter;
    }

    public JSONReader getJsonReader() {
        return jsonReader;
    }
    
    
    
    
    
}
