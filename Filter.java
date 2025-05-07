import java.awt.image.BufferedImage;
import java.awt.image.*;
import org.json.JSONObject;


public interface Filter {
    BufferedImage apply(BufferedImage img, JSONObject params);
}

