/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Arma del jugador.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Shield extends GameObjectMobile{

    /**
     * Velocida a la que viaja el escudo o arma.
     */
    public static int BULLET_SPEED = 40;
    /**
     * Ruta de la imagen del arma.
     */
    private BufferedImage bulletImage = loadSprite("bala.png");
    private boolean goBack;
    private Player player;
    private boolean wentBack;
    /**
     * Cantidad de vida que le quita a los enemigos al usarla.
     */
    final int DAMAGE = 15;
    
    public Shield(int x, int y,int xMouse, int yMouse) {
        super(x, y);
        this.goBack = false;
        setWidth(32);
        setHeight(32);
        setVelX((xMouse - getX()) / BULLET_SPEED);
        setVelY((yMouse - getY()) / BULLET_SPEED);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(goBack){
            goBack();
        }
        //checkBulletHitsGameObjects();
    }
    
    @Override
    public boolean checkCollision(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImage, x, y, null);
//        g.setColor(Color.yellow);
//        g.fillOval(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(),getHeight());
    }
    
    /**
     * Se conoce la direccion a la que fue lanzada el arma,
     * y dependiendo se devuelve hacia el jugador mismo.
     */
    public void goBack(){
        
        if(player.getX() < x){
            velX = -7;
        }
        if(player.getX() > x){
            velX = 7;
        }
        if(player.getY() < y){
            velY = -7;
        }
        if(player.getY() > y){
            velY = 7;
        }
        if(player.getBounds().intersects(getBounds())){
            wentBack = true;
            player.setHaveShield(true);
        }
    }

    public boolean isGoBack() {
        return goBack;
    }

    public void setGoBack(boolean goBack) {
        this.goBack = goBack;
    }

    
    public boolean isWentBack() {
        return wentBack;
    }

    public void setWentBack(boolean wentBack) {
        this.wentBack = wentBack;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    
    
}
