package ChatAppConfig;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.configuration.IMockitoConfiguration;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Disabled.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientTest {
           Socket socket ;
           BufferedWriter bufferedWriter ;
           BufferedReader bufferedReader ;
           String username ;

    ClientTest(Socket socket, String username) throws IOException {
        this.socket = socket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = username;
    }

    @Test
    void shouldCheck() throws IOException {
        Client client = mock(Client.class);

        String messageToSend = "";
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                messageToSend = scanner.nextLine();
                bufferedWriter.write(username + " : " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            client.closeEverything(socket, bufferedReader, bufferedWriter);
        }
      //  when(client.sendMessage(messageToSend))
    }
}