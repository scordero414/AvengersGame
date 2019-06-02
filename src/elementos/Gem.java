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
public class Gem extends GameObjectStatic{
    
    protected BufferedImage[] gemImage = new BufferedImage[6];
    private int level;
    public Gem( int x, int y) {
        super( x, y);
        initImages();
        level = Handler.LEVEL;
    }

    
    @Override
    public void render(Graphics g) {
        g.drawImage(gemImage[level], x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    private void initImages(){
        gemImage[0] = loadSprite("gemaRoja.png");
        gemImage[1] = loadSprite("gemaAmarilla.png");
        gemImage[2] = loadSprite("gemaAzul.png");
        gemImage[3] = loadSprite("gemaMorada.png");
        gemImage[4] = loadSprite("gemaVerde.png");
        gemImage[5] = loadSprite("gemaDorada.png");
    }
    
    public BufferedImage getImageOfGem(){
        return gemImage[level];
    }
}
