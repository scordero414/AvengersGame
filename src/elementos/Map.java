/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ASUS
 */
public class Map extends Sprite{

    private Handler handler;

    public Map(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        this.handler.addGameObject(new Player(handler, 100, 100));
        setWidth(1280);
        setHeight(960);
    }

    @Override
    public void tick() {
        handler.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1280,960);
        handler.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
    
}
