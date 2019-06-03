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
public class Map implements Container{

    private ArrayList<GameObject> gameObjects;
    private Player player;
    private ArrayList<Chainsaw> chainsaws;
    private ArrayList<Outrider> outriders;
    private ArrayList<Block> blocks;
    private ArrayList<Bullet> bullets;
    private Container container;
    public Map() {
        gameObjects = new ArrayList<>();
    }

    
    public void tick() {
        if(player == null ||chainsaws == null){
            player = getPlayerOfMap();
            player.setMap(this);
            chainsaws = getChainsawsOfMap();
            blocks = getBlocksOfMap();
        }
        outriders = getOutridersOfMap();
        bullets = getBulletsOfMap();
        for (int i = 0; i < outriders.size(); i++) {
            Outrider tempOutrider = outriders.get(i);
            tempOutrider.setMap(this);
        }
        checkCollisionInTheMap();
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject =  gameObjects.get(i);
            if(tempObject instanceof Floor){
                continue;
            }if(tempObject instanceof GameObjectStatic){
                continue;
            }else{
                GameObjectMobile tempObjectMobile = (GameObjectMobile) tempObject;
                tempObjectMobile.tick();
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
    
    public void removeGameObject(GameObjectStatic tempGameObject){
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
    public ArrayList<Outrider> getOutridersOfMap(){
        ArrayList<Outrider> outriders = new ArrayList<>();
        for (int j = 0; j < gameObjects.size(); j++) {
            GameObject get = gameObjects.get(j);
            if(get instanceof Outrider){
                outriders.add((Outrider) get);
            }
        }
        return outriders;
    }
    public ArrayList<Bullet> getBulletsOfMap(){
        ArrayList<Bullet> bullets = new ArrayList<>();
        for (int j = 0; j < gameObjects.size(); j++) {
            GameObject get = gameObjects.get(j);
            if(get instanceof BallOfFire){
                bullets.add((Bullet) get);
            }
        }
        return bullets;
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
    public void checkCollisionInTheMap(){
        checkPlayerCollisionChainsaw();
        checkBallFireCollisionPlayer();
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            if(tempObject instanceof Gem){
                if(player.checkCollision(tempObject)){
                    if(player.isItsFull()){
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
                checkChainsawCollisionBlock(block);
                checkOutriderCollisionBlock(block);
                checkBulletCollisionBlock(block);
                
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
            if(tempObject instanceof LifeRecharge){
                LifeRecharge lifeRecharge = (LifeRecharge) tempObject;
                if(player.checkCollision(lifeRecharge)){
                    if(player.getLife() >= 0  && player.getLife() <100){
                        player.increaseLife(LifeRecharge.RECHARGE);
                        gameObjects.remove(lifeRecharge);
                    }
                }
            }
            if(tempObject instanceof Shield){
                Shield bullet = (Shield) tempObject;
                 checkBulletCollisionEnemys(bullet);
                if(bullet.isWentBack()){
                    gameObjects.remove(bullet);
                }
                checkBulletCollisionBlocks(bullet);
               
            }
//            if(tempObject instanceof Outrider){
//                //checkOutriderCollisionBlock(tempObject);
//            }
        }
        
    }
    
    private void checkBulletCollisionBlocks( Shield bullet){
        bullet.setPlayer(player);
        for(int j = 0; j<blocks.size();j++){
            if(bullet.checkCollision(blocks.get(j))){
                bullet.setGoBack(true);
            }
        }
    }
    
    private void checkBallFireCollisionPlayer(){
        for(int j = 0; j<bullets.size();j++){
            BallOfFire bullet = (BallOfFire) bullets.get(j);
            if(bullet.checkCollision(player)){
                determineWhatToDecrease(player, bullet.DAMAGE);
                gameObjects.remove(bullet);
            }
        }
    }
    private void checkBulletCollisionBlock(Block block){
        for(int j = 0; j<bullets.size();j++){
            Bullet bullet = bullets.get(j);
            if(bullet.checkCollision(block)){
                gameObjects.remove(bullet);
            }
        }
    }
    private void checkBulletCollisionEnemys(Shield bullet){
        bullet.setPlayer(player);
        for(int j = 0; j<outriders.size();j++){
            if(bullet.checkCollision(outriders.get(j))){
                outriders.get(j).loseLife(bullet.DAMAGE);
                bullet.setGoBack(true);
                if(outriders.get(j).getLife() <=0 ){
                    gameObjects.remove(outriders.get(j));
                }       
            }
        }
    }
    private void checkChainsawCollisionBlock(Block block){
        for(int j = 0; j<chainsaws.size();j++){
            if(chainsaws.get(j).checkCollision(block)){
                chainsaws.get(j).moveBack();
            }
        }
    }
    private void checkOutriderCollisionBlock(Block block){
        for(int j = 0; j<outriders.size();j++){
            if(outriders.get(j).checkCollision(block)){
                outriders.get(j).move();
            }
        }
    }
    
    
    private void checkPlayerCollisionChainsaw(){
        for(int j = 0; j<chainsaws.size();j++){
            if(player.checkCollision(chainsaws.get(j))){
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
            gameObjects.remove(player);
            container.refresh();
        }
    }

    public void keyPressed(int estateMove,boolean decision){
        switch(estateMove){
            case 1:
                player.setUp(decision);
            break;
            case 2:
                player.setDown(decision);
            break;
            case 3:
                player.setLeft(decision);
            break;
            case 4:
                player.setRight(decision);
            break;
        }
    }
    
    void keyReleased(int estateMove, boolean decision) {
        switch(estateMove){
            case 1:
                player.setUp(decision);
            break;
            case 2:
                player.setDown(decision);
            break;
            case 3:
                player.setLeft(decision);
            break;
            case 4:
                player.setRight(decision);
            break;
        }
    }
    
    public void mousePressed(boolean decision) {
        player.setItsFull(decision);
    }
    
    public void mouseReleased(boolean decision) {
        player.setItsFull(decision);
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    
    @Override
    public void refresh() {
        container.refresh();
    }
}