import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordManager {
    private List<String> words;

    
     // Constructor to load words from a file.
     
    public WordManager(String filePath) throws IOException {
        try {
            // Read all lines from the file
            words = Files.readAllLines(Paths.get(filePath));
            
            // Check if the file is empty
            if (words.isEmpty()) {
                throw new IOException("The words file is empty.");
            }

            System.out.println("Words loaded successfully.");
        } catch (IOException e) {
            // Log the error
            System.err.println("Error loading words file: " + e.getMessage());
            throw e; // Re-throw the exception for handling in the caller
        }
    }

    
     // Gets a random word from the loaded words.
    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
