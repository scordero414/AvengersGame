/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOelements;

import elementos.Map;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author ASUS
 */
public interface LectorMapa {
    public abstract Map leerMapa() throws IOException;
}
