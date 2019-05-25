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

/**
 *
 * @author ASUS
 */
public class Player extends GameObject{

    private static final int VEL = 8;
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

    public Player(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        this.handler = handler;
        setWidth(32);
        setHeight(32);
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
//       g.setColor(Color.yellow);
//       g.fillRect(x, y, getWidth(), getHeight());
        g.drawImage(animation.getSprite(), x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(),getY(),getWidth(), getHeight());
    }
    
}
