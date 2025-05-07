// ConfigLoader.java
import org.json.*;
import java.nio.file.*;
import java.io.IOException;

public class ConfigLoader {
    public static Config loadConfig(String path) throws IOException, JSONException { //might throw JSONException if the JSON is invalid
        
        String content = Files.readString(Path.of(path)); // Read the file content as a string
        JSONObject json = new JSONObject(content); // Parse the string into a JSONObject

        //save the values from the JSON file into the Config object
        String input = json.getString("input"); 
        String output = json.optString("output", null);
        boolean display = json.optBoolean("display", false);
        JSONArray operations = json.getJSONArray("operations");

        return new Config(input, output, display, operations);
    }
}