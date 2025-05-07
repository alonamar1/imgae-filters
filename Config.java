// Config.java
import org.json.JSONArray;

public class Config {
    private String inputPath;
    private String outputPath;
    private boolean display;
    private JSONArray operations; //array of objects in JSON format

    public Config(String inputPath, String outputPath, boolean display, JSONArray operations) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.display = display;
        this.operations = operations;
    }

    public String getInputPath() { return inputPath; }
    public String getOutputPath() { return outputPath; }
    public boolean isDisplay() { return display; }
    public JSONArray getOperations() { return operations; }
}