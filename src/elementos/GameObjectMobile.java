/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

/**
 *
 * @author ASUS
 */
public abstract class GameObjectMobile extends GameObject{
    
    protected int velX;
    protected int velY;
    
    public GameObjectMobile(int x, int y) {
        super(x, y);
        this.velX = 0;
        this.velY = 0;
    }
    
    public abstract void tick();

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
