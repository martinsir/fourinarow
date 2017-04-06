package controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.AppClientListener;
import model.AppServerThread;

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
    private boolean isGameOn;
    private int counter =0;
    AppServerThread appServerThread;

    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
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
    private GridPane gridPane;

    @FXML
    private Button connect;
    @FXML
    private Button logOut;
    @FXML
    private Button chooseName;
    @FXML
    private Button sendMessage;
    @FXML
    private TextField textFieldWrite;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField userNameField;
    @FXML
    private TextArea userNameOnline;

    @FXML
    public void exitApp(MouseEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void connectToServer(MouseEvent event) {
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

            while(!isGameOn){
                if(counter%2 == 0){
                    //get first user socket.lockaport fx
                   // switch turn

                }
                if(counter%2 == 1){
                    //second user
                    //first user
                }
                counter++;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // bruges til at sende data til serveren
    public void sendMessage() {
        //observer listener?
        try {
            outputStream.writeUTF(textFieldWrite.getText());
            outputStream.flush();
            textFieldWrite.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(MouseEvent event) {
        try {
            outputStream.writeUTF("QUIT");
            outputStream.flush();
            exitApp(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ændre navnet til det foretrukkende hvis det er optaget
    public void chooseName() throws IOException {
        try {
            outputStream.writeUTF("user_name"+userNameField.getText());
            outputStream.flush();
            userNameOnline.setText(userNameField.getText());

            if (isNameTaken()==true){
                textArea.appendText("Name is taken.\n");
                chooseName.setDisable(false);
            }else if (isNameTaken()==false) {
                userNameOnline.setText(userNameField.getText());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //button method click handler ActionEvent
    //TODO findWinner, startGame, buttonHandler, logOut, playerTurn
    //find 4 in a row verticle, horizontal, incline and decline


    /*Denne metode sætter O tegnet og en farve mellem rød og blå.
    * metoden er ikke færdig, da man kan spille både som rød og blå*/
    public void buttonHandleronAction(MouseEvent event) {

        Button clickedButton = ((Button) event.getTarget());
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer){
            clickedButton.setText("O");
            clickedButton.setTextFill(Color.RED);
            isFirstPlayer = false;
        }else if("".equals(buttonLabel)&& !isFirstPlayer) {
            clickedButton.setText("O");
            clickedButton.setTextFill(Color.BLUE);
            isFirstPlayer = true;
        }
        findWinner();

    }

    /*Denne metode er heller ikke færdig. Men nåede at tilføje kolonne vinder træk, der mangler række og på skrå -
    skå skal laves to gange for hver sin side (op fra ned venstre side, og opfra ned højre)*/
    private boolean findWinner() {
        //column
        if (""!=button1.getText() && button1.getText() ==button2.getText() && button2.getText() ==button3.getText() && button3.getText()==button4.getText()) {
            System.out.println("test ");
            textArea.setText(isFirstPlayer + "Won!");


        return true;}

        return false;
    }
    //start spill /nyt spill
    public void startGame(MouseEvent event) {
        ObservableList<Node> buttons = gridPane.getChildren();
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

    public TextField getUserNameField() {
        return userNameField;
    }

    public TextArea getUserNameOnline() {
        return userNameOnline;
    }

    public void setUserNameField(TextField userNameField) {
        this.userNameField = userNameField;
    }

    public void setUserNameOnline(TextArea userNameOnline) {
        this.userNameOnline = userNameOnline;
    }

    public TextField getTextFieldWrite() {
        return textFieldWrite;
    }

    public void setTextFieldWrite(TextField textFieldWrite) {
        this.textFieldWrite = textFieldWrite;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
