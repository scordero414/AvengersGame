/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOelements;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetSerializer;
import elementos.Block;
import elementos.Floor;
import elementos.GameObject;
import elementos.Handler;
import elementos.Map;
import elementos.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class LectorMapaTxt implements LectorMapa {
    
    private int width = 60;
    private int height = 50;
    private final Path ruta = Paths.get("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\JavaAvengersV2\\Mapas\\mapa_1.txt");
    private Handler handler;
    public LectorMapaTxt(Handler handler) {
        this.handler =  handler;
    }
    
    
    @Override
    public Map leerMapa() throws IOException{
        
        Map map = new Map(handler, 0, 0);
        
        char [][] matriz  = new char[width][width];
        
        BufferedReader reader = Files.newBufferedReader(ruta, Charset.defaultCharset() );
        String line = null;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(" ");
            //recorremos el arrar de string
            for (int i = 0; i<values.length; i++) {
            //se obtiene el primer caracter de el arreglo de strings
                matriz[index][i] = values[i].charAt(0);

            }
            index++;
        }
        System.out.println(matriz[0][2]);
        dibujarMatriz(map, matriz);
        reader.close();
        return map;
    }
    
    public void dibujarMatriz(Map map,char[][] matriz){
        for(int i = 0 ; i<matriz.length ; i++){
            for(int j=0; j<matriz[0].length ; j++){
                switch(matriz[i][j]){
                    case '1':
                        GameObject block = new Block(handler, j*32, i*32);
                        map.addGameObject(block);
                    break;
//                    case '0':
//                        GameObject floor = new Floor(handler, j*32, i*32);
//                        map.addGameObject(floor);
//                    break;
                    
                }

            }
        }
   }
}
