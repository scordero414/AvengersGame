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
public class Block extends GameObject{
    private final int SIDE_BLOCK = 2;
    private final int HIGHER_BLOCK = 1;
    private final int CORNER_BLOCK = 3;
    private BufferedImage sideBlock = loadSprite("ParedLateral.png");
    private BufferedImage higherBlock = loadSprite("ParedSuperior.png");
    private BufferedImage cornerBlock = loadSprite("EsquinaInferior.png");
    private int typeBlock;
    public Block(Handler handler, int x, int y,int typeBlock) {
        super(handler, x, y);
        setWidth(32);
        setHeight(32);
        this.typeBlock = typeBlock;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        switch(typeBlock){
            case SIDE_BLOCK:
                g.drawImage(higherBlock, x, y, null);
            break;
            case HIGHER_BLOCK:
                g.drawImage(sideBlock, x, y, null);
            break;
            case CORNER_BLOCK:
                g.drawImage(cornerBlock, x, y, null);
            break;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
    
}
