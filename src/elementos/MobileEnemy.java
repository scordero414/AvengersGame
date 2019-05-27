/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ASUS
 */
public abstract class MobileEnemy extends Enemy implements Runnable{

    protected Thread thread;
    public MobileEnemy(Handler handler, int x, int y) {
        super(handler, x, y);
        thread = new Thread(this);
    }

    public abstract void run();
    
}
