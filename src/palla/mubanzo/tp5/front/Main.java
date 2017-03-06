package palla.mubanzo.tp5.front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("vueTP5.fxml"));
        primaryStage.setTitle("Mot Croisés");
        primaryStage.setScene(new Scene(root, 280, 330));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
