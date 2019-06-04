package IOelements;

import elementos.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


/**
 *
 * @author jimezam
 */
public class JSONReader 
{
    public Player read(String filename) 
            throws FileNotFoundException, IOException 
    {
        InputStream fis = new FileInputStream(filename);
		
        JsonReader jsonReader = Json.createReader(fis);
        
        
        JsonObject jsonObject = jsonReader.readObject();
        
        Player player = process(jsonObject);
        jsonReader.close();
        fis.close();

        return player;
    }
    
    public Player process(JsonObject jsonObject)
    {
        Player player = new Player(0, 0);

        player.setX(jsonObject.getInt("x", 0));
        player.setY(jsonObject.getInt("y", 0));
        player.setScore(jsonObject.getInt("score", 0));
        player.setAmmo(jsonObject.getInt("shield", 0));
        player.setLife(jsonObject.getInt("life", 0));
        
        return player;
    }
}
