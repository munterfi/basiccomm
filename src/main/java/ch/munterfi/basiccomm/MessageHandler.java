package ch.munterfi.basiccomm;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface MessageHandler extends UnaryOperator<Message> {
    Message apply(Message message);
}
