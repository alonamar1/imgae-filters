import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolution {

    public static BufferedImage apply(BufferedImage img, float[][] kernel) {
        int width = img.getWidth();
        int height = img.getHeight();

        int kw = kernel[0].length; //kernel's width
        int kh = kernel.length; //kernel's height

        int radiusX = kw / 2; //indicates the distance from the center of the kernel to its edge
        int radiusY = kh / 2; //indicates the distance from the center of the kernel to its edge

        BufferedImage result = new BufferedImage(width, height, img.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                float sumR = 0, sumG = 0, sumB = 0;

                for (int i = -radiusY; i <= radiusY; i++) {
                    for (int j = -radiusX; j <= radiusX; j++) {
                        int nx = x + j;
                        int ny = y + i;

                        if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                            continue; 
                        }

                        Color c = new Color(img.getRGB(nx, ny));
                        float weight = kernel[i + radiusY][j + radiusX];

                        sumR += c.getRed() * weight;
                        sumG += c.getGreen() * weight;
                        sumB += c.getBlue() * weight;
                    }
                }

                int r = legalRange(Math.round(sumR));
                int g = legalRange(Math.round(sumG));
                int b = legalRange(Math.round(sumB));

                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }

        return result;
    }

    private static int legalRange(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
