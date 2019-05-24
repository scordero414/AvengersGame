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
public class Floor extends GameObject{

    public Floor(Handler handler, int x, int y) {
        super(handler, x, y);
        setHeight(32);
        setWidth(32);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, getWidth(), getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
