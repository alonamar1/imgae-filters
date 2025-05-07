import java.awt.image.BufferedImage;
import org.json.JSONObject;

public class SobelFilter implements Filter {

    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // recognize horizontal and vertical changes in the image
        int[][] gx = {
                { -1, 0, 1 },
                { -2, 0, 2 },
                { -1, 0, 1 }
        };

        int[][] gy = {
                { -1, -2, -1 },
                { 0, 0, 0 },
                { 1, 2, 1 }
        };

        // Loop through each pixel in the image, excluding the borders
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sumX = 0;
                int sumY = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int rgb = img.getRGB(x + j, y + i);
                        int gray = (rgb >> 16) & 0xff;

                        sumX += gray * gx[i + 1][j + 1];
                        sumY += gray * gy[i + 1][j + 1];
                    }
                }

                int magnitude = Math.abs(sumX) + Math.abs(sumY);
               
                //REF: https://www.youtube.com/watch?v=VL8PuOPjVjY
                // Normalize the magnitude to be in the range [0, 255]
                int g = legalRange(magnitude);
                int color = (g << 16) | (g << 8) | g;

                output.setRGB(x, y, color);
            }
        }

        return output;
    }

    private int legalRange(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
