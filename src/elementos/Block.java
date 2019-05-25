/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Block extends GameObject{
    private BufferedImage block = loadSprite("ParedLateral.png");
    
    public Block(Handler handler, int x, int y) {
        super(handler, x, y);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(block, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
    
}
