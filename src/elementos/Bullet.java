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

    public static int BULLET_SPEED = 10;
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
