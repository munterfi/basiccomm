# basiccomm

This project provides a Java implementation of basic client-server / consumer-service communication using sockets.

## Public API

The public API consists of the following classes and interface:

- `Client`: sends requests in the form of messages from a consumer to the server / service listening on a port.
- `Server`: binds to a port on the host using a `ServerSocket` and waits for connections.
- `Message`: record class that stores the data (payload) along with a header that contains the size of the
  message as a byte array.
- `MessageHandler`: interface that determines how the server should respond to a request message with a response
  message.

## Internal classes

The internal classes are used by the public API. They include:

- `ClientHandler`: accepts a new `ClientSocket` from the server when a client makes a request and answers the request in
  a separate thread, allowing the server to remain available for further requests.
- `MessageWriter`: writes the bytes of a message to an output stream.
- `MessageReader`: first reads the header containing the message size from a socket output stream, then reads the bytes
  of the entire message (based on the size) and converts them into a `Message`.
