import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EcalenderController {

    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    private final HashMap<String, String> notes = new HashMap<>();
    private final Calendar calendar = Calendar.getInstance();
    private int displayedYear;
    private int displayedMonth;

    @FXML
    public void initialize() {
        displayedYear = calendar.get(Calendar.YEAR);
        displayedMonth = calendar.get(Calendar.MONTH);

        setupYearComboBox();
        setupMonthComboBox();

        updateCalendar();

        calendarGrid.setGridLinesVisible(false); // Ensure the grid lines are always visible

        prevButton.setOnAction(e -> changeMonth(-1));
        nextButton.setOnAction(e -> changeMonth(1));

        yearComboBox.setOnAction(e -> {
            displayedYear = yearComboBox.getValue();
            updateCalendar();
        });

        monthComboBox.setOnAction(e -> {
            displayedMonth = monthComboBox.getSelectionModel().getSelectedIndex();
            updateCalendar();
        });
    }

    private void setupYearComboBox() {
        int currentYear = calendar.get(Calendar.YEAR);
        yearComboBox.getItems().addAll(IntStream.range(currentYear - 5, currentYear + 6).boxed().collect(Collectors.toList()));
        yearComboBox.setValue(currentYear);
    }

    private void setupMonthComboBox() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthComboBox.getItems().addAll(months);
        monthComboBox.getSelectionModel().select(displayedMonth);
    }

    private void updateCalendar() {
        calendarGrid.getChildren().clear();

        // Set up column headers for days of the week
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int col = 0; col < daysOfWeek.length; col++) {
            Label dayLabel = new Label(daysOfWeek[col]);
            calendarGrid.add(dayLabel, col, 0);
        }

        // Configure calendar for the displayed month/year
        calendar.set(displayedYear, displayedMonth, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday=0
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Populate calendar grid
        int row = 1;
        int col = firstDayOfWeek;
        for (int day = 1; day <= daysInMonth; day++) {
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setMaxWidth(Double.MAX_VALUE);

            String dateKey = formatDateKey(displayedYear, displayedMonth, day);
            if (notes.containsKey(dateKey)) {
                dayButton.setStyle("-fx-background-color: lightblue;");
            }
            dayButton.setOnAction(e -> showNoteDialog(dateKey));

            calendarGrid.add(dayButton, col, row);

            if (++col > 6) { // Move to the next row after Saturday
                col = 0;
                row++;
            }
        }

        updateMonthYearLabel();
    }

    private void changeMonth(int delta) {
        displayedMonth += delta;
        if (displayedMonth < 0) {
            displayedMonth = 11;
            displayedYear--;
        } else if (displayedMonth > 11) {
            displayedMonth = 0;
            displayedYear++;
        }
        yearComboBox.setValue(displayedYear);
        monthComboBox.getSelectionModel().select(displayedMonth);
        updateCalendar();
    }

    private void updateMonthYearLabel() {
        monthYearLabel.setText(String.format("%tB %d", calendar.getTime(), displayedYear));
    }

    private void showNoteDialog(String dateKey) {
        String existingNote = notes.getOrDefault(dateKey, "");
        TextInputDialog dialog = new TextInputDialog(existingNote);
        dialog.setTitle("Notes");
        dialog.setHeaderText("Add or Edit Notes for " + dateKey + (existingNote.isEmpty() ? "" : "\nExisting Note: " + existingNote));
        dialog.setContentText("Notes:");

        dialog.showAndWait().ifPresent(note -> {
            if (note.isEmpty()) {
                notes.remove(dateKey);
            } else {
                notes.put(dateKey, note);
            }
            updateCalendar();
        });
    }

    private String formatDateKey(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month + 1, day);
    }
}
