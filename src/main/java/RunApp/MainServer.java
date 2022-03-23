package RunApp;

import ChatAppConfig.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        Server server = new Server(serverSocket);
        server.startServer();

    }
}
