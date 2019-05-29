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
import javafx.scene.input.MouseButton;

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
    
    
    
    @Override
    public void mousePressed(MouseEvent e){
        int xMouse = (int)(e.getX() + camera.getX());
        int yMouse = (int)(e.getY() + camera.getY());
        if(e.getButton() == MouseEvent.BUTTON1){
            for (int i = 0; i < handler.getMap().getGameObjects().size(); i++) {
               GameObject tempObject = handler.getMap().getGameObjects().get(i);

               if (tempObject instanceof Player) {
                   handler.getMap().getGameObjects().add(new Bullet(tempObject.getX()+32, tempObject.getY()+32,xMouse,yMouse));

               }
               
           }
        }
        
        if(e.getButton()==MouseEvent.BUTTON3){
            handler.setPressBuuton2(true);
        }
        
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON3){
                handler.setPressBuuton2(false);
            }
    }
    
}
