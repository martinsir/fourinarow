package model;

import controller.AppClientController;

import java.io.DataInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Martin on 03-04-2017.
 */
public class AppClientListener implements Runnable{

    private final AppClientController appClientController;
    private DataInputStream inputStream;

    public AppClientListener(AppClientController appClientController) {
        this.inputStream = appClientController.getInputStream();
        this.appClientController = appClientController;
    }

    @Override
    public void run() {
        while (true) {
            String formatDateTime=dateTime();
            try {
                String msg = inputStream.readUTF();
                System.out.println("msg:  " +msg);

                if (msg.contains("Logged off")) {
                                  //msg = msg.substring()
                                //do something? quit?
                }
                if (msg.contains("J_ERR")){
                    appClientController.setNameTaken(true);
                }
                if (msg.contains("user_name")) {
                                        // msg = msg.substring?
                } else if (!msg.isEmpty()){
                                         //append (formatted time + msg)
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String dateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //dd-MM-yyyy
        return now.format(formatter);
    }

}
