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
 * Cofre, lugar donde se deposita la gema.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Chest extends GameObjectStatic{
    
    /**
     * Determina si el cofre ya esta ocupado o no.
     */
    private boolean itsFull;
    /**
     * Ruta de la imagen del cofre.
     */
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
