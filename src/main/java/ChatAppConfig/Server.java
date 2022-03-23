package ChatAppConfig;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Getter
@Setter
public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try {
            while(!serverSocket.isClosed()){
               Socket socket =  serverSocket.accept();
                System.out.println("A new client has connected!");

                ClientHandler clientHandler= new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {
           e.printStackTrace(); //correct to sout if issues
        }
}

    public void closeServerSocket(){  // to avoid nested try catch to shut down if error occur
        try {
            if (serverSocket != null) {
                serverSocket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

