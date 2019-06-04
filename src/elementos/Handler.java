/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * se anejan los mapas y su funcionamiento.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Handler implements Serializable{
    
    /**
     * ArrayList donde se almacenan los mapas.
     */
    private ArrayList<Map> maps;
    
    private boolean pressBuuton2;
    public static int LEVEL = 1;
    private boolean inGame;
    public Handler() {
        this.maps = new ArrayList<>();
    }
    
    /**
     * Se actualiza el mapa.
     */
    public void tick(){
       getMap().tick();
    }
    
    /**
     * Se grafica el mapa.
     * @param g 
     */
    public void render(Graphics g){
        getMap().render(g);
    }
    
    /**
     * Se obtiene el mapa y sus objetos.
     * @return 
     */
    public ArrayList<GameObject> getGameObjectsOfMap(){
        return maps.get(LEVEL-1).getGameObjects();
    }
    
    /**
     * Se añaden los mapas al arraylist.
     * @param map 
     */
    public void addMap(Map map){
        maps.add(map);
    }
    
    public void removeGameObject(Map tempGameObject){
        maps.remove(tempGameObject);
    }

    public Map getMap() {
        return maps.get(LEVEL-1);
    }

    public boolean isPressBuuton2() {
        return pressBuuton2;
    }

    public void setPressBuuton2(boolean pressBuuton2) {
        this.pressBuuton2 = pressBuuton2;
    }

    public boolean isInGame(){
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public static void setLEVEL(int LEVEL) {
        Handler.LEVEL = LEVEL;
    }
    
    
    
   
}
