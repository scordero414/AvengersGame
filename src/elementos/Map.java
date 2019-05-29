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
public class Map {

    private Handler handler;
    private ArrayList<GameObject> gameObjects;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private Player player;

    public Map(Handler handler) {
        gameObjects = new ArrayList<>();
        this.handler = handler;
//        setWidth(1920);
//        setHeight(1600);
        
    }

    
    public void tick() {
        player = getPlayerOfMap();
        player.setUp(up);
        player.setDown(down);
        player.setLeft(left);
        player.setRight(right);
        if(playerDIed()){
            handler.isInGame();
        }
        ArrayList<Chainsaw> chainsaws = getChainsawsOfMap();
        checkCollisionInTheMap(player,chainsaws);
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
        //handler.tick();
    }

    public void render(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);  
        }
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
    public ArrayList<Chainsaw> getChainsawsOfMap(){
        ArrayList<Chainsaw> chainsaws = new ArrayList<>();
        for (int j = 0; j < gameObjects.size(); j++) {
            GameObject get = gameObjects.get(j);
            if(get instanceof Chainsaw){
                chainsaws.add((Chainsaw) get);
            }
        }
        return chainsaws;
    }
    public ArrayList<Block> getBlocksOfMap(){
        ArrayList<Block> blocks = new ArrayList<>();
        for (int j = 0; j < gameObjects.size(); j++) {
            GameObject get = gameObjects.get(j);
            if(get instanceof Block){
                blocks.add((Block) get);
            }
        }
        return blocks;
    }
    public void removetLasersOfMap(){
       
        for (int j = 0; j < gameObjects.size(); j++) {
            GameObject get = gameObjects.get(j);
            if(get instanceof LaserBeam){
                gameObjects.remove(get);
            }
        }
    }
    public Gem getGemOfMap(){
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject get = gameObjects.get(i);
            if(get instanceof Gem){
                return (Gem)get;
            }
        }
        return null;
    }
    public void checkCollisionInTheMap(Player player,ArrayList<Chainsaw> chainsaws){
        checkPlayerCollisionChainsaw(chainsaws, player);
        
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            if(tempObject instanceof Gem){
                if(player.checkCollision(tempObject)){
                    if(handler.isPressBuuton2()){
                        player.setHaveGem(true);
                        if(player.isHaveGem()){
                            player.saveGem((Gem) tempObject);
                            gameObjects.remove(tempObject);                        
                        }
                        if(player.getGemObtained() != null){
                            gameObjects.add(new Gem(player.getX(), player.getY()));
                        }  
                    }else{
                        player.setHaveGem(false);
                    }
                }   
            }
            if(tempObject instanceof Chest){
                Chest chest = (Chest) tempObject;
                 if(player.getGemObtained() != null){
                    if(player.getGemObtained().getBounds().intersects(((Chest) tempObject).getBounds())){
                        chest.setItsFull(true);
                        player.setHaveGem(false);
                        gameObjects.remove(getGemOfMap());
                    }else{
                        chest.setItsFull(false);
                    }
                    
                    if(chest.isItsFull()){
                        removetLasersOfMap();
                    }
                }
            }
            if(tempObject instanceof Block){
                Block block = (Block) tempObject;
                if(player.checkCollision(block)){
                    player.stop();
                }
                checkChainsawCollisionBlock(chainsaws, block);
            }
            if(tempObject instanceof LaserBeam){
                
                LaserBeam laser = (LaserBeam) tempObject;
                if(player.checkCollision(laser)){
                    determineWhatToDecrease(player,LaserBeam.DAMAGE);
                }
            }
            if(tempObject instanceof Chuzo){
                Chuzo chuzo = (Chuzo) tempObject;
                if(player.checkCollision(chuzo)){

                    determineWhatToDecrease(player,Chuzo.DAMAGE);
                }
            }
            if(tempObject instanceof ShieldRecharge){
                ShieldRecharge shield = (ShieldRecharge) tempObject;
                if(player.checkCollision(shield)){
                    if(player.getAmmo() >= 0  && player.getAmmo() <100){
                        player.increaseShield(ShieldRecharge.RECHARGE);
                        gameObjects.remove(shield);
                    }
                }
            }
            if(tempObject instanceof Bullet){
                Bullet bullet = (Bullet) tempObject;
                checkBulletCollisionBlock(getBlocksOfMap(), bullet);
            }
            
        }
        
    }
    
    private boolean playerDIed(){
        return !player.isIsAlive();
    }
    private void checkBulletCollisionBlock(ArrayList<Block> blocks, Bullet bullet){
        for(int j = 0; j<blocks.size();j++){
            if(bullet.checkBulletHitsGameObjects(blocks.get(j))){
                gameObjects.remove(bullet);
            }
        }
    }
    private void checkChainsawCollisionBlock( ArrayList<Chainsaw> chainsaws,Block block){
        for(int j = 0; j<chainsaws.size();j++){
            if(chainsaws.get(j).checkCollision(block)){
                chainsaws.get(j).moveBack();
            }
        }
    }
    private void checkPlayerCollisionChainsaw( ArrayList<Chainsaw> chainsaws,Player player){
        for(int j = 0; j<chainsaws.size();j++){
            if(player.getBounds().intersects(chainsaws.get(j).getBounds())){
                //gameObjects.remove(chainsaws.get(j));
                determineWhatToDecrease(player,Chainsaw.DAMAGE);
            }
        }
    }
    private void determineWhatToDecrease(Player player,int amountDamage){
        if(player.getAmmo() > 0){
            player.loseAmmo(amountDamage);
            if(player.getAmmo() <0){
                player.setAmmo(0);
            }
        }else if(player.getLife()>0){
           player.loseLife(amountDamage);
        }else if(player.getLife() <= 0){
            handler.getGameObjectsOfMap().remove(player);
        }
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