package ivan.slavka.javafx.tutorial;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class ColorfullCircles extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.BLACK);
		primaryStage.setScene(scene);

		Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
				new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new
						Stop[]{
						new Stop(0, Color.web("#f8bd55")),
						new Stop(0.14, Color.web("#c0fe56")),
						new Stop(0.28, Color.web("#5dfbc1")),
						new Stop(0.43, Color.web("#64c2f8")),
						new Stop(0.57, Color.web("#be4af7")),
						new Stop(0.71, Color.web("#ed5fc2")),
						new Stop(0.85, Color.web("#ef504c")),
						new Stop(1, Color.web("#f2660f")),}));
		root.getChildren().add(colors);

		Group circles = new Group();
		for (int i = 0; i < 30; i++) {
			Circle circle = new Circle(150, Color.web("white", 0.05));
			circle.setStrokeType(StrokeType.OUTSIDE);
			circle.setStroke(Color.web("white", 0.16));
			circle.setStrokeWidth(4);
			circles.getChildren().add(circle);
		}
		root.getChildren().add(circles);

		circles.setEffect(new BoxBlur(10, 10, 3));

		primaryStage.show();
	}

	public static void main(String[] args) {

		Application.launch(args);
	}


}
