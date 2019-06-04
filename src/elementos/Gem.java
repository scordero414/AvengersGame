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
 * Gema a recoger para poder pasar los niveles.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Gem extends GameObjectStatic{
    
    /**
     * Arreglo donde se almacenan las imagenes de las gemas.
     */
    protected BufferedImage[] gemImage = new BufferedImage[6];
    /**
     * Nivel donde se carga la gema determinada.
     */
    private int level;
    public Gem( int x, int y) {
        super( x, y);
        initImages();
        level = Handler.LEVEL-1;
    }

    
    @Override
    public void render(Graphics g) {
        g.drawImage(gemImage[level], x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * Se almacenan las imagenes de las gemas al arreglo.
     */
    private void initImages(){
        gemImage[0] = loadSprite("gemaRoja.png");
        gemImage[1] = loadSprite("gemaAmarilla.png");
        gemImage[2] = loadSprite("gemaAzul.png");
        gemImage[3] = loadSprite("gemaMorada.png");
        gemImage[4] = loadSprite("gemaVerde.png");
        gemImage[5] = loadSprite("gemaDorada.png");
    }
    
    /**
     * Se obtiene la imagen de la gema,
     * conociendo el nivel en el que se está.
     * @return 
     */
    public BufferedImage getImageOfGem(){
        return gemImage[level];
    }
}
