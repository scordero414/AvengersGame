/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouseElements;

import elementos.Bullet;
import elementos.Camera;
import elementos.GameObject;
import elementos.Handler;
import elementos.Player;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ASUS
 */
public class MouseInput extends MouseAdapter{
    
    private Handler handler;
    private Camera camera;

    public MouseInput(Handler hanlder, Camera camera) {
        this.handler = hanlder;
        this.camera = camera;
    }
    
    
    
    public void mousePressed(MouseEvent e){
        int xMouse = (int)(e.getX() + camera.getX());
        int yMouse = (int)(e.getY() + camera.getY());
        
         for (int i = 0; i < handler.getMaps().get(0).getGameObjects().size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            
            if (tempObject instanceof Player) {
                handler.getMaps().get(0).getGameObjects().add(new Bullet(handler, tempObject.getX()+32, tempObject.getY()+32,xMouse,yMouse));
            }
        }
    }
}
