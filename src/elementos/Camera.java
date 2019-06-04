/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.io.Serializable;

/**
 * Se determina el movimiento de la cámara.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Camera {
    
    /**
     * Posición horizontal de la camara.
     */
    private float x;
    /**
     * Posisión vertical de la cámara.
     */
    private float y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Permite mover la cámara y de igual manera se actualiza 60 veces x segundo.
     * @param object 
     */
    public void tick(GameObject object){
        move(object);
    }
    
    /**
     * Determina el movimiento de la cámara.
     * @param object 
     */
    private void move(GameObject object){
        if(object != null){
            x += ((object.getX() - x) - 1920/2) * 0.05f;
            y += ((object.getY() - y) - 960/2) * 0.05f;

            if(x <= 0){
                x = 0;
            }
            if(x >= 640){
                x = 640;
            }
            if(y <= 0){
                y = 0;
            }
            if(y >= 640){
                y = 640+32;
            }
        }
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
}
