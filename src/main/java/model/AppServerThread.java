package model;

import controller.AppClientController;
import controller.AppServerController;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Martin on 03-04-2017.
 */
public class AppServerThread extends Thread {

    private AppServerController appServerController = null;
    private Socket socket; //Socket(InetAddress address, int port, InetAddress localAddr, int localPort)
    private InetAddress address;
    private int port;
    private String userName;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    boolean isConnected; // no functionality
    boolean isNameTaken;

    public AppServerThread(AppServerController server, Socket socket){
        this.appServerController = server;
        this.socket = socket;
    }

    public void run(){
        while(!socket.isClosed()){
            String inputLive;
            try {
                inputLive = inputStream.readUTF();
                if (inputLive.startsWith("user_name")) {
                    clientChangeUserName(inputLive);
                }else if(inputLive.equals("QUIT")) {
                    String exitCmdText;
                    exitCmdText = inputLive.replace("QUIT", "Logged off");
                    appServerController.handler(userName, exitCmdText);
                    appServerController.getClients().remove(this);
                    appServerController.sendOnlineUsers();
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    break;
                }else {
                    appServerController.handler(userName, inputLive);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
                break;
            }
        }
    }

    public void clientChangeUserName (String input){
        isNameTaken = false;
        String desiredUsername = input.replace("user_name", "");
        for (AppServerThread client : appServerController.getClients()) {
            if (client.getUserName().equals(desiredUsername)) {
                isNameTaken=true;
            }
        }
        if (isNameTaken == true) {
            send("J_ERR "+desiredUsername+" is already taken");
        }
        if (isNameTaken == false) {
            userName=desiredUsername;
            appServerController.sendOnlineUsers();
        }

    }

    public void send(String msg){
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws IOException {
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}