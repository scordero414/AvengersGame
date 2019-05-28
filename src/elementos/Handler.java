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
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean pressBuuton2;
    public static int LEVEL = 1;
    
    public Handler() {
        this.maps = new ArrayList<>();
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
    
    public void tick(){
        ArrayList<GameObject> gameObjects = getGameObjectsOfMap();
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject =  gameObjects.get(i);
            if(tempObject instanceof Floor){
                continue;
            }if(tempObject instanceof Block){
                continue;
            }if(tempObject instanceof Chuzo){
                continue;
            }if(tempObject instanceof Trap){
                continue;
            }if(tempObject instanceof ConsumableItem){
                continue;
            }if(tempObject instanceof Gem){
                continue;
            }else{
                tempObject.tick();
            }
        }
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

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPressBuuton2() {
        return pressBuuton2;
    }

    public void setPressBuuton2(boolean pressBuuton2) {
        this.pressBuuton2 = pressBuuton2;
    }

    

    
   
}
