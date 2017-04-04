/**
 * Created by Martin on 03-04-2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
        primaryStage.setTitle("4 på stribe");
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.show();
    }

    // override void stop
}
