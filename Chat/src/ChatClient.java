import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

public class ChatClient {
    private static final int PORT = 12345;
    private static String serverAddress;

    public static void main(String[] args) {
        System.out.println("Enter server address:");
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            serverAddress = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Socket socket = new Socket(serverAddress, PORT)) {
            System.out.println("Connected to the chat server.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = console.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
