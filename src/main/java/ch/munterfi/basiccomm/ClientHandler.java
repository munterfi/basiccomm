package ch.munterfi.basiccomm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket socket;
    private final MessageHandler messageHandler;

    public ClientHandler(Socket socket, MessageHandler messageHandler) {
        this.socket = socket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            var request = new MessageReader(is).read();
            var response = messageHandler.apply(request);
            new MessageWriter(os).write(response);
        } catch (IOException e) {
            throw new RuntimeException("Error while handling client", e);
        }
    }
}
