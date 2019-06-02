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
public class Outrider extends GameObjectMobile{

    public Outrider( int x, int y) {
        super( x, y);
    }


    @Override
    public void tick() {
        x += velX;
        y += velY;
     }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
}
