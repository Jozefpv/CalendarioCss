package dad;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalendarController implements Initializable {

	private IntegerProperty year = new SimpleIntegerProperty(LocalDate.now().getYear());

    @FXML
    private GridPane view;

    @FXML
    private Label yearText;
    
	public CalendarController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		yearText.textProperty().bind(year.asString());
		start();
		
	}

	private void start() {
		view.getChildren().stream()
		.filter(nodo -> nodo instanceof ItemCalendar)
		.map(nodo -> (ItemCalendar) nodo)
		.forEach(nodo -> nodo.yearNumberProperty().bind(year));
	}
	
	public GridPane getView() {
		return view;
	}
	
    @FXML
    void onNextAction(ActionEvent event) {
    	setYear(getYear()+1);
    }

    @FXML
    void onPreviousAction(ActionEvent event) {
    	setYear(getYear()-1);
    }

	public final IntegerProperty yearProperty() {
		return this.year;
	}
	

	public final int getYear() {
		return this.yearProperty().get();
	}
	

	public final void setYear(final int year) {
		this.yearProperty().set(year);
	}
	

}
