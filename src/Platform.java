import javafx.scene.image.Image;
import Texturepack.*;
public class Platform extends Actor {
	public Platform() {
		String path = getClass().getClassLoader().getResource("Texturepack/2dplatform.jpg").toString();
		Image img = new Image(path);
		this.setImage(img);
		
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		System.out.println(this.getY());
	}

}
