import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
public class BoshiWorld extends World {
	//Properties
	private BooleanProperty gameOver = new SimpleBooleanProperty();
	private BooleanProperty gameWin = new SimpleBooleanProperty();
	public final boolean getGameOver() {
		return gameOver.get();
	}
	
	public final boolean getGameWin() {
		return gameWin.get();
	}
	
	public final void setGameOver(boolean b) {
		gameOver.set(b);
	}
	
	public final void setGameWin(boolean b) {
		gameWin.set(b);
	}
	public BooleanProperty gameOverProperty() {
		return gameOver;
	}
	
	public BooleanProperty gameWinProperty() {
		return gameWin;
	}
	private Life lives;
	public BoshiWorld() {
		lives = new Life();
		lives.setX(900);
		lives.setY(20);
		getChildren().add(lives);
	}
	
	public Life getLife() {
		return this.lives;
	}
	
	public void clearFireBalls() {
		for (BossBullet b : this.getObjects(BossBullet.class)) {
			this.remove(b);
		}
	}
	
	public void clearMeteors() {
		for (Meteor m : this.getObjects(Meteor.class)) {
			this.remove(m);
		}
	}
	
	public void clearBullets() {
		for (Bullet b : this.getObjects(Bullet.class)) {
			this.remove(b);
		}
	}
	
	public void restart() {
		this.start();
		setGameOver(false);
		setGameWin(false);
		this.pauseTimerForDuration(new Duration(1000));
		clearFireBalls();
		clearMeteors();
		clearBullets();
		lives.setLifeCount(3);
		for (Boss b : this.getObjects(Boss.class)) {
			this.remove(b);
			Boss newBoss = new Boss();
			newBoss.setX(600);
			newBoss.setY(0);
			this.add(newBoss);
		}
		for (Player p : this.getObjects(Player.class)) {
			this.remove(p);
			Player boshy = new Player();
			boshy.setX(200);
			boshy.setY(300-p.getHeight());
			this.add(boshy);
		}
		this.clearKeyCode();
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

}
