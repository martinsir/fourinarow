/**
 * Created by Martin on 03-04-2017.
 */

import controller.AppClientController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AppClient extends Application {
    AppClientController appClientController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
        primaryStage.setTitle("4 på stribe");
        primaryStage.setScene(new Scene(root,600,600));




        primaryStage.setResizable(false);
        primaryStage.show();
    }
    // override void stop

}
