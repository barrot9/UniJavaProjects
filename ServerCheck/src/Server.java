import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(8888)) {
            System.out.println("Server is running on port 8888...");
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);

                // Print received message
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + receivedMessage + " from " + packet.getAddress() + ":" + packet.getPort());

                // Echo the message back to the client
                DatagramPacket response = new DatagramPacket(
                        packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());
                serverSocket.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}