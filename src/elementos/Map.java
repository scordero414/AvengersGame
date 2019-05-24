/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Map extends Sprite{

    private Handler handler;
    private ArrayList<GameObject> gameObjects;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Map(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        gameObjects = new ArrayList<>();
        setWidth(1280);
        setHeight(960);
        
    }

    @Override
    public void tick() {
        handler.tick();
    }

    @Override
    public void render(Graphics g) {
        handler.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
    
    public void addGameObject(GameObject tempObject){
        gameObjects.add(tempObject);
    }
    
    public void removeGameObject(GameObject tempGameObject){
        gameObjects.remove(tempGameObject);
    }
    
    
}
