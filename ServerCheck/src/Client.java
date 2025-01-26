// Client.java
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.*;

public class Client {
    private static final int PORT = 8888;
    private static final int TIMEOUT_MS = 10000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter server address: ");
        String serverAddress = scanner.nextLine();

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            clientSocket.setSoTimeout(TIMEOUT_MS);
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

            ExecutorService executor = Executors.newFixedThreadPool(2);

            // Thread for sending messages
            executor.submit(() -> {
                try {
                    for (int i = 1; i <= 10; i++) {
                        String message = "Message " + i;
                        byte[] buffer = message.getBytes();

                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverInetAddress, PORT);
                        clientSocket.send(packet);
                        System.out.println("Sent: " + message);

                        Thread.sleep(500); // Slight delay to avoid flooding
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Thread for receiving messages
            executor.submit(() -> {
                int receivedCount = 0;

                for (int i = 1; i <= 10; i++) {
                    try {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                        clientSocket.receive(packet);

                        String response = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Received: " + response + " from " + packet.getAddress() + ":" + packet.getPort());

                        receivedCount++;
                    } catch (SocketTimeoutException e) {
                        System.out.println("Timeout waiting for message " + i);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }

                System.out.println("Total messages received: " + receivedCount);
                executor.shutdown();
            });

            executor.awaitTermination(TIMEOUT_MS * 2, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}