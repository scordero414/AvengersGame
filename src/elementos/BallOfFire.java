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
 * Objeto lanzado por los enemigos (outriders)
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class BallOfFire extends Bullet{

    /**
     * Ruta para la imagen de la bola de fuego.
     */
    private BufferedImage ballFireImage = loadSprite("bolaFuego.png");
    /**
     * Conocer la dirección de movimiento de la bola de fuego.
     */
    private int typeBall;
    /**
     * Cantidad de vida que se diminuye.
     */
    final int DAMAGE = 10;
    
    public BallOfFire(int x, int y,int typeBall) {
        super(x, y);
        setHeight(32);
        setWidth(32);
        this.typeBall = typeBall;
    }

    @Override
    public void tick() {
        
        move();
        x += velX;
        y += velY;
    }
    
    /**
     * Se determina la dirección de movimiento de la bola,
     * en este caso se dirige hacia las 4 direcciones a la misma vez.
     */
    public void move(){
        switch(typeBall){
            case 1://vertical abajo
                velX=0;
                velY = 4;
            break;
            case 2://vertical arriba
                velX=0;
                velY = -4;
            break;
            case 3://hroizontal izquierda
                velX= -4;
                velY = 0;
            break;
            case 4://Horizontal derecha
                velX= 4;
                velY = 0;
            break;
        }
    }
    @Override
    public boolean checkCollision(GameObject tempObject) {
        return getBounds().intersects(tempObject.getBounds());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ballFireImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
}
