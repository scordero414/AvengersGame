/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

/**
 * Objetos que causan daño al jugador,
 * enemigos estaticos
 * @author Sebastian Cordero Ramirez
 * @author Daniel Gutierrez Duque
 * @since 20191905
 * @version 1.0
 */
public abstract class Trap extends EnemyStatic{
    
    public Trap(int x, int y) {
        super( x, y);
    }
    
}
