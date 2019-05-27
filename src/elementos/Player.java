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
public class Player extends GameObject{

    private static final int VEL = 4;
    private Handler handler;
    private final BufferedImage[] walkingUp = {getSprite(0, 3), getSprite(1, 3), getSprite(2, 3)};
    private final BufferedImage[] walkingDown = {getSprite(0, 0), getSprite(1, 0), getSprite(2, 0)};
    private final BufferedImage[] walkingLeft = {getSprite(0, 1), getSprite(1, 1), getSprite(2, 1)}; 
    private final BufferedImage[] walkingRight = {getSprite(0, 2), getSprite(1, 2), getSprite(2, 2)};
    private final BufferedImage[] standing = {getSprite(1, 0)};

    // These are animation states
    private Animation walkUpAnimation = new Animation(walkingUp, 4);
    private Animation walkDownAnimation = new Animation(walkingDown, 4);
    private Animation walkLeftAnimation = new Animation(walkingLeft, 4);
    private Animation walkRightAnimation = new Animation(walkingRight,4);
    private final Animation standingAnimation = new Animation(standing,4);

    // This is the actual animation
    private Animation animation = standingAnimation;
    
    private int life;
    private int ammo;

    public Player(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        setWidth(40);
        setHeight(40);
        this.life = 100;
        this.ammo = 0;
    }


    @Override
    public void tick() {
        x += velX;
        y += velY; 
        move();
        animation.stop();
    }

    public void move(){
        
        if(handler.isDown())  {
            velY = VEL;
            animation = walkDownAnimation;
            animation.start();
        }else if(!handler.isUp()){
            velY = 0;
        }
        if(handler.isUp()){
            velY = -VEL;
            animation = walkUpAnimation;
            animation.start();
        }else if(!handler.isDown()){
            velY = 0;
        }
        if(handler.isLeft()){
            velX = -VEL;
            animation = walkLeftAnimation;
            animation.start();
        }else if(!handler.isRight()){
            velX = 0;
        }
        if(handler.isRight()){
            velX = VEL;
            animation = walkRightAnimation;
            animation.start();
        }else if(!handler.isLeft()){
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
    
    public void stop(){
        x += velX * -1;
        y += velY * -1;
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

    
    
}
