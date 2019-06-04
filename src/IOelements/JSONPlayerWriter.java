package IOelements;

import elementos.Chainsaw;
import elementos.Map;
import elementos.Player;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.JsonWriter;


/**
 *
 * @author jimezam
 */
public class JSONPlayerWriter implements JSONWriter{
    
    
    
    @Override
    public void write(Player maps, String filename) 
            throws FileNotFoundException
    {
	OutputStream os = new FileOutputStream(filename);
        JsonWriter jsonWriter = Json.createWriter(os);

        JsonObject personsBuilder = process(maps);
        
        jsonWriter.writeObject(personsBuilder);
        jsonWriter.close();    
    }
    
    public JsonObject process(Player player)
    {
//        JsonArrayBuilder chainsaws = Json.createArrayBuilder();
//        JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();
        JsonObjectBuilder newPlayer = Json.createObjectBuilder();

//        for (Chainsaw chainsaw : map.getChainsawsOfMap()) {
//                chainsaws.add((JsonValue) chainsaw);
//        }

//        addressBuilder.add("street", person.getAddress().getStreet())
//                      .add("city", person.getAddress().getCity())
//                      .add("province", person.getAddress().getProvince());

        newPlayer.add("life", player.getLife())
                     .add("shield", player.getAmmo())
                     .add("score", player.getScore())
                     .add("x", player.getX())
                    .add("y", player.getY());

        JsonObject personJsonObject = newPlayer.build();
        
        return personJsonObject;
    }
}
