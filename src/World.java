import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;
public abstract class World extends Pane{
	private AnimationTimer timer;
	private HashSet<KeyCode> set;
	private ArrayList<Node> ar = new ArrayList<Node>();
	public World() {
		set = new HashSet<KeyCode>();
		for (Node n : getChildren()) {
			ar.add(n);
		}
		timer = new AnimationTimer(){
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				
				for (int i = 0; i < ar.size(); i++) {
					System.out.println("yes");
					if(ar.get(i) instanceof Actor) {
						((Actor) ar.get(i)).act();
					}
					
				}
			}
			
		};	
	}
	
	public abstract void act();
	public void add(Actor actor) {
		this.getChildren().add((Node)actor);
		ar.add((Node)actor);
	}
	
	public void remove(Actor actor) {
		this.getChildren().remove((Node)actor);
		ar.remove((Node)actor);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public <A extends Actor> List<A> getObjects(Class<A> cls) {
		List<A> a = new ArrayList<A>();
		for (Node n : this.getChildren()) {
			if (cls.isInstance(n)) {
				a.add((A)n);
			}
		}
		return a;
	}
	
	public void addKeyCode(KeyCode k) {
		set.add(k);
	}
	
	public void removeKeyCode(KeyCode k) {
		set.remove(k);
	}
	
	public boolean isKeyDown(KeyCode k) {
		return set.contains(k);
	}
	
	public void pauseTimerForDuration(Duration duration) {
	    PauseTransition pt = new PauseTransition(duration);
	    pt.setOnFinished(event -> timer.start());

	    timer.stop();
	    pt.play();
	}
}