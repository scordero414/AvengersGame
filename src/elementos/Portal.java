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
 * Lugar para pasar de un nivel al siguiente.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Portal extends GameObjectStatic{

    /**
     * Ruta de la imagen del portal.
     */
    private BufferedImage portalImage = loadSprite("portal.gif");
    
    private boolean checkPoint;
    
    public Portal(int x, int y) {
        super(x, y);
        setWidth(32);
        setHeight(32);
        checkPoint = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(portalImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean isCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }
}
