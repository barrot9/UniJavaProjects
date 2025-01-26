import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ChatController {

    @FXML
    private TextField serverField; // Field to enter the server's address
    @FXML
    private TextField nameField;   // Field to enter the user's name
    @FXML
    private Button joinButton;    // Button to join the chat
    @FXML
    private Button leaveButton;   // Button to leave the chat
    @FXML
    private TextArea messagesArea; // Area to display messages
    @FXML
    private TextField messageField; // Field to type messages
    @FXML
    private Button sendButton;    // Button to send messages
    @FXML
    private ListView<String> participantsList; // List to display connected users

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    // Handle the Join button click
    @FXML
    public void handleJoin(ActionEvent event) {
        String serverAddress = serverField.getText().trim();
        String userName = nameField.getText().trim();

        if (serverAddress.isEmpty() || userName.isEmpty()) {
            appendMessage("Error: Server address and name are required to join the chat.");
            return;
        }

        try {
            // Connect to the server
            socket = new Socket(serverAddress, 12345);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Send the user's name to the server
            out.println(userName);

            // Start a background thread to listen for messages
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        handleIncomingMessage(message);
                    }
                } catch (IOException e) {
                    appendMessage("Disconnected from the server.");
                }
            });

            appendMessage("Connected to the server as " + userName);
            updateUIOnConnection(true);
        } catch (IOException e) {
            appendMessage("Unable to connect to the server. Please check the address.");
        }
    }

    // Handle the Leave button click
    @FXML
    public void handleLeave(ActionEvent event) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            appendMessage("You have left the chat.");
        } catch (IOException e) {
            appendMessage("Error while disconnecting.");
        } finally {
            updateUIOnConnection(false);
        }
    }

    // Handle the Send button click
    @FXML
    public void handleSend(ActionEvent event) {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && out != null) {
            out.println(message);
            messageField.clear();
        }
    }

    // Handle incoming messages from the server
    private void handleIncomingMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return;
        }

        if (message.startsWith("Users in the chat:")) {
            String[] users = message.substring(18).split(", ");
            Platform.runLater(() -> participantsList.getItems().setAll(users));
        } else {
            appendMessage(message);
        }
    }

    // Append a message to the messages area
    private void appendMessage(String message) {
        Platform.runLater(() -> messagesArea.appendText(message + "\n"));
    }

    // Update UI components based on connection status
    private void updateUIOnConnection(boolean connected) {
        Platform.runLater(() -> {
            joinButton.setDisable(connected);
            leaveButton.setDisable(!connected);
            sendButton.setDisable(!connected);
            if (!connected) {
                participantsList.getItems().clear();
            }
        });
    }
}
