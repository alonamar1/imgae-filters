import java.io.*;

public class Main {
    public static void main(String[] args) {
        String configPath;

        // Check if the user provided a config path as an argument
        if (args.length == 2 && args[0].equals("--config")) {
            configPath = args[1];
        } else {
            System.out.println("No config path given, using default: config.json");
            configPath = "config.json"; // Default config path
        }

        try {
            Config config = ConfigLoader.loadConfig(configPath); // Load the config from the specified path
            ImageEditor editor = new ImageEditor(config); // Create an ImageEditor instance with the loaded config
            editor.applyOperations(); // Apply the operations specified in the config

            
            if (config.isDisplay()) {
                ImageEditor.displayImage(editor.getImage(), "Filtered Image");
            }
            
            if (config.getOutputPath() != null) {
                editor.saveImage(config.getOutputPath());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
