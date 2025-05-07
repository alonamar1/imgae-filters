import java.awt.image.BufferedImage;
import org.json.JSONObject;

public class BoxBlurFilter implements Filter {
    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {
        
        //obligatory check for width and height parameters
        if (!params.has("width") || !params.has("height")) {
            throw new IllegalArgumentException("BoxBlurFilter requires 'width' and 'height' parameters.");
        }

        int width = params.getInt("width");
        int height = params.getInt("height");

        float[][] kernel = new float[height][width];
        float value = ((float) 1) / (width * height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                kernel[i][j] = value;
            }
        }

        // Apply the convolution with the box blur kernel
        return Convolution.apply(img, kernel);
    }
}
