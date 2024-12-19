import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MyProjectController {

    @FXML
    private Button btn;

    @FXML
    private Canvas canv;

    private GraphicsContext gc;
    private Matrix matrix;
    private boolean isFirstClick = true; // Tracks if this is the first button click

    public void initialize() {
        gc = canv.getGraphicsContext2D();
        matrix = new Matrix(10, 10); // Create a 10x10 matrix
    }

    @FXML
    void btnPressed(ActionEvent event) {
        if (isFirstClick) {
            drawMatrix();  // First click: Draw the initial random matrix
            isFirstClick = false;  // Set the flag to false after the first click
        } else {
            updateMatrix();  // Subsequent clicks: Update matrix based on current generation
            drawMatrix();    // Redraw the updated matrix
        }
    }
    
    //updates the next generation on the matrix according to the current one
    private void updateMatrix() {
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        int[][] utilityMatrix = matrix.createUtilityMatrix();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int whiteNeighbors = matrix.countWhiteNeighbors(row, col);
                int currentValue = matrix.getValue(row, col);

                if (currentValue == 1) { // Grey cell
                    utilityMatrix[row][col] = (whiteNeighbors == 3) ? 0 : 1;
                } else { // White cell
                    if (whiteNeighbors == 2 || whiteNeighbors == 3) {
                        utilityMatrix[row][col] = 0; // Remains white
                    } else {
                        utilityMatrix[row][col] = 1; // Turns grey
                    }
                }
            }
        }

        matrix.applyUtilityMatrix(utilityMatrix);
    }
    //Method to draw the matrix on the canvas 
    private void drawMatrix() {
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        double squareWidth = canv.getWidth() / cols;
        double squareHeight = canv.getHeight() / rows;

        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color color = (matrix.getValue(row, col) == 0) ? Color.WHITE : Color.GREY;
                gc.setFill(color);
                gc.fillRect(col * squareWidth, row * squareHeight, squareWidth, squareHeight);
                gc.setStroke(Color.BLACK);
                gc.strokeRect(col * squareWidth, row * squareHeight, squareWidth, squareHeight);
            }
        }
    }
}
