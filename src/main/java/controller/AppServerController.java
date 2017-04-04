package controller;

import javafx.fxml.Initializable;
import model.AppServerThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Martin on 03-04-2017.
 */
public class AppServerController implements Initializable, Runnable {
    private List<AppServerThread> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private Thread thread;
    private boolean isConneted =false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ////

    }

    @Override
    public void run() {
        while(isConneted==true){
            try {
                sendOnlineUsers();
                //append serverstarted
                System.out.println("Waiting for cliets");
                addClient(serverSocket.accept());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

 //

    public void startServer() {

        if(isConneted==false) {
            try {
                serverSocket = new ServerSocket(1234); // get text int port
                this.thread = new Thread(this);
                this.thread.start();
                isConneted =true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient (Socket socket) throws Exception{
        // append J_OK if accepted by server
        AppServerThread serverThread = new AppServerThread(this, socket);
        clients.add(serverThread);
        try {
            serverThread.open();
            serverThread.start();
        }catch (IOException e){
            e.printStackTrace();
        }
        sendOnlineUsers();
    }

    public List<AppServerThread> getClients() {
        return clients;
    }

    public void sendOnlineUsers(){
      String userNameList = "user_name";
      for (AppServerThread user : clients){
          userNameList+= user.getUserName()+"\n";
      }
      for (AppServerThread user: clients){
          user.send(userNameList);
      }
      // set online user text
    }

    public synchronized  void handler(String id, String input){
        for (AppServerThread user: clients){
            user.send(id+ ": "+ input);
        }
        // append text on server side? record?
    }

}
