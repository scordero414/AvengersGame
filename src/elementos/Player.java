/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ASUS
 */
public class Player extends GameObject{

    private static final int VEL = 8;
    private Handler handler;

    public Player(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        this.handler = handler;
        setWidth(50);
        setHeight(70);
    }

    
    
    

    @Override
    public void tick() {
        x += velX;
        y += velY;  
        move();
        
    }

    public void move(){
        
        if(handler.isDown())  {
            velY = VEL;
        }else if(!handler.isUp()){
            velY = 0;
        }
        if(handler.isUp()){
            velY = -VEL;
        }else if(!handler.isDown()){
            velY = 0;
        }
        if(handler.isLeft()){
            velX = -VEL;
        }else if(!handler.isRight()){
            velX = 0;
        }
        if(handler.isRight()){
            velX = VEL;
        }else if(!handler.isLeft()){
            velX = 0;
        }
    }
    
    @Override
    public void render(Graphics g) {
       g.setColor(Color.yellow);
       g.fillRect(x, y, getWidth(), getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(),getY(),getWidth(), getHeight());
    }
    
}
