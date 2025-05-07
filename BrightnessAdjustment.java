
// BrightnessAdjustment.java
import java.awt.*;
import java.awt.image.*;
import org.json.JSONObject;

public class BrightnessAdjustment implements Filter {
    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {

        // Read the brightness value from the config (can be positive or negative)
        int value = params.getInt("value");

        // Create a new image of the same size and type
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        // Iterate over each pixel in the image and
        for (int y = 0; y < img.getHeight(); y++) 
        {
            for (int x = 0; x < img.getWidth(); x++) 
            {
                Color color = new Color(img.getRGB(x, y));
                
                // Calculate the new R, G, B values with brightness adjustment
                // Ensure the values are clamped between 0 and 255
                int r = Math.min(255, Math.max(0, color.getRed() + value));
                int g = Math.min(255, Math.max(0, color.getGreen() + value));
                int b = Math.min(255, Math.max(0, color.getBlue() + value));
                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }

        return result;
    }
}