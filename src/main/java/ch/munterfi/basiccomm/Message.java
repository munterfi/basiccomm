package ch.munterfi.basiccomm;

import java.nio.charset.StandardCharsets;

public record Message(byte[] data) {

    public String toString() {
        return new String(data, StandardCharsets.UTF_8);
    }
}
