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
public class Floor extends GameObject{
    
    private BufferedImage floor = loadSprite("fondo (2).png");
    
    public Floor( int x, int y) {
        super( x, y);
    }

   
    @Override
    public void render(Graphics g) {
        g.drawImage(floor, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    
}
