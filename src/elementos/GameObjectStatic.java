/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Objetos que dependen en el mapa y sirven
 * para el buen servicio del juego.
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public abstract class GameObjectStatic extends GameObject {

    public GameObjectStatic(int x, int y) {
        super( x, y);
    }


}
