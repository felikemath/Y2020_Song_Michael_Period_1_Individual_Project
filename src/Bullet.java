import javafx.scene.image.Image;
import Texturepack.*;
public class Bullet extends Actor {
	private double velocity;
	public Bullet(double velocity) {
		String path = getClass().getClassLoader().getResource("Texturepack/ball.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		this.velocity = velocity;
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
		boolean removed=false;
		//Bullet going off screen
		if (this.getX() < 0 || this.getX() > getWorld().getWidth()) {
			getWorld().remove(this);
			removed = true;
		}
		
		//Bullet colliding with a wall
		if (!(removed)) {
			Wall wall = (Wall)(this.getOneIntersectingObject(Wall.class));
			if (wall != null) {
				getWorld().remove(this);
				removed = true;
			}
		}
		
		if (!(removed)) {
			Boss boss = (Boss)(this.getOneIntersectingObject(Boss.class));
			if (boss != null) {
				boss.decreaseHealth(5);
				getWorld().remove(this);
				removed = true;
			}
		}
		
		if (!(removed)) {
			BossBullet b = (BossBullet)(this.getOneIntersectingObject(BossBullet.class));
			if (b != null) {
				getWorld().remove(this);
				removed = true;
			}
		}
		if (!(removed)) {
			this.move(velocity, 0);
		}
	}

}
