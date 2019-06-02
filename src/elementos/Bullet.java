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
public class Bullet extends GameObject{

    public static int BULLET_SPEED = 40;
    private BufferedImage bulletImage = loadSprite("bala.png");
    
    public Bullet(int x, int y,int xMouse, int yMouse) {
        super(x, y);
        setVelX((xMouse - getX()) / BULLET_SPEED);
        setVelY((yMouse - getY()) / BULLET_SPEED);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        //checkBulletHitsGameObjects();
    }
    
    public boolean checkBulletHitsGameObjects(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(),getHeight());
    }
    
}
