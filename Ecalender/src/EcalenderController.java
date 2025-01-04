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
    private ComboBox<Integer> yearComboBox; // Dropdown to select the year

    @FXML
    private ComboBox<String> monthComboBox; // Dropdown to select the month

    private final HashMap<String, StringBuilder> notes = new HashMap<>(); // Stores notes with date keys
    private final Calendar calendar = Calendar.getInstance(); // Calendar instance for date management
    private int displayedYear; // Current displayed year
    private int displayedMonth; // Current displayed month (0-based)

    @FXML
    public void initialize() {
        // Initialize the displayed year and month to the current date
        displayedYear = calendar.get(Calendar.YEAR);
        displayedMonth = calendar.get(Calendar.MONTH);

        // Set up the year and month dropdowns
        setupYearComboBox();
        setupMonthComboBox();

        // Populate the calendar grid
        updateCalendar();

        
        calendarGrid.setGridLinesVisible(false); // To remove gridPane border lines

        // Attach actions to navigation buttons
        prevButton.setOnAction(e -> changeMonth(-1));
        nextButton.setOnAction(e -> changeMonth(1));

        // Attach actions to year and month dropdowns
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
        // Populate the year dropdown with a range of years around the current year
        int currentYear = calendar.get(Calendar.YEAR);
        yearComboBox.getItems().addAll(IntStream.range(currentYear - 5, currentYear + 6).boxed().collect(Collectors.toList())); // Assuming 10 years is enough..
        yearComboBox.setValue(currentYear); // Set the default selected year
    }

    private void setupMonthComboBox() {
        // Populate the month dropdown with all months of the year
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthComboBox.getItems().addAll(months);
        monthComboBox.getSelectionModel().select(displayedMonth); // Set the default selected month
    }

    private void updateCalendar() {
        // Clear the grid for repopulation
        calendarGrid.getChildren().clear();

        // Set up column headers for days of the week
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int col = 0; col < daysOfWeek.length; col++) {
            Label dayLabel = new Label(daysOfWeek[col]);
            calendarGrid.add(dayLabel, col, 0); // Add headers to the first row
        }

        // Configure the calendar for the displayed month and year
        calendar.set(displayedYear, displayedMonth, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Adjust to 0-based index (Sunday=0)
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Populate the calendar grid with day buttons
        int row = 1;
        int col = firstDayOfWeek;
        for (int day = 1; day <= daysInMonth; day++) {
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setMaxWidth(Double.MAX_VALUE);

            String dateKey = formatDateKey(displayedYear, displayedMonth, day);
            // Highlight days with existing notes
            if (notes.containsKey(dateKey)) {
                dayButton.setStyle("-fx-background-color: lightblue;");
            }
            dayButton.setOnAction(e -> showNoteDialog(dateKey)); // Attach note dialog action

            calendarGrid.add(dayButton, col, row);

            // Move to the next row after Saturday
            if (++col > 6) {
                col = 0;
                row++;
            }
        }

        // Update the month and year label
        updateMonthYearLabel();
    }

    private void changeMonth(int delta) {
        // Adjust the displayed month and handle year rollover
        displayedMonth += delta;
        if (displayedMonth < 0) {
            displayedMonth = 11;
            displayedYear--;
        } else if (displayedMonth > 11) {
            displayedMonth = 0;
            displayedYear++;
        }
        yearComboBox.setValue(displayedYear); // Update year dropdown
        monthComboBox.getSelectionModel().select(displayedMonth); // Update month dropdown
        updateCalendar(); // Refresh the calendar grid
    }

    private void updateMonthYearLabel() {
        // Update the label to show the current displayed month and year
        monthYearLabel.setText(String.format("%tB %d", calendar.getTime(), displayedYear));
    }

    private void showNoteDialog(String dateKey) {
        // Retrieve or initialize notes for the selected date
        StringBuilder existingNotes = notes.computeIfAbsent(dateKey, k -> new StringBuilder());

        // Create a dialog with a text area for multi-line notes
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Meetings");
        dialog.setHeaderText(dateKey + "\nAdd or Edit Meetings:" );

        TextArea textArea = new TextArea(existingNotes.toString());
        textArea.setWrapText(true);

        dialog.getDialogPane().setContent(textArea);

        // Add OK and Cancel buttons
        ButtonType okButtonType = new ButtonType("Save changes", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return textArea.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            existingNotes.setLength(0); // Clear the current notes
            existingNotes.append(result); // Save the updated notes

            if (existingNotes.toString().isEmpty()) {
                notes.remove(dateKey); // Remove the note if it's empty
            }

            updateCalendar(); // Refresh the calendar to reflect changes
        });
    }

    private String formatDateKey(int year, int month, int day) {
        // Format a date key as "YYYY-MM-DD" for note storage
        return String.format("%04d-%02d-%02d", year, month + 1, day);
    }
}
