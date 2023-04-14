package ch.munterfi;

import ch.munterfi.basiccomm.Client;
import ch.munterfi.basiccomm.Message;
import ch.munterfi.basiccomm.MessageHandler;
import ch.munterfi.basiccomm.Server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class HelloWorldMessageHandler implements MessageHandler {
    @Override
    public Message apply(Message message) {
        var requestString = message.toString();
        System.out.println("Service - Received request: " + requestString);
        var responseString = "Unknown request!";
        if (requestString.equals("hello")) {
            responseString = "world";
        }
        var response = new Message(responseString.getBytes(StandardCharsets.UTF_8));
        System.out.println("Service - Sending response: " + response);
        return response;
    }
}

public class HelloWorldExample {

    private final int port = 22010;
    private Server server;

    public void runServer() {
        var messageHandler = new HelloWorldMessageHandler();
        server = new Server(port, messageHandler);
        new Thread(server::start).start();
    }

    public void stopServer() {
        server.stop();
    }

    public Message runClient(String requestText) throws IOException {
        var client = new Client("localhost", port);
        System.out.println("Consumer - Sending request: " + requestText);
        var request = new Message(requestText.getBytes(StandardCharsets.UTF_8));
        var response = client.send(request);
        System.out.println("Consumer - Received response: " + response.toString());
        return response;
    }

    public static void main(String[] args) throws IOException {
        var example = new HelloWorldExample();
        example.runServer();
        example.runClient("hello");
        example.runClient("foobar");
        example.stopServer();
    }
}
