import javafx.scene.image.Image;
import Texturepack.*;
public class Meteor extends Actor {
	private static int dy=5;
	public Meteor(double x) {
		String path = getClass().getClassLoader().getResource("Texturepack/meteor2.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		this.setX(x);
		this.setY(0-this.getHeight());
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		if (this.getY() < getWorld().getHeight()) {
			this.move(0, dy);
		}
		else {
			getWorld().remove(this);
		}
//		if (this.getIntersectingObjects(Actor.class).size() != 0) {
//			getWorld().remove(this);
//		}
	}

}
