import java.awt.*;
import java.awt.image.BufferedImage;
import org.json.JSONObject;

public class SaturationAdjustment implements Filter {
    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {

        float factor = (float) params.optDouble("factor", 1.0);// Default factor is 1.0 (no change)
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                // Get the color of the pixel
                Color c = new Color(img.getRGB(x, y));

                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();

                int gray = (r + g + b) / 3;

                // Calculate the new R, G, B values with saturation adjustment
                int newR = legalRange((int)(gray + (r - gray) * factor));
                int newG = legalRange((int)(gray + (g - gray) * factor));
                int newB = legalRange((int)(gray + (b - gray) * factor));

                result.setRGB(x, y, new Color(newR, newG, newB).getRGB());
            }
        }

        return result;
    }

    private int legalRange(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
