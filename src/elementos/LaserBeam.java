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
 * Enemigo estático del jugador, rayo laser.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class LaserBeam extends Trap{
    
    /**
     * Arreglo donde se almacenan las dos imagenes
     * del rayo laser, vertical y horizontalmente.
     */
    private BufferedImage[] fondoLaser = new BufferedImage[2];
    /**
     * Cantida de daño que causa.
     * Quita el 100% de la vida.
     */
    public static final int DAMAGE = 100;
    
    private int typeLaser;
    public LaserBeam( int x, int y,int typeLaser) {
        super( x, y);
        setWidth(10);
        setHeight(32);
        initLaserImages();
        this.typeLaser = typeLaser;
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fondoLaser[typeLaser-1], x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth()+16, getHeight());
    }
    
    /**
     * Se guardan las dos imágenes del rayo laser,
     * vertical y horizontal, al arreglo.
     */
    public void initLaserImages(){
        fondoLaser[0] = loadSprite("fondoLaser.png");
        fondoLaser[1] = loadSprite("fondoLaser2.png");
    }
}
