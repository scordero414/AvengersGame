/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Handler {
    
    private ArrayList<Map> maps;
    
    private boolean pressBuuton2;
    public static int LEVEL = 1;
    
    public Handler() {
        this.maps = new ArrayList<>();
    }
    
    public void tick(){
        ArrayList<GameObject> gameObjects = getGameObjectsOfMap();
       
    }
    
    public void render(Graphics g){
        ArrayList<GameObject> gameObjects = getGameObjectsOfMap();
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);  
        }
    }
    
    public ArrayList<GameObject> getGameObjectsOfMap(){
        return maps.get(LEVEL-1).getGameObjects();
    }
    public void addMap(Map map){
        maps.add(map);
    }
    
    public void removeGameObject(Map tempGameObject){
        maps.remove(tempGameObject);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public boolean isPressBuuton2() {
        return pressBuuton2;
    }

    public void setPressBuuton2(boolean pressBuuton2) {
        this.pressBuuton2 = pressBuuton2;
    }

    

    
   
}
