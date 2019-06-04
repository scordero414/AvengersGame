/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import static elementos.GameObject.loadSprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Poción o aumento de vida, para recargar la energía y vida
 * del jugador.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class LifeRecharge extends ConsumableItem{

    /**
     * Cantidad de vida que se aumenta al tomarla.
     */
    public static final int RECHARGE = 10;
    /**
     * Ruta de la imagen.
     */
    private BufferedImage potionImage = loadSprite("pocionVerde.png");
    
    public LifeRecharge(int x, int y) {
        super(x, y);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void render(Graphics g) {
         g.drawImage(potionImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
         return new Rectangle(x, y, getWidth(),getHeight());
    }
    
}
