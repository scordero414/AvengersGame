/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOelements;

import elementos.Player;
import java.io.FileNotFoundException;

/**
 *
 * @author ASUS
 */
public interface JSONWriter {
    public void write(Player maps, String filename) throws FileNotFoundException;
}
