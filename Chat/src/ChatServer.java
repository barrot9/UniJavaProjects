import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<String> users = Collections.synchronizedSet(new HashSet<>());
    private static Set<PrintWriter> clientWriters = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("Chat server running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private String name;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
            ) {
                out = new PrintWriter(output, true);
                clientWriters.add(out);

                // Get user name
                name = in.readLine();
                synchronized (users) {
                    if (users.contains(name)) {
                        out.println("Name already taken. Disconnecting...");
                        socket.close();
                        return;
                    }
                    users.add(name);
                }

                // Notify all users about the new user
                broadcast(name + " has joined the chat.");
                sendUserList();

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(name + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (name != null) {
                    users.remove(name);
                    broadcast(name + " has left the chat.");
                    sendUserList();
                }
                if (out != null) {
                    clientWriters.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }

        private void sendUserList() {
            synchronized (clientWriters) {
                String userList = "Users in the chat: " + String.join(", ", users);
                for (PrintWriter writer : clientWriters) {
                    writer.println(userList);
                }
            }
        }
    }
}
