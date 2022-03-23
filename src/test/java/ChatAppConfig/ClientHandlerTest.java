package ChatAppConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.nio.file.Paths;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {

    @Test
    void checkConstructor(){
        Socket socket = new Socket();
        ClientHandler actualClientHandler = new ClientHandler(socket);
        assertTrue(ClientHandler.clientHandlers.isEmpty());
        assertSame(socket,actualClientHandler.getSocket());
    }

    @Test
    void checkConnectionIsClosed(){
        ClientHandler clientHandler = new ClientHandler(new Socket());
        Socket socket = new Socket();
        BufferedReader bufferedReader = new BufferedReader(new StringReader("hello"));
        clientHandler.closeEverything(socket,bufferedReader,new BufferedWriter(new StringWriter()));
        assertTrue(ClientHandler.clientHandlers.isEmpty());
    }

    @Test
    void checkConnectionIsClosedWhenSocketIsNull(){
        ClientHandler clientHandler = new ClientHandler(new Socket());
        BufferedReader bufferedReader = new BufferedReader(new StringReader("hello"));
        clientHandler.closeEverything(null,bufferedReader,new BufferedWriter(new StringWriter()));
        assertTrue(ClientHandler.clientHandlers.isEmpty());
    }
    @Test
    void checkConnectionIsClosedWhenBufferReaderIsNull(){
        ClientHandler clientHandler = new ClientHandler(new Socket());
        Socket socket = new Socket();
        clientHandler.closeEverything(socket,null,new BufferedWriter(new StringWriter()));
        assertTrue(ClientHandler.clientHandlers.isEmpty());
    }

}