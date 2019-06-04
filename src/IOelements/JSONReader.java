/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOelements;

import elementos.Player;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public interface JSONReader {
    public Player read(String filename)throws FileNotFoundException, IOException;
}
