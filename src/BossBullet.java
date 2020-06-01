import javafx.scene.image.Image;
import Texturepack.*;
public class BossBullet extends Actor {
	private int counter;
	private double theta;
	private double dx, dy;
	private boolean bouncing;
	private int health;
	public BossBullet(double theta) {
		String path = getClass().getClassLoader().getResource("Texturepack/fireball2.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		counter=1;
		this.theta = theta;
		//total magnitude of vector = 10
		dx = Math.cos(theta)*10;
		dy = Math.sin(theta)*10;
		bouncing = false;
		health = 50;
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
		//Check collisions with stage boundaries
		if (bouncing) {
			this.move(dx, dy);
			bouncing = false;
		}
		if (this.getX() < 0 || this.getX()+this.getWidth() > getWorld().getWidth()) {
			this.dx = -this.dx;
			bouncing = true;
		}
		
		if (this.getY() < 0 || this.getY() + this.getWidth() > getWorld().getHeight()) {
			this.dy = -this.dy;
			bouncing = true;

		}
		
		//Check collisions with wall
		Wall wall = (Wall)(this.getOneIntersectingObject(Wall.class));
		if (wall != null) {
			if (this.getY()+this.getHeight() < wall.getY() && this.getX() > wall.getX() && this.getX() < wall.getX()+wall.getWidth()) {
				this.dy = -this.dy;
				bouncing = true;

			}
			else if (this.getY() +this.getWidth() > wall.getY()) {
				this.dx = -this.dx;
				bouncing = true;

			}
//			if ((this.getX() < wall.getX() || this.getX()+this.getWidth() > wall.getX()+wall.getWidth()) && this.getY() > wall.getY()) {
//				this.dx = -this.dx;
//			}
		}
		
		//Check collisions with platform
		Platform platform = (Platform)(this.getOneIntersectingObject(Platform.class));
		if (platform != null && !(platform instanceof FloatPlatform)) {
			this.dy = -this.dy;
			bouncing = true;

		}
		this.move(this.dx, this.dy);
		if (this.counter == 800) {
			getWorld().remove(this);
			return;
		}
		
		//Check to see if fireball is destroyed
		Bullet b = (Bullet)(this.getOneIntersectingObject(Bullet.class));
		if (b != null) {
			this.health -= 10;
		}
		
		if (this.health <= 0) {
			getWorld().remove(this);
			return;
		}
		counter++;
	}

}
