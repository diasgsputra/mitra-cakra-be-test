import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketExample {

    public static void main(String[] args) {
        try {
            // URL WebSocket server
            URI serverUri = new URI("ws://localhost:8080/websocket");

            // Inisialisasi WebSocketClient
            WebSocketClient client = new WebSocketClient(serverUri) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to server!");
                    send("Hello, server!");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Received message from server: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Connection closed. Reason: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("Error occurred: " + ex.getMessage());
                }
            };
            client.connect();

            Thread.sleep(5000);

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
