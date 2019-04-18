package oop.application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import oop.view.View;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			View mView = new View();
			Scene scene = mView.getMainScene();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Tic Tac Toe");

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
