package ch.munterfi.basiccomm;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CompletableFuture;

public class Server {
    private final int port;
    private final MessageHandler messageHandler;
    private ServerSocket serverSocket;
    private Thread serverThread;

    public Server(int port, MessageHandler messageHandler) {
        this.port = port;
        this.messageHandler = messageHandler;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            serverThread = Thread.currentThread();
            while (!serverThread.isInterrupted()) {
                var socket = serverSocket.accept();
                var clientHandler = new ClientHandler(socket, messageHandler);
                CompletableFuture.runAsync(clientHandler);
            }
        } catch (IOException e) {
            if (!serverThread.isInterrupted()) {
                throw new RuntimeException("Error while running server", e);
            }
        }
    }

    public void stop() {
        serverThread.interrupt();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while stopping server", e);
        }
    }
}

