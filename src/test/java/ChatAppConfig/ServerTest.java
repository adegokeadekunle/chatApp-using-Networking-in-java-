package ChatAppConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
   @Test
    void shouldCheckSocketConnection() throws IOException {
       ServerSocket serverSocket = new ServerSocket();
       ServerSocket serverSocket1 =( new Server(serverSocket)).getServerSocket();
       assertSame(serverSocket,serverSocket1);
       assertNull(serverSocket1.getChannel());
       assertFalse(serverSocket1.isClosed());

   }
   @Test
    void shouldCheckServerHasStarted() throws IOException {
       ServerSocket serverSocket = new ServerSocket();
       Server server = new Server(serverSocket);
       server.startServer();
       assertSame(serverSocket,server.getServerSocket());
   }
}