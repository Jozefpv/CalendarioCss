package dad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new CalendarController().getView()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
