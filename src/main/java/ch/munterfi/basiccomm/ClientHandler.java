package ch.munterfi.basiccomm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket socket;
    private final MessageHandler handler;

    public ClientHandler(Socket socket, MessageHandler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            var request = new MessageReader(is).readMessage();
            var response = handler.handleMessage(request);
            new MessageWriter(os).writeMessage(response);
        } catch (IOException e) {
            throw new RuntimeException("Error while handling client", e);
        }
    }
}
