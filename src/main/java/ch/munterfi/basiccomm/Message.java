package ch.munterfi.basiccomm;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public record Message(byte[] data) {
    private static final int HEADER_SIZE = Integer.BYTES;

    public int getSize() {
        return HEADER_SIZE + data.length;
    }

    public byte[] toByteArray() {
        var bb = ByteBuffer.allocate(getSize());
        bb.putInt(data.length);
        bb.put(data);
        return bb.array();
    }

    public static Message fromByteArray(byte[] bytes) {
        var bb = ByteBuffer.wrap(bytes);
        int size = bb.getInt();
        var data = new byte[size];
        bb.get(data);
        return new Message(data);
    }

    public String toString() {
        return new String(data, StandardCharsets.UTF_8);
    }
}
