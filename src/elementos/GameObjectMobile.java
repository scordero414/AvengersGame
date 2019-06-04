/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

/**
 * Objetos que se mueven ya se manualmente o por hilos.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public abstract class GameObjectMobile extends GameObject {
    
    /**
     * Velocidad del objeto.
     */
    protected int velX;
    protected int velY;
    
    public GameObjectMobile(int x, int y) {
        super(x, y);
        this.velX = 0;
        this.velY = 0;
    }
    
    /**
     * Actualiza 60 veces por segundo, para
     * mover a los jugadores.
     */
    public abstract void tick();
    /**
     * Verificar las colisiones de un objeto con otro.
     * @param tempObject objeto a colisionar.
     * @return Si la colision fue acertada.
     */
    public abstract boolean checkCollision(GameObject tempObject);

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    
}
