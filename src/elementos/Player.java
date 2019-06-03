/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static javafx.scene.text.Font.font;

/**
 *
 * @author ASUS
 */
public class Player extends GameObjectMobile{

    private static final int VEL = 8;
    private final BufferedImage[] walkingUp = {getSprite(0, 3), getSprite(1, 3), getSprite(2, 3)};
    private final BufferedImage[] walkingDown = {getSprite(0, 0), getSprite(1, 0), getSprite(2, 0)};
    private final BufferedImage[] walkingLeft = {getSprite(0, 1), getSprite(1, 1), getSprite(2, 1)}; 
    private final BufferedImage[] walkingRight = {getSprite(0, 2), getSprite(1, 2), getSprite(2, 2)};
    private final BufferedImage[] standing = {getSprite(1, 0)};

    // These are animation states
    private Animation walkUpAnimation = new Animation(walkingUp, 2);
    private Animation walkDownAnimation = new Animation(walkingDown, 2);
    private Animation walkLeftAnimation = new Animation(walkingLeft, 2);
    private Animation walkRightAnimation = new Animation(walkingRight,2);
    private final Animation standingAnimation = new Animation(standing,2);

    // This is the actual animation
    private Animation animation = standingAnimation;
    private static BufferedImage spriteSheet;
    
    private int life;
    private int ammo;
    private boolean obtGem;
    private boolean haveGem;
    private Gem gemObtained;
    private boolean isAlive;
    private boolean itsFull;
    
    
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    
    
    private boolean haveShield;
    private Map map;

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Player( int x, int y) {
        super( x, y);
        setWidth(32);
        setHeight(32);
        isAlive = true;
        obtGem = false;
        haveGem = false;
        haveShield = true;
        this.life = 100;
        this.ammo = 0;
        itsFull = false;
    }


    @Override
    public void tick() {
        x += velX;
        y += velY; 
        map.checkCollisionInTheMap();
        move();
        animation.stop();
    }

    public void move(){
        
        if(isDown())  {
            velY = VEL;
            animation = walkDownAnimation;
            animation.start();
        }else if(!isUp()){
            velY = 0;
        }
        if(isUp()){
            velY = -VEL;
            animation = walkUpAnimation;
            animation.start();
        }else if(!isDown()){
            velY = 0;
        }
        if(isLeft()){
            velX = -VEL;
            animation = walkLeftAnimation;
            animation.start();
        }else if(!isRight()){
            velX = 0;
        }
        if(isRight()){
            velX = VEL;
            animation = walkRightAnimation;
            animation.start();
        }else if(!isLeft()){
            velX = 0;
        }
        animation.update();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getSprite(), x, y, null);
    }

    
    public void loseLife(int amount){        
        setLife(getLife()- amount);
        if(life <=0){
            isAlive = false;
        }
    }
    public void loseAmmo(int amount){        
        setAmmo(getAmmo()- amount);
    }
    
    public void drawLifeLine(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width-300,height,600,20);
        g.setColor(new Color(135, 233, 94));
        g.fillRect(width-300,height,getLife()*6,20);
        g.setColor(Color.BLACK);
        g.drawRect(width-300,height,600,20);
        String life = getLife()+"/100";
        g.drawString(life, width-life.length(), height+15);
    }
    
    public void drawAmmoLine(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width-300,height,600,20);
        g.setColor(new Color(84, 169, 242));
        g.fillRect(width-300,height,getAmmo()*6,20);
        g.setColor(Color.BLACK);
        g.drawRect(width-300,height,600,20);
        String ammo = getAmmo()+"/100";
        g.drawString(ammo, width-ammo.length(), height+15);
    }
    
    public void drawInventary(Graphics g,int width,int height){
        
        g.setColor(Color.GRAY);
        g.fillRect(width+5,height+5,100,100);
//        g.setColor(new Color(84, 169, 242));
//        g.fillRect(width-300,height,getAmmo()*6,20);
        g.setColor(Color.BLACK);
        g.fillRect(width+10,height+10,90,90);
        g.drawRect(width+5,height+5,100,100);
        if(isHaveGem()){
            g.drawImage(gemObtained.getImageOfGem(),width+40,height+40,null);
        }
//        String ammo = getAmmo()+"/100";
//        g.drawString(ammo, width-ammo.length(), height+15);
    }
    
    public boolean checkCollision(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
    public void increaseShield(int amount){
        setAmmo(getAmmo()+ amount);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getWidth(), getHeight());
    }
    public void saveGem(Gem gem){
        gemObtained = gem;
        
    }
    public void stop(){
        velX *= -1;
        velY *=-1;
        x += velX;
        y += velY;
    }

    
    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("capAmerica.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setHaveGem(boolean haveGem) {
        this.haveGem = haveGem;
    }

    public boolean isHaveGem() {
        return haveGem;
    }

    public Gem getGemObtained() {
        return gemObtained;
    }

    public void setGemObtained(Gem gemObtained) {
        this.gemObtained = gemObtained;
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

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isItsFull() {
        return itsFull;
    }

    public void setItsFull(boolean itsFull) {
        this.itsFull = itsFull;
    }

    public boolean isHaveShield() {
        return haveShield;
    }

    public void setHaveShield(boolean haveShield) {
        this.haveShield = haveShield;
    }
    
    
    
}
