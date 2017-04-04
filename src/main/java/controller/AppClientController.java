package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.AppClientListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by Martin on 03-04-2017.
 */
public class AppClientController implements Initializable {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean isNameTaken;

    @FXML
    private Button connect;

    @FXML
    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void connectToServer(ActionEvent actionEvent) {
        try {
            //String set name?
            //String username = "test name ";
            String ip = "localhost";
            int port = 1234;
            socket = new Socket(ip, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            AppClientListener listener = new AppClientListener(this);
            Thread t1 = new Thread(listener);
            t1.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        //observer listener?
        try {
            outputStream.writeUTF("getText");
            outputStream.flush();
            //set""
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        try {
            outputStream.writeUTF("QUIT");
            outputStream.flush();
            exitApp(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean isNameTaken() {
        return isNameTaken;
    }

    public void setNameTaken(boolean nameTaken) {
        isNameTaken = nameTaken;
    }

}
