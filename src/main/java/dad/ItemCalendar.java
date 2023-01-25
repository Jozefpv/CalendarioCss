package dad;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ItemCalendar extends GridPane implements Initializable {

	// model
	private StringProperty month = new SimpleStringProperty();

	private IntegerProperty monthNumber = new SimpleIntegerProperty(LocalDate.now().getMonthValue());
	private IntegerProperty yearNumber = new SimpleIntegerProperty(LocalDate.now().getYear());

	@FXML
	private Label monthText;

	@FXML
	private Label dialabel;

	@FXML
	private GridPane view;

	public ItemCalendar() {
		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calendar.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		monthText.textProperty().bind(month);

		loadMonthDays();
		yearNumber.addListener((o, ov, nv) -> onChangeYear(o, ov, nv));
		monthNumber.addListener((o, ov, nv) -> onChangeMonth(o, ov, nv));
		

	}

	private void onChangeMonth(ObservableValue<? extends Number> o, Number ov, Number nv) {
		loadMonthDays();
	}

	private void onChangeYear(ObservableValue<? extends Number> o, Number ov, Number nv) {
		loadMonthDays();
	}

	private void loadMonthDays() {

		cleanLabel();
		int daysInMonth = YearMonth.of(getYearNumber(), getMonthNumber()).lengthOfMonth();

		int fila = 2;
		for (int i = 1; i <= daysInMonth; i++) {
			LocalDate fecha = LocalDate.of(yearNumber.get(), monthNumber.get(), i);
			switch (fecha.getDayOfWeek()) {
			case MONDAY:
				getLabel(fila, 0).setText(fecha.getDayOfMonth() + "");
				break;
			case TUESDAY:
				getLabel(fila, 1).setText(fecha.getDayOfMonth() + "");
				break;
			case WEDNESDAY:
				getLabel(fila, 2).setText(fecha.getDayOfMonth() + "");
				break;
			case THURSDAY:
				getLabel(fila, 3).setText(fecha.getDayOfMonth() + "");
				break;
			case FRIDAY:
				getLabel(fila, 4).setText(fecha.getDayOfMonth() + "");
				break;
			case SATURDAY:
				getLabel(fila, 5).setText(fecha.getDayOfMonth() + "");
				break;
			case SUNDAY:
				getLabel(fila, 6).setText(fecha.getDayOfMonth() + "");
				fila++;
				break;
			}
		}
		getActualDay();


	}

	private void getActualDay() {

		if(getYearNumber() == LocalDate.now().getYear() && getMonthNumber() == LocalDate.now().getMonthValue()) {
			view.getChildren().stream().filter(nodo -> nodo instanceof Label)
			.filter(nodo -> "diaLabel".equalsIgnoreCase(nodo.getId()))
			.map(item -> (Label) item)
			.filter(item -> item.getText().equals(LocalDate.now().getDayOfMonth()+ ""))
			.forEach(day -> day.getStyleClass().add("actualDay"));
		}
		
			

		
	}

	private Label getLabel(int fila, int col) {

		List<Label> labels = view.getChildren().stream().filter(nodo -> nodo instanceof Label)
				.filter(nodo -> "diaLabel".equalsIgnoreCase(nodo.getId()))
				.filter(nodo -> GridPane.getRowIndex(nodo) != null && GridPane.getColumnIndex(nodo) != null)
				.map(item -> (Label) item).collect(Collectors.toList());

		return labels.stream()
				.filter(nodo -> GridPane.getRowIndex(nodo) == fila && GridPane.getColumnIndex(nodo) == col).findFirst()
				.get();

	}

	private void cleanLabel() {
		view.getChildren().stream().filter(nodo -> nodo instanceof Label)
				.filter(nodo -> "diaLabel".equalsIgnoreCase(nodo.getId())).map(item -> (Label) item)
				.forEach(item -> {
					item.setText("");
					item.getStyleClass().remove("actualDay");
				});
				
	}

	public GridPane getView() {
		return view;
	}

	public final StringProperty monthProperty() {
		return this.month;
	}

	public final String getMonth() {
		return this.monthProperty().get();
	}

	public final void setMonth(final String month) {
		this.monthProperty().set(month);
	}

	public final IntegerProperty monthNumberProperty() {
		return this.monthNumber;
	}

	public final int getMonthNumber() {
		return this.monthNumberProperty().get();
	}

	public final void setMonthNumber(final int monthNumber) {
		this.monthNumberProperty().set(monthNumber);
	}

	public final IntegerProperty yearNumberProperty() {
		return this.yearNumber;
	}

	public final int getYearNumber() {
		return this.yearNumberProperty().get();
	}

	public final void setYearNumber(final int yearNumber) {
		this.yearNumberProperty().set(yearNumber);
	}

}
