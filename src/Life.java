import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Life extends Text{
	private int lifeCount;
	
	public Life() {
		this.lifeCount = 3;
		this.setFont(new Font(20));
		updateDisplay();
	}
	
	public void updateDisplay() {
		this.setText("Lives: " + lifeCount);
	}
	
	public int getLives() {
		return this.lifeCount;
	}
	
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
		updateDisplay();
	}
	
	
}
