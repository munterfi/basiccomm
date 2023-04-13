package ch.munterfi.basiccomm;

@FunctionalInterface
public interface MessageHandler {
    Message handleMessage(Message message);
}
