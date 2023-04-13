package ch.munterfi.basiccomm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private final MessageHandler handler;
    private ServerSocket serverSocket;
    private Thread serverThread;

    public Server(int port, MessageHandler handler) {
        this.port = port;
        this.handler = handler;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            serverThread = Thread.currentThread();
            while (!serverThread.isInterrupted()) {
                Socket socket = serverSocket.accept();
                Thread t = new Thread(new ClientHandler(socket, handler));
                t.start();
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

