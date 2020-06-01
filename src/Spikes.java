import javafx.scene.image.Image;
import Texturepack.*;
public class Spikes extends Actor {
	public Spikes() {
		String path = getClass().getClassLoader().getResource("Texturepack/spikes3.png").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

}
