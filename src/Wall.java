import javafx.scene.image.Image;
import Texturepack.*;
public class Wall extends Actor{
	public Wall() {
		String path = getClass().getClassLoader().getResource("Texturepack/wall4.jpg").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	
	public void act() {
		
	}
	
	
}
