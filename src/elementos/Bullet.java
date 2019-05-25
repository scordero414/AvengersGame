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
public class Bullet extends GameObject{

    public static int BULLET_SPEED = 40;
    private Handler handler;
    
    public Bullet(Handler handler, int x, int y,int xMouse, int yMouse) {
        super(handler, x, y);
        this.handler = handler;
        setVelX((xMouse - getX()) / BULLET_SPEED);
        setVelY((yMouse - getY()) / BULLET_SPEED);
        setWidth(12);
        setHeight(12);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        for (int i = 0; i < (handler.getMaps().get(0).getGameObjects()).size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            if(tempObject instanceof Block){
                if(getBounds().intersects(((Block) tempObject).getBounds())){
                    handler.getMaps().get(0).getGameObjects().remove(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, getWidth(),getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(),getHeight());
    }
    
}
