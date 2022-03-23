package ChatAppConfig;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

@Getter
@Setter
public class ClientHandler implements   Runnable{
    public static  ArrayList<ClientHandler> clientHandlers  = new ArrayList<>(); //to keep track of all the client so when a send a message we can loop through the list and send the messge to each client
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER : " + clientUsername + " has entered the chat!");

        } catch (IOException e) {
           closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient != null)
                broadcastMessage(messageFromClient);
                else  closeEverything(socket, bufferedReader, bufferedWriter);

            } catch (IOException e) {

                System.out.println("\n"+clientUsername+" has disconnected from server port : "+ socket.getLocalPort());
                break;
            }

        }
    }
    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler: clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                   clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);

            }

        }

    }
    public void removeClientHandler(){
        broadcastMessage("SERVER : "+clientUsername+ " has left the chat! ");
        clientHandlers.remove(this);


    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();

            }
            if (bufferedWriter != null) {
                bufferedWriter.close();

            }
            if (socket != null) {
                socket.close();

            }
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
