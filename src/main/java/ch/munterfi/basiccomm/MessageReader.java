package ch.munterfi.basiccomm;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

class MessageReader {

    private final DataInputStream is;

    public MessageReader(InputStream is) {
        this.is = new DataInputStream(is);
    }

    public Message read() throws IOException {
        int messageSize = is.readInt();
        var data = new byte[messageSize];
        int totalRead = 0;
        while (totalRead < messageSize) {
            int read = is.read(data, totalRead, messageSize - totalRead);
            if (read == -1) {
                throw new IOException("Unexpected end of stream");
            }
            totalRead += read;
        }
        return Message.fromByteArray(data);
    }
}
