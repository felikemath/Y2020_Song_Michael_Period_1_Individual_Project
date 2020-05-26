import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import Texturepack.*;
public class Player extends Actor {
	private static double dx=3;
	private static double g=9.81/1000;
	private static double dy=0;
	
	public Player() {
		String path = getClass().getClassLoader().getResource("Texturepack/ball.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		
		
	}
	
	@Override
	public void act() {
		
		// TODO Auto-generated method stub
		System.out.println(this.getX() + " " + this.getY());
		movement();
	}
	
	public void movement() {
		//horizontal movement
		dx = 0;
		if (getWorld().isKeyDown(KeyCode.LEFT)) {
			dx = -3;
		}
		if (getWorld().isKeyDown(KeyCode.RIGHT)) {
			dx = 3;
		}
		
		//Vertical Movement
		Platform platform = (Platform)(this.getOneIntersectingObject(Platform.class));
		
		if (platform == null) {
			dy += g;
		}
		else {
			//Platform is under the character
			if (platform.getY() > this.getY()) {
				if (getWorld().isKeyDown(KeyCode.UP)) {
					dy = 10;
				}
				else {
					dy = 0;
				}
			}
			//Platform is above the character
			if (platform.getY() <= this.getY()) {
				dy = -1;
			}
		}
		
		this.move(dx, dy);
	}

}
