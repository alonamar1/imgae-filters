import java.awt.*;
import java.awt.image.BufferedImage;
import org.json.JSONObject;

public class ContrastAdjustment implements Filter {
    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {
        float factor = (float) params.optDouble("factor", 1.0); 

        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        //REF : https://www.dfstudios.co.uk/articles/programming/image-programming-algorithms/image-processing-algorithms-part-5-contrast-adjustment/
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y)); // Get the color of the pixel

                // Calculate the new R, G, B values with contrast adjustment
                int r = legalRange((int)((c.getRed() - 128) * factor + 128));
                int g = legalRange((int)((c.getGreen() - 128) * factor + 128));
                int b = legalRange((int)((c.getBlue() - 128) * factor + 128));

                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }

        return result;
    }

    // Helper method to ensure the color values are within the legal range [0, 255]
    private int legalRange(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
