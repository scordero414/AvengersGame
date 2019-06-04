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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objeto de 'sierra' que actua como enemigo del jugador.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Chainsaw extends GameObjectMobile{
    
    /**
     * Cantida de vida que se diminuye.
     */
    public static final int DAMAGE = 6;
    /**
     * Ruta de la imagen de la sierra.
     */
    private BufferedImage chainsawImage = loadSprite("Sierra.gif");
    /**
     * Determinar el lugar de la sierra en movimiento.
     */
    private final int SIDE_BLOCK = 2;
    private final int UPRIGHT_BLOCK = 1;
//    private boolean running;
    private int typeChainsaw;
    
    
    public Chainsaw( int x, int y,int typeChainsaw) {
        super( x, y);
        setWidth(32);
        setHeight(32);
        this.typeChainsaw = typeChainsaw;
        switch(typeChainsaw){
            case SIDE_BLOCK:
                velX = 6;
            break;
            case UPRIGHT_BLOCK:
                velY = 6;
            break;
        }
    }

    @Override
    public void tick(){
        x += velX;
        y += velY; 
        //moveBack();
        //remove();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(chainsawImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Determinar el recorrido de la sierra de ida y vuelta,
     * conociendo su posicio orignal y multiplicandolo por -1 para
     * que se cambie de posición.
     */
    public void moveBack(){
        switch(typeChainsaw){
            case SIDE_BLOCK:
                
                x += (velX*6) * -1;
                velX *= -1;
            break;
            case UPRIGHT_BLOCK:
                y += (velY*6) * -1;
                velY *= -1;
            break;
        }
        
    }
    
    @Override
    public boolean checkCollision(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
}
