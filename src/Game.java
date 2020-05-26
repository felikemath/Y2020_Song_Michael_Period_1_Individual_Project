import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class Game extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	Stage window;
	Scene channel1;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Boshy");
		window = stage;
		stage.setWidth(800);
		stage.setHeight(600);
		//Game stage
		BorderPane rootNode1 = new BorderPane();
		channel1 = new Scene(rootNode1);
		BoshiWorld world = new BoshiWorld();
		Player boshy = new Player();
		
		rootNode1.setCenter(world);
		boshy.setX(300);
		boshy.setY(300);
		
		Platform platform1 = new Platform();
		platform1.setX(100);
		platform1.setY(300);
		
		world.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				world.addKeyCode(event.getCode());
			}
			
		});
		
		world.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				world.removeKeyCode(event.getCode());
			}
			
		});
		
		stage.show();
		world.requestFocus();
	}
	
}
