/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import static elementos.Sprite.loadSprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class LaserBeam extends Trap{
    
    private BufferedImage fondoLaser = loadSprite("fondoLaser.png");
    public static final int DAMAGE = 100;
    public LaserBeam(Handler handler, int x, int y) {
        super(handler, x, y);
        setWidth(10);
        setHeight(32);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fondoLaser, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth()+16, getHeight());
    }
    
}
