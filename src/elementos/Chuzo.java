/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import static elementos.Sprite.loadSprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Chuzo extends Trap{
    
    private final int SIDE_CHUZO = 2;
    private final int HIGHER_CHUZO = 1;
    private BufferedImage sideChuzo = loadSprite("Chuzos2.png");
    private BufferedImage higherChuzo = loadSprite("Chuzos1.png");
    private int typeChuzo;
    public static final int DAMAGE = 3;
    
    public Chuzo(Handler handler, int x, int y,int typeChuzo) {
        super(handler, x, y);
        this.typeChuzo = typeChuzo;
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void tick() {
        
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
