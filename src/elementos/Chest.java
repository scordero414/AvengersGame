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
public class Chest extends GameObjectStatic{
    private boolean itsFull;
    private BufferedImage chestImage = loadSprite("cofre.png");
    public Chest( int x, int y) {
        super( x, y);
        itsFull = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(chestImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isItsFull() {
        return itsFull;
    }

    public void setItsFull(boolean itsFull) {
        this.itsFull = itsFull;
    }
    
}
