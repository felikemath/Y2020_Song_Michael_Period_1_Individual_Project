import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import Texturepack.*;
public class Player extends Actor {
	private static double dx=3;
	private static double g=1;
	private static double dy=0;
	private boolean inAir;
	private int delay;
	private boolean invulnerable;
	//direction = 0 means facing left while direction = 1 means facing right
	private int direction;
	public Player() {
		String path = getClass().getClassLoader().getResource("Texturepack/BoshyV6.png").toString();
		Image img = new Image(path);
		this.setImage(img);
		inAir = true;
		direction = 1;
		delay = 0;
		invulnerable = false;
	}
	
	@Override
	public void act() {
		
		// TODO Auto-generated method stub
		movement();
		damage();
		if (delay % 3 == 0) {
			shoot();
			
		}
		delay++;
	}
	public void damage() {
		if (invulnerable) {
			return;
		}
		Meteor m = (Meteor)(this.getOneIntersectingObject(Meteor.class));

		BossBullet b = (BossBullet)(this.getOneIntersectingObject(BossBullet.class));
		Boss boss = (Boss)(this.getOneIntersectingObject(Boss.class));
		Spikes spikes = (Spikes)(this.getOneIntersectingObject(Spikes.class));
		if (b != null || boss != null || spikes != null || m != null) {
			reduceHealth();
		}
	}
	
	public void reduceHealth() {
		invulnerable = true;
		Life lives = ((BoshiWorld)getWorld()).getLife();
		lives.setLifeCount(lives.getLives()-1);
		if (lives.getLives() == 0) {
			((BoshiWorld)(getWorld())).setGameOver(true);
		}
		//((BoshiWorld)getWorld()).restart();
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), this);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(12);
        fadeTransition.setAutoReverse(true);
        fadeTransition.statusProperty().addListener(new ChangeListener<Status>() {

			@Override
			public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue) {
				// TODO Auto-generated method stub
				if (newValue != Status.RUNNING) {
					invulnerable = false;
				}
				
			}
        	
        });
        fadeTransition.play();
	}
	
	
	public void movement() {
		
		//horizontal movement
		Wall wall = (Wall)(this.getOneIntersectingObject(Wall.class));
		dx = 0;
		if (getWorld().isKeyDown(KeyCode.LEFT)) {
			dx = -3;
			if (wall != null && wall.getX() < this.getX() && wall.getY() < this.getY()) {
				dx = 0;
			}
			this.direction = 0;
		}
		if (getWorld().isKeyDown(KeyCode.RIGHT)) {
			
			if (wall != null && wall.getX() > this.getX() && wall.getY() < this.getY()) {
				
			}
			else {
				dx = 3;
			}
			this.direction = 1;
		}
		//Horizontal Collision with a wall
		
		if (wall != null) {

		}
		//Vertical Movement
		Platform platform = (Platform)(this.getOneIntersectingObject(Platform.class));
		
		if (platform == null && wall == null) {
			dy += g;
			inAir = true;
			
		}
		else {
			//Platform is under the character
			if (platform != null && platform.getY() > this.getY() || (wall != null && wall.getY() > this.getY())) {
				if (getWorld().isKeyDown(KeyCode.UP) && !(inAir)) {
					dy = -15;
					inAir = true;
				}
				else {
					dy = 0;
					inAir = false;
				}
			}
			//Platform is above the character
			if (platform != null && platform.getY() <= this.getY()) {
				dy = 1;
			}
		}
		
		this.move(dx, dy);
	}
	
	public void shoot() {
		if (getWorld().isKeyDown(KeyCode.Z)) {
			Bullet b = new Bullet(-15*Math.pow(-1, this.direction));
			b.setX(this.getX()+this.getWidth()/2);
			b.setY(this.getY()+this.getHeight()/2);
			getWorld().add(b);
		}
//		Bullet b = new Bullet(-15*Math.pow(-1, this.direction));
//		b.setX(this.getX()+this.getWidth()/2);
//		b.setY(this.getY()+this.getHeight()/2);
//		getWorld().add(b);
	}

}
