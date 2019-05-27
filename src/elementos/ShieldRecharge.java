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
public class ShieldRecharge extends ConsumableItem{

    public static final int RECHARGE = 10;
    
    public ShieldRecharge(Handler handler, int x, int y) {
        super(handler, x, y);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void tick() {
            
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(84, 169, 242));
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(),getHeight());
    }
    
}
