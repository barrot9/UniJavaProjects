public class Matrix {
    private final int[][] grid;

    //Constructor to initialize the matrix
    public Matrix(int rows, int cols) {
        grid = new int[rows][cols];
        generateRandomMatrix();
    }

    //Generates a random matrix when first clicked
    public void generateRandomMatrix() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = (int) (Math.random() * 2); // 0 or 1
            }
        }
    }

    //Counts the number of white neighbors (value 0)
    public int countWhiteNeighbors(int row, int col) {
        int count = 0;

        // Check all 8 possible neighbors
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r == row && c == col) continue; // Skip the current cell
                if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
                    if (grid[r][c] == 0) count++; // Count white neighbors
                }
            }
        }

        return count;
    }

    //This matrix will be used to store updated values during a computation cycle
    public int[][] createUtilityMatrix() {
        return new int[grid.length][grid[0].length];
    }

    //Copies the contents of the provided utility matrix into the main matrix
    //This ensures the main matrix is updated only after all computations are complete
    public void applyUtilityMatrix(int[][] utilityMatrix) {
        for (int row = 0; row < grid.length; row++) {
            System.arraycopy(utilityMatrix[row], 0, grid[row], 0, grid[row].length);
        }
    }
    
    //Get value of specified cell
    public int getValue(int row, int col) {
        return grid[row][col];
    }

    //Set value of specified cell
    public void setValue(int row, int col, int value) {
        grid[row][col] = value;
    }

    //Get the number of rows in the matrix
    public int getRows() {
        return grid.length;
    }
    
    //Get the number of columns in the matrix
    public int getCols() {
        return grid[0].length;
    }
}
