import javafx.scene.image.Image;
import Texturepack.*;
public class Boss extends Actor {
	private int counter;
	private static double dy=0;
	private static double g=1;
	private int health;
	private boolean jumped;
	private boolean stuck;

	public Boss() {
		String path = getClass().getClassLoader().getResource("Texturepack/boss2.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		counter = 0;
		health = 1000;
		jumped = false;
		stuck = false;

	}
	public void setHealth(int amount) {
		this.health = amount;
	}
	public void decreaseHealth(int amount) {
		this.health -= amount;
		if (this.health <= 0) {
			die();
		}
	}
	
	public void die() {
		((BoshiWorld)(getWorld())).setGameWin(true);

		
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		//Will shoot if counter every 10 "ticks"
		movement();
		if (this.counter % 500 == 0) {
			shoot();
		}
		
		if (this.counter % 1000 == 0) {
			shake();
		}
		meteor();
		
		counter++;
	}
	
	public void meteor() {
		if (counter % 200 == 0) {
			double x = Math.random()*(getWorld().getWidth());
			Meteor m = new Meteor(x);
			getWorld().add(m);
		}
		
	}
	
	public void shake() {
		dy = -20;
		jumped = true;
		stuck = true;
		this.move(0, dy);
	}
	
	public void movement() {
		Platform platform = (Platform)(this.getOneIntersectingObject(Platform.class));
		if (platform == null || stuck) {
			stuck = false;
			dy += g;			
		}
		else {
			dy = 0;
			if (jumped) {
				for (Player p : getWorld().getObjects(Player.class)) {
					Platform platform2 = (Platform)(p.getOneIntersectingObject(Platform.class));
					Wall wall = (Wall)(p.getOneIntersectingObject(Wall.class));
					
					if (platform2 != null || wall != null) {
						p.reduceHealth();
					}
				}
				jumped = false;
			}
		}
		this.move(0, dy);
	}
	public void shoot() {
		double rad = Math.random()*2*Math.PI;
		BossBullet b = new BossBullet(rad);
		b.setX(this.getX()+this.getWidth()/2);
		b.setY(this.getY());
		getWorld().add(b);
	}

}
