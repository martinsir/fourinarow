package model;

import controller.AppServerController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Martin on 03-04-2017.
 */
public class AppServerThread extends Thread {
    private AppServerController appServerController;
    private Socket socket; //Socket(InetAddress address, int port, InetAddress localAddr, int localPort)
    private InetAddress address;
    private int port;
    private String userName;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    boolean isConnected; // no functionality

    public AppServerThread(InetAddress address,int port, String userName){
        this. address =address;
        this. port = port;
        this. userName =userName;
    }

    public void run(){
        while(!isConnected){
            String inputCmd;
            try {
                inputCmd = inputStream.readUTF();
                //if user name start with JOIN then connect to server.
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void open(){

    }

}