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
    
    public Handler() {
        this.maps = new ArrayList<>();
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
    
    public void tick(){
        Map map = maps.get(0);
        for (int i = 0; i < map.getGameObjects().size(); i++) {
            GameObject tempObject =  map.getGameObjects().get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        Map map = maps.get(0);
        for (int i = 0; i < map.getGameObjects().size(); i++) {
            GameObject tempObject = map.getGameObjects().get(i);
            tempObject.render(g);  
        }
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

   
}
