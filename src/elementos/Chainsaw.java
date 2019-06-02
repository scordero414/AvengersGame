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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Chainsaw extends GameObjectMobile{
    
    public static final int DAMAGE = 6;
    private BufferedImage chainsawImage = loadSprite("Sierra.gif");
    private final int SIDE_BLOCK = 2;
    private final int UPRIGHT_BLOCK = 1;
//    private boolean running;
    private int typeChainsaw;
    
    
    public Chainsaw( int x, int y,int typeChainsaw) {
        super( x, y);
        setWidth(32);
        setHeight(32);
        this.typeChainsaw = typeChainsaw;
        switch(typeChainsaw){
            case SIDE_BLOCK:
                velX = 6;
            break;
            case UPRIGHT_BLOCK:
                velY = 6;
            break;
        }
    }

    @Override
    public void tick(){
        x += velX;
        y += velY; 
        //moveBack();
        //remove();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(chainsawImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    
    public void moveBack(){
        switch(typeChainsaw){
            case SIDE_BLOCK:
                
                x += (velX*6) * -1;
                velX *= -1;
            break;
            case UPRIGHT_BLOCK:
                y += (velY*6) * -1;
                velY *= -1;
            break;
        }
        
    }
    
    @Override
    public boolean checkCollision(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
}
