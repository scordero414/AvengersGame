/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOelements;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetSerializer;
import elementos.Block;
import elementos.Chainsaw;
import elementos.Chest;
import elementos.Chuzo;
import elementos.Floor;
import elementos.GameObject;
import elementos.GameObjectMobile;
import elementos.GameObjectStatic;
import elementos.Gem;
import elementos.Handler;
import elementos.LaserBeam;
import elementos.Map;
import elementos.Outrider;
import elementos.Player;
import elementos.ShieldRecharge;
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
    
    
    @Override
    public Map leerMapa() throws IOException{
        
        Map map = new Map();
        
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
        ArrayList<GameObjectMobile> mobileObjects = new ArrayList<>();
        for(int i = 0 ; i<matriz.length ; i++){
            for(int j=0; j<matriz[0].length ; j++){
                GameObject floor;
                switch(matriz[i][j]){
                    case '0':
                        floor = new Floor(j*32, i*32);
                        map.addGameObject(floor);
                    break;
                    case '1':
                        GameObjectStatic sideBlock = new Block( j*32, i*32,1);
                        map.addGameObject(sideBlock);
                    break;
                    case '2':
                        GameObjectStatic higherBlock = new Block(j*32, i*32,2);
                        map.addGameObject(higherBlock);
                    break;
                    case '3':
                        GameObjectStatic cornerBlock = new Block( j*32, i*32,3);
                        map.addGameObject(cornerBlock);
                    break;
                    case 'l':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectStatic laser = new LaserBeam( j*32, i*32,2);
                        map.addGameObject(laser);                        
                    break;
                    case 'k':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectStatic laser2 = new LaserBeam(j*32, i*32,1);
                        map.addGameObject(laser2);                        
                    break;
                    case 's':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectStatic shield = new ShieldRecharge(j*32, i*32);
                        map.addGameObject(shield);
                    break;
                    case 'c':
                        GameObjectStatic chuzo = new Chuzo(j*32, i*32,1);
                        map.addGameObject(chuzo);
                    break;
                    case 'x':
                        GameObjectStatic chuzo2 = new Chuzo( j*32, i*32,2);
                        map.addGameObject(chuzo2);
                    break;
                    case 'm':
                        GameObjectMobile chainsaw = new Chainsaw(j*32, i*32,1);
                        mobileObjects.add(chainsaw);
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                    break;
                    case 'n':
                        GameObjectMobile chainsaw2 = new Chainsaw( j*32, i*32,2);
                        mobileObjects.add(chainsaw2);
                        floor = new Floor(j*32, i*32);
                        map.addGameObject(floor);
                    break;
                    case 'e':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectMobile outrider = new Outrider( j*32, i*32);
                        mobileObjects.add(outrider);
                    break;
                    case 'p':
                        GameObjectMobile player = new Player( j*32, i*32);
                        mobileObjects.add(player);
                        floor = new Floor(j*32, i*32);
                        map.addGameObject(floor);
                    break;
                    case 'g':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectStatic gem = new Gem( j*32, i*32);
                        map.addGameObject(gem);
                    break;
                    case 'h':
                        floor = new Floor( j*32, i*32);
                        map.addGameObject(floor);
                        GameObjectStatic chest = new Chest(j*32, i*32);
                        map.addGameObject(chest);
                    break;
                }

            }
        }
        
        for (int i = 0; i < mobileObjects.size(); i++) {
            GameObjectMobile get = mobileObjects.get(i);
            map.addGameObject(get);
        }
   }
}
