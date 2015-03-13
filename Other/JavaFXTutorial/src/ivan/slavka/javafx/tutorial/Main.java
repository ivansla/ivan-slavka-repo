package ivan.slavka.javafx.tutorial;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{

	private String meno = "Ivan";
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.BLACK);

		Image image = new Image("d:/workspace/JavaFXTutorial/src/ivan/slavka/javafx/tutorial/claudia.jpg", 300, 0, true, false);
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		root.getChildren().add(imageView);

		//		Rectangle r = new Rectangle(25,25,250,250);
		//		r.setFill(Color.BLUE);
		//		root.getChildren().add(r);
		//
		TranslateTransition translate =
				new TranslateTransition(Duration.millis(750));
		translate.setToX(390);
		translate.setToY(390);

		FillTransition fill = new FillTransition(Duration.millis(750));
		fill.setToValue(Color.RED);

		RotateTransition rotate = new RotateTransition(Duration.millis(750));
		rotate.setToAngle(360);

		ScaleTransition scale = new ScaleTransition(Duration.millis(750));
		scale.setToX(0.1);
		scale.setToY(0.1);

		ParallelTransition transition = new ParallelTransition(imageView,
				translate, fill, rotate, scale);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setAutoReverse(true);
		transition.play();

		stage.setTitle("JavaFX Scene Graph Demo");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
