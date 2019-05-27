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
    private Player player;

    public Map(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        gameObjects = new ArrayList<>();
        setWidth(1920);
        setHeight(1600);
        
    }

    @Override
    public void tick() {
        player = getPlayerOfMap();
        
        checkPlayerCollisionAnotherElements(player);
        handler.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.white);
//        g.fillRect(x, y, getWidth(), getHeight());
                
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

    public Player getPlayerOfMap(){
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            if(tempObject instanceof Player)
                return (Player) tempObject;
        }
        return null;
    }
    
    public void checkPlayerCollisionAnotherElements(Player player){
        
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            
            if(tempObject instanceof Block){
                Block block = (Block) tempObject;
                if(player.checkCollision(block)){
                    player.stop();
                }
            }
            if(tempObject instanceof LaserBeam){
                LaserBeam laser = (LaserBeam) tempObject;
                if(player.checkCollision(laser)){
                    if(player.getAmmo() > 0){
                        player.loseAmmo(LaserBeam.DAMAGE);
                    }else if(player.getLife()>0){
                       player.loseLife(LaserBeam.DAMAGE);
                    }else if(player.getLife() <= 0){
                        handler.getGameObjectsOfMap().remove(player);
                    }
                }
            }
            if(tempObject instanceof Chuzo){
                Chuzo chuzo = (Chuzo) tempObject;
                if(player.checkCollision(chuzo)){
                    
                    if(player.getAmmo() > 0){
                        player.loseAmmo(Chuzo.DAMAGE);
                        if(player.getAmmo() <0){
                            player.setAmmo(0);
                        }
                    }else if(player.getLife()>0){
                       player.loseLife(Chuzo.DAMAGE);
                    }else if(player.getLife() <= 0){
                        handler.getGameObjectsOfMap().remove(player);
                    }
                }
            }
            if(tempObject instanceof ShieldRecharge){
                ShieldRecharge shield = (ShieldRecharge) tempObject;
                if(player.checkCollision(shield)){
                    if(player.getAmmo() >= 0  && player.getAmmo() <100){
                        player.increaseShield(ShieldRecharge.RECHARGE);
                        handler.getGameObjectsOfMap().add(new Floor(handler, shield.getX(), shield.getY()));
                        handler.getGameObjectsOfMap().remove(shield);
                        handler.getGameObjectsOfMap().remove(player);
                        handler.getGameObjectsOfMap().add(player);
                    }
                }
            }
        }
    }
    
}
