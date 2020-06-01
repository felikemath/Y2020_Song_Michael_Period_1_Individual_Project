import javafx.scene.image.Image;
import Texturepack.*;
public class FloatPlatform extends Platform {
	public FloatPlatform() {
		String path = getClass().getClassLoader().getResource("Texturepack/paddle.png").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	
}
