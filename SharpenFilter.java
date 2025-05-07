import java.awt.image.BufferedImage;
import org.json.JSONObject;

public class SharpenFilter implements Filter {
    @Override
    public BufferedImage apply(BufferedImage img, JSONObject params) {

        //basic kernel for sharpening
        //REF: https://en.wikipedia.org/wiki/Kernel_(image_processing)
        float[][] kernel = {
            { 0f, -1f,  0f },
            {-1f,  5f, -1f },
            { 0f, -1f,  0f }
        };

        return Convolution.apply(img, kernel);
    }
}
