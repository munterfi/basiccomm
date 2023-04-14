package ch.munterfi.basiccomm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class MessageWriter {

    private final DataOutputStream os;

    public MessageWriter(OutputStream os) {
        this.os = new DataOutputStream(os);
    }

    public void write(Message message) throws IOException {
        var data = message.data();
        os.writeInt(data.length);
        os.write(data);
        os.flush();
    }
}
