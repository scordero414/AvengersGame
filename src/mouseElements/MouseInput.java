/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouseElements;

import elementos.Shield;
import elementos.Camera;
import elementos.GameObject;
import elementos.GameObjectStatic;
import elementos.Handler;
import elementos.Player;
import elementos.World;
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
    private World world;

    public MouseInput(Handler hanlder, Camera camera,World world) {
        this.handler = hanlder;
        this.camera = camera;
        this.world = world;
    }
    
    
    
    @Override
    public void mousePressed(MouseEvent e){
        int xMouse = (int)(e.getX() + camera.getX());
        int yMouse = (int)(e.getY() + camera.getY());
        if(e.getButton() == MouseEvent.BUTTON1){
            for (int i = 0; i < handler.getMap().getGameObjects().size(); i++) {
               GameObject tempObject = handler.getMap().getGameObjects().get(i);

               if (tempObject instanceof Player) {
                   if(((Player) tempObject).isHaveShield()){
                       handler.getMap().getGameObjects().add(new Shield(tempObject.getX()+32, tempObject.getY()+32,xMouse,yMouse));
                       ((Player) tempObject).setHaveShield(false);
                   }
                   

               }
               
           }
        }
        
        if(e.getButton()==MouseEvent.BUTTON3){
            world.mousePressed(true);
        }
        
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON3){
            world.mouseReleased(false);
        }
    }
    
}
