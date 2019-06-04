/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import static elementos.GameObject.TILE_SIZE;
import static elementos.GameObject.loadSprite;
import static elementos.Player.getSprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ASUS
 */
public class Outrider extends GameObjectMobile {

    private final BufferedImage[] standing = {getSprite(1, 0)};

    private final Animation standingAnimation = new Animation(standing,4);
    private Animation animation = standingAnimation;
    private static BufferedImage spriteSheet;
    
    private Random random;
    private int choose;
    private int life;
    private int segundos = 0;
    public static final int COIN = 20;
    
    public Outrider( int x, int y) {
        super( x, y);
        setHeight(32);
        setWidth(32);
        life = 100;
        random = new Random();
        choose = 0;
        velX = 0;
        velY = 0;
    }


    @Override
    public void tick() {
        x += velX;
        y += velY;
        shoot();
        segundos++;
    }

    private void shoot(){
        choose = random.nextInt(10);
        if(choose == 1){
            velX = (random.nextInt(4 +4) - 4);
            velY = (random.nextInt(4 +4) - 4);
        }
        if(segundos == 300){
            map.addGameObject(new BallOfFire(x+32, y+32,1));
            map.addGameObject(new BallOfFire(x, y,2));
            map.addGameObject(new BallOfFire(x, y,3));
            map.addGameObject(new BallOfFire(x+32, y+32,4));
            segundos =0;
        }
    }
    @Override
    public void render(Graphics g) {
        drawLifeLine(g);
        g.drawImage(animation.getSprite(), x, y, null);
    }

    public void drawLifeLine(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y-10,width,5);
        if(getLife()>50){
            g.setColor(new Color(135, 233, 94));
        }else if(getLife()>0 && getLife()<=50){
            g.setColor(Color.red);
        }
        g.fillRect(x,y-10,(getLife()/width)*10,5);
        g.setColor(Color.BLACK);
        g.drawRect(x,y-10,width,5);
    }
    
    public void move(){
//        velX += (velX*2)-1;
//        velY += (velY*2)-1;
//        
        y += (velY*6) * -1;
        velY *= -1;
        
        
        x += (velX*6) * -1;
        velX *= -1;
    }
    
    public void loseLife(int amount){        
        setLife(getLife()- amount);
    }
    
    @Override
    public boolean checkCollision(GameObject tempObject){
        return getBigBounds().intersects(tempObject.getBounds());
    }
    
    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("outrider.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    public Rectangle getBigBounds(){
        return new Rectangle(x-16, y-16, width*2, height*2);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    private Map map;

    public void setMap(Map map) {
        this.map = map;
    }

    
}
