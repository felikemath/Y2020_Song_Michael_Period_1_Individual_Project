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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class Game extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	Stage window;
	Scene channel1, channel2, channel3, channel4;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Boshy");
		window = stage;
		
		stage.setWidth(1000);
		stage.setHeight(600);
		BoshiWorld world = new BoshiWorld();
		
		//Title Screen
		BorderPane rootNode3 = new BorderPane();
		channel3 = new Scene(rootNode3);
		window.setScene(channel3);
		Glow glow = new Glow();
		Text titleStuff = new Text("BOSHY DEMO");
		titleStuff.setEffect(glow);
		
		RotateTransition rt = new RotateTransition();
		rt.setDuration(Duration.seconds(1));
		rt.setByAngle(360);
		rt.setNode(titleStuff);
		
		
		titleStuff.setFont(new Font(40));
		Text controls = new Text("Controls:");
		Text actualControls = new Text("Arrow keys to move, up arrow to jump and z to shoot.\n"
				+ " Avoid the barrage of attacks from the boss and \n"
				+ "beware of the spikes :).");
		VBox vBox2 = new VBox();
		Button startButton = new Button("Start");
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel1);
				
			}
			
		});;
		vBox2.getChildren().addAll(titleStuff, controls, actualControls, startButton);
		vBox2.setAlignment(Pos.CENTER);
		rootNode3.setCenter(vBox2);
		stage.show();
		rt.play();
		world.requestFocus();
		
		//Death Screen
		
		BorderPane rootNode2 = new BorderPane();
		channel2 = new Scene(rootNode2);
		Text t = new Text("You Lose");
		t.setFont(new Font(40));
		VBox vBox = new VBox();
		Button restartButton = new Button("Restart");
		Button titleButton = new Button("Go Back to Main Menu");
		restartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel1);
				
			}
			
		});;
		titleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				window.setScene(channel3);
				
			}
			
		});
		vBox.getChildren().addAll(t, restartButton, titleButton);
		vBox.setAlignment(Pos.CENTER);
		rootNode2.setCenter(vBox);
		stage.show();
		
		
		//Win Screen
		
		BorderPane rootNode4 = new BorderPane();
		channel4 = new Scene(rootNode4);
		Text t2 = new Text("You Win");
		t2.setFont(new Font(40));
		t.setFont(new Font(40));
		VBox vBox3 = new VBox();
		Button restartButton2 = new Button("Restart");
		Button titleButton2 = new Button("Go Back to Main Menu");
		restartButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel1);
				
			}
			
		});;
		titleButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				window.setScene(channel3);
				
			}
			
		});
		vBox3.getChildren().addAll(t2, restartButton2, titleButton2);
		vBox3.setAlignment(Pos.CENTER);
		rootNode4.setCenter(vBox3);
		stage.show();
		
		//Game stage
		BorderPane rootNode1 = new BorderPane();
		channel1 = new Scene(rootNode1);
		
		Player boshy = new Player();
		
		rootNode1.setCenter(world);
		boshy.setX(200);
		boshy.setY(300-boshy.getHeight());
		
		Platform platform1 = new Platform();
		platform1.setX(0);
		platform1.setY(300);
		
		FloatPlatform fp = new FloatPlatform();
		fp.setX(100);
		fp.setY(200);
		
		world.add(fp);
		
		Wall wall = new Wall();
		wall.setX(400);
		wall.setY(230);
		
		//Adding boss
		Boss boss = new Boss();
		boss.setX(600);
		boss.setY(0);
		
		//Adding spikes
		Spikes s = new Spikes();
		s.setX(0);
		s.setY(300-s.getHeight());
		
		world.add(s);
		
		world.add(boss);
		
		world.add(wall);
		world.add(boshy);
		world.add(platform1);
		world.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
//				if (event.getCode().equals(KeyCode.Z)) {
//					boshy.shoot();
//				}
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
		
		world.gameOverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				world.stop();
				window.setScene(channel2);
			}
			
		});
		
		world.gameWinProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				world.stop();
				window.setScene(channel4);
			}
			
		});
		stage.show();
		world.requestFocus();
	}
	
}
