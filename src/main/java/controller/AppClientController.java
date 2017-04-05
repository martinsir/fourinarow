package controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    private boolean isFirstPlayer = true;

    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button b5;
    @FXML
    Button b6;
    @FXML
    Button b7;
    @FXML
    Button b8;
    @FXML
    Button b9;
    @FXML
    Button b10;
    @FXML
    Button b11;
    @FXML
    Button b12;
    @FXML
    Button b13;
    @FXML
    Button b14;
    @FXML
    Button b15;
    @FXML
    Button b16;
    @FXML
    Button b17;
    @FXML
    Button b18;
    @FXML
    Button b19;
    @FXML
    Button b20;
    @FXML
    Button b21;
    @FXML
    Button b22;
    @FXML
    Button b23;
    @FXML
    Button b24;
    @FXML
    Button b25;
    @FXML
    Button b26;
    @FXML
    Button b27;
    @FXML
    Button b28;
    @FXML
    Button b29;
    @FXML
    Button b30;
    @FXML
    Button b31;
    @FXML
    Button b32;
    @FXML
    Button b33;
    @FXML
    Button b34;
    @FXML
    Button b35;
    @FXML
    Button b36;
    @FXML
    Button b37;
    @FXML
    Button b38;
    @FXML
    Button b39;
    @FXML
    Button b40;
    @FXML
    Button b41;
    @FXML
    Button b42;

    @FXML
    private Button startGame;
    @FXML
    private GridPane board;

    @FXML
    private Button connect;

    @FXML
    public void exitApp(ActionEvent actionEvent) {
        //Platform.exit();
        buttonHandler(actionEvent);
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

    public void logOut(ActionEvent actionEvent) {
        try {
            outputStream.writeUTF("QUIT");
            outputStream.flush();
            exitApp(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //button method click handler ActionEvent

    public void buttonHandler(ActionEvent actionEvent){
        Button clickedButton = (Button) actionEvent.getTarget();
        String buttonLabel = clickedButton.getText();
        clickedButton.setText("test");

        if ("".equals(buttonLabel) && isFirstPlayer){
            clickedButton.setText("O");
            clickedButton.setTextFill(Color.RED);
            isFirstPlayer = false;
        }else if("".equals(buttonLabel)&& !isFirstPlayer) {
            clickedButton.setText("O");
            clickedButton.setTextFill(Color.BLUE);
            isFirstPlayer = true;
        }
        //find the winner
    }

    //TODO findWinner, startGame, buttonHandler, logOut
    //find 4 in a row verticle, horizontal, incline and decline


    private boolean findWinner() {
        //row
        if (""!=b1.getText() && b1.getText() ==b2.getText() && b2.getText() ==b3.getText()) {
        return true;}

        return false;
    }

    public void startGame(ActionEvent actionEvent) {
        ObservableList<Node> buttons = board.getChildren();
        buttons.forEach(button ->{
            ((Button)button).setText("");
        });
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
