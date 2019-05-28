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
    
    private BufferedImage[] fondoLaser = new BufferedImage[2];
    
    public static final int DAMAGE = 100;
    private int typeLaser;
    public LaserBeam(Handler handler, int x, int y,int typeLaser) {
        super(handler, x, y);
        setWidth(10);
        setHeight(32);
        initLaserImages();
        this.typeLaser = typeLaser;
        
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fondoLaser[typeLaser-1], x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth()+16, getHeight());
    }
    public void initLaserImages(){
        fondoLaser[0] = loadSprite("fondoLaser.png");
        fondoLaser[1] = loadSprite("fondoLaser2.png");
    }
}
