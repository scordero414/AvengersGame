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
 * Chuzos o espinas que causan daño al jugador.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Chuzo extends Trap{
    
    /**
     * Posición de los chuzos en el mapa.
     */
    private final int SIDE_CHUZO = 2;
    private final int HIGHER_CHUZO = 1;
    /**
     * Rutas de las imágenes de los chuzos.
     */
    private BufferedImage sideChuzo = loadSprite("Chuzos2.png");
    private BufferedImage higherChuzo = loadSprite("Chuzos1.png");
    private int typeChuzo;
    /**
     * Cantidad de vida que se disminuye.
     */
    public static final int DAMAGE = 3;
    
    public Chuzo(int x, int y,int typeChuzo) {
        super( x, y);
        this.typeChuzo = typeChuzo;
        setWidth(32);
        setHeight(32);
    }

    

    @Override
    public void render(Graphics g) {
        switch(typeChuzo){
            case SIDE_CHUZO:
                g.drawImage(sideChuzo, x, y, null);
            break;
            case HIGHER_CHUZO:
                g.drawImage(higherChuzo, x, y, null);
            break;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
    
}
