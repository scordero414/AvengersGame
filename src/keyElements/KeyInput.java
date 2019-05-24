/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyElements;

import elementos.GameObject;
import elementos.Handler;
import elementos.Player;
import elementos.World;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author ASUS
 */
public class KeyInput extends KeyAdapter{
    
    
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.getMaps().get(0).getGameObjects().size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            
            if (tempObject instanceof Player) {
                if(key == KeyEvent.VK_W) handler.setUp(true);
                if(key == KeyEvent.VK_S) handler.setDown(true);  
                if(key == KeyEvent.VK_A) handler.setLeft(true);
                if(key == KeyEvent.VK_D) handler.setRight(true);
            }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.getMaps().get(0).getGameObjects().size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            
            if (tempObject instanceof Player) {
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);  
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
            }
        }
    }
    
    
    
}
