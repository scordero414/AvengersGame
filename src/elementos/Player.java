/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static javafx.scene.text.Font.font;

/**
 * Jugador principal. (Capitan America)
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public class Player extends GameObjectMobile {

    /**
     * Velocidad a la que se mueve le jugador.
     */
    private static final int VEL = 8;
    /**
     * Frames o Imagenes determinadas a la posición
     * del jugador.
     */
    private final BufferedImage[] walkingUp = {getSprite(0, 3), getSprite(1, 3), getSprite(2, 3)};
    private final BufferedImage[] walkingDown = {getSprite(0, 0), getSprite(1, 0), getSprite(2, 0)};
    private final BufferedImage[] walkingLeft = {getSprite(0, 1), getSprite(1, 1), getSprite(2, 1)}; 
    private final BufferedImage[] walkingRight = {getSprite(0, 2), getSprite(1, 2), getSprite(2, 2)};
    private final BufferedImage[] standing = {getSprite(1, 0)};

    // These are animation states
    private Animation walkUpAnimation = new Animation(walkingUp, 6);
    private Animation walkDownAnimation = new Animation(walkingDown, 6);
    private Animation walkLeftAnimation = new Animation(walkingLeft, 6);
    private Animation walkRightAnimation = new Animation(walkingRight,6);
    private final Animation standingAnimation = new Animation(standing,6);

    // This is the actual animation
    private Animation animation = standingAnimation;
    private static BufferedImage spriteSheet;
    private BufferedImage scoreImage = loadSprite("star.png");
    
    /**
     * Vida del jugador;
     */
    private int life;
    private int ammo;
    /**
     * Indicar si se el jugador ha recogido la gema.
     */
    private boolean obtGem;
    private boolean haveGem;
    private Gem gemObtained;
    /**
     * Indicar la cantidad de vida del jugador.
     */
    private boolean isAlive;
    private boolean itsFull;
    /**
     * Direcciones del movimiento del jugador.
     */
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    /**
     * Puntaje que se lleva a lo largo del juego.
     */
    private int score;
    /**
     * Indicar si se tiene arma, en este caso
     * el escudo del Cap.
     */
    private boolean haveShield;
    private Map map;

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Player( int x, int y) {
        super( x, y);
        setWidth(32);
        setHeight(32);
        score = 0;
        isAlive = true;
        obtGem = false;
        haveGem = false;
        haveShield = true;
        this.life = 100;
        this.ammo = 0;
        itsFull = false;
    }


    @Override
    public void tick() {
        x += velX;
        y += velY; 
        
        map.checkCollisionInTheMap();
        move();
        animation.stop();
    }

    /**
     * Dependiendo del movimiento del jugador,
     * se determina su animacion e imagen.
     */
    public void move(){
        
        if(isDown())  {
            velY = VEL;
            animation = walkDownAnimation;
            animation.start();
        }else if(!isUp()){
            velY = 0;
        }
        if(isUp()){
            velY = -VEL;
            animation = walkUpAnimation;
            animation.start();
        }else if(!isDown()){
            velY = 0;
        }
        if(isLeft()){
            velX = -VEL;
            animation = walkLeftAnimation;
            animation.start();
        }else if(!isRight()){
            velX = 0;
        }
        if(isRight()){
            velX = VEL;
            animation = walkRightAnimation;
            animation.start();
        }else if(!isLeft()){
            velX = 0;
        }
        animation.update();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getSprite(), x, y, null);
    }

    /**
     * Se modifica la vida, restando la cantidad
     * dependiendo al enemigo por el que haya sido atacado.
     * @param amount 
     */
    public void loseLife(int amount){        
        setLife(getLife()- amount);
        if(life <=0){
            isAlive = false;
        }
    }
    public void loseAmmo(int amount){        
        setAmmo(getAmmo()- amount);
    }
    
    /**
     * Se dibuja la vida del jugador, la cual es 100.
     * @param g
     * @param width
     * @param height 
     */
    public void drawLifeLine(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width-300,height,600,20);
        g.setColor(new Color(135, 233, 94));
        g.fillRect(width-300,height,getLife()*6,20);
        g.setColor(Color.BLACK);
        g.drawRect(width-300,height,600,20);
        String life = getLife()+"/100";
        g.drawString(life, width-life.length(), height+15);
    }
    
    public void drawAmmoLine(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width-300,height,600,20);
        g.setColor(new Color(84, 169, 242));
        g.fillRect(width-300,height,getAmmo()*6,20);
        g.setColor(Color.BLACK);
        g.drawRect(width-300,height,600,20);
        String ammo = getAmmo()+"/100";
        g.drawString(ammo, width-ammo.length(), height+15);
    }
    
    /**
     * Se dibujan los objetos propios del mapa.
     * @param g
     * @param width
     * @param height 
     */
    public void drawInventary(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width+5,height+5,50,50);
        g.setColor(Color.BLACK);
        g.fillRect(width+10,height+10,40,40);
        g.drawRect(width+5,height+5,50,50);
        if(isHaveGem()){
            g.drawImage(gemObtained.getImageOfGem(),width+15,height+15,null);
        }
    }
    
    /**
     * Se dibuja el cuadro del puntaje y mientras éste
     * va incrementando se va dibujando sincronicamente,
     * @param g
     * @param width
     * @param height 
     */
    public void drawScore(Graphics g,int width,int height){
        g.setColor(Color.GRAY);
        g.fillRect(width+5,height+5,100,50);
        g.setColor(Color.BLACK);
        g.fillRect(width+10,height+10,90,40);
        g.drawRect(width+5,height+5,100,50);
        g.drawImage(scoreImage, width+13, height+13,null);
        g.setColor(Color.white);
        g.setFont( new Font( "Tahoma", Font.BOLD, 32 ) );
        g.drawString(score+"", width+45, height+45 );
    }
    
    /**
     * Se verifica con un inserts si el jugador ha
     * colisionado con algún otro objeto.
     * @param tempObject
     * @return 
     */
    @Override
    public boolean checkCollision(GameObject tempObject){
        return getBounds().intersects(tempObject.getBounds());
    }
    
    /**
     * Se modifica el poder, aumentando y teniendo mas escudo o poder.
     * @param amount 
     */
    public void increaseShield(int amount){
        setAmmo(getAmmo()+ amount);
    }
    
    /**
     * Se verifica si la vida ya está a 100, y si no es así,
     * se incrementa a su cantidad.
     * @param amount 
     */
    public void increaseLife(int amount){
        setLife(getLife()+ amount);
        if(getLife()>100){
            setLife(100);
        }
        
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getWidth(), getHeight());
    }
    
    /**
     * Se determina que se guardo la gema en el cofre.
     * @param gem 
     */
    public void saveGem(Gem gem){
        gemObtained = gem;
        
    }
    public void stop(){
        velX *= -1;
        velY *=-1;
        x += velX;
        y += velY;
    }

    /**
     * Se determina la ruta del Sprite del jugador,
     * el capitan america en este caso.
     * @param xGrid
     * @param yGrid
     * @return 
     */
    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("capAmerica.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setHaveGem(boolean haveGem) {
        this.haveGem = haveGem;
    }

    public boolean isHaveGem() {
        return haveGem;
    }

    public Gem getGemObtained() {
        return gemObtained;
    }

    public void setGemObtained(Gem gemObtained) {
        this.gemObtained = gemObtained;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isItsFull() {
        return itsFull;
    }

    public void setItsFull(boolean itsFull) {
        this.itsFull = itsFull;
    }

    public boolean isHaveShield() {
        return haveShield;
    }

    public void setHaveShield(boolean haveShield) {
        this.haveShield = haveShield;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addCoin(int amountCoin) {
       score += amountCoin;
    }
    
    
    
    
}
