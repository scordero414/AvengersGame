/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyElements;

import elementos.GameObject;
import elementos.Gem;
import elementos.Handler;
import elementos.Player;
import elementos.World;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import vistas.Ventana;

/**
 *
 * @author ASUS
 */
public class KeyInput extends KeyAdapter{
    
    
    private Handler handler;
    private Ventana ventana;
    public KeyInput(Handler handler,Ventana ventana) {
        this.handler = handler;
        this.ventana = ventana;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.getMaps().get(0).getGameObjects().size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            
            if (tempObject instanceof Player) {
                if(key == KeyEvent.VK_W) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setUp(true);
                if(key == KeyEvent.VK_S) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setDown(true);  
                if(key == KeyEvent.VK_A) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setLeft(true);
                if(key == KeyEvent.VK_D) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setRight(true);
                //if(key == KeyEvent.VK_E) handler.setPressE(true);
            }
        }
        if(key == KeyEvent.VK_ESCAPE){
            ventana.getFrame().setVisible(false);
            ventana.crearVistaMenu();
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.getMaps().get(0).getGameObjects().size(); i++) {
            GameObject tempObject = handler.getMaps().get(0).getGameObjects().get(i);
            
            if (tempObject instanceof Player) {
                if(key == KeyEvent.VK_W) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setUp(false);
                if(key == KeyEvent.VK_S) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setDown(false);  
                if(key == KeyEvent.VK_A) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setLeft(false);
                if(key == KeyEvent.VK_D) handler.getMaps().get(Handler.LEVEL-1).getPlayerOfMap().setRight(false);
//                if(key == KeyEvent.VK_E) handler.setPressE(false);
            }
        }
    }
    
    
    
}
