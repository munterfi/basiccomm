package ch.munterfi.basiccomm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Message send(Message message) throws IOException {
        try (Socket socket = new Socket(host, port); InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            new MessageWriter(os).write(message);
            return new MessageReader(is).read();
        }
    }
}