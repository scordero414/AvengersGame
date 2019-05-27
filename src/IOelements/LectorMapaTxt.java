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
import elementos.Chuzo;
import elementos.Floor;
import elementos.GameObject;
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
        ArrayList<GameObject> mobileObjects = new ArrayList<>();
        for(int i = 0 ; i<matriz.length ; i++){
            for(int j=0; j<matriz[0].length ; j++){
                switch(matriz[i][j]){
                    case '0':
                        GameObject floor = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor);
                    break;
                    case '1':
                        GameObject sideBlock = new Block(handler, j*32, i*32,1);
                        map.addGameObject(sideBlock);
                    break;
                    case '2':
                        GameObject higherBlock = new Block(handler, j*32, i*32,2);
                        map.addGameObject(higherBlock);
                    break;
                    case '3':
                        GameObject cornerBlock = new Block(handler, j*32, i*32,3);
                        map.addGameObject(cornerBlock);
                    break;
                    case 'l':
                        GameObject laser = new LaserBeam(handler, j*32, i*32);
                        map.addGameObject(laser);
                    break;
                    case 's':
                        GameObject floor4 = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor4);
                        GameObject shield = new ShieldRecharge(handler, j*32, i*32);
                        map.addGameObject(shield);
                    break;
                    case 'c':
                        GameObject chuzo = new Chuzo(handler, j*32, i*32,1);
                        map.addGameObject(chuzo);
                    break;
                    case 'x':
                        GameObject chuzo2 = new Chuzo(handler, j*32, i*32,2);
                        map.addGameObject(chuzo2);
                    break;
                    case 'm':
                        GameObject chainsaw = new Chainsaw(handler, j*32, i*32,1);
                        mobileObjects.add(chainsaw);
                        GameObject floor1 = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor1);
                    break;
                    case 'n':
                        GameObject chainsaw2 = new Chainsaw(handler, j*32, i*32,2);
                        mobileObjects.add(chainsaw2);
                        GameObject floor5 = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor5);
                    break;
                    case 'e':
                        GameObject outrider = new Outrider(handler, j*32, i*32);
                        mobileObjects.add(outrider);
                        GameObject floor2 = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor2);
                    break;
                    case 'p':
                        GameObject player = new Player(handler, j*32, i*32);
                        mobileObjects.add(player);
                        GameObject floor3 = new Floor(handler, j*32, i*32);
                        map.addGameObject(floor3);
                    break;
                }

            }
        }
        
        for (int i = 0; i < mobileObjects.size(); i++) {
            GameObject get = mobileObjects.get(i);
            map.addGameObject(get);
        }
   }
}
