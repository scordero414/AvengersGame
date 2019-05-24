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
    
    private ArrayList<GameObject> gameObjects;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    
    public Handler() {
        this.gameObjects = new ArrayList<>();
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }
    
    
    public void tick(){
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);  
        }
    }
    
    
    public void addGameObject(GameObject tempGameObject){
        gameObjects.add(tempGameObject);
    }
    
    public void removeGameObject(GameObject tempGameObject){
        gameObjects.remove(tempGameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
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
