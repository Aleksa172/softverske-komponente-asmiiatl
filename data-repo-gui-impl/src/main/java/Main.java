package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panels.MainPane;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            MainPane root = new MainPane(primaryStage);
            Scene scene = new Scene(root,600,600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
