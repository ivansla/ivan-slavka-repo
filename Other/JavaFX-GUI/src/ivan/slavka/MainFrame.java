package ivan.slavka;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MainFrame extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 200, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("My first user control GUI");
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
