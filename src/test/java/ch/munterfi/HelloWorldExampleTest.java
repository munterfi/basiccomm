package ch.munterfi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldExampleTest {

    HelloWorldExample example;

    @BeforeEach
    void setUp() {
        example = new HelloWorldExample();
        example.runServer();
    }

    @AfterEach
    void tearDown() {
        example.stopServer();
    }

    @Test
    void helloWorld() throws IOException {
        assertEquals("world", example.runClient("hello").toString());
    }

    @Test
    void fooBar() throws IOException {
        assertEquals("Unknown request!", example.runClient("fooBar").toString());
    }
}