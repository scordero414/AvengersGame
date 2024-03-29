/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author educacion
 */
public abstract class Sprite {
   protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int velX;
    protected int velY;
    protected static BufferedImage spriteSheet;
    protected static final int TILE_SIZE = 32;
    
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
        this.width = 20;
        this.height = 20;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("Assets/" + file));
        } catch (IOException e) {
            System.out.println("Hay un error al leer la imagen.");
        }
        return sprite;
    }
    
    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("ImagePlayer.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
   

    
//    public boolean isOutOfGameSection(){
//        
//        Rectangle bounds = container.getBorders();
//        
//        return !(  getX() >= bounds.getX() &
//                 getY() >= bounds.getY() &
//                 getX() + getWidth()  <= bounds.getX() + bounds.getWidth() &
//                 getY() + getHeight() <= bounds.getY() + bounds.getHeight());
//    }
    
    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
