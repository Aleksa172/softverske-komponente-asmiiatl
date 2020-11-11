package panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.RepoType;

import java.io.File;

public class MainPane extends BorderPane {

    BorderPane bp = new BorderPane();
    HBox hb1 = new HBox();
    ComboBox<RepoType> cbRepoType = new ComboBox<>();
    Button btRepoOpen = new Button("Open");

    FileChooser fc = new FileChooser();

    Stage primaryStage;

    public MainPane(Stage stage){
        this.primaryStage = stage;

        btRepoOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fc.setTitle("Choose Repo Location");
                File chosenDirectory = fc.showOpenDialog(MainPane.this.primaryStage);
                if(!chosenDirectory.isDirectory()) {
                    System.out.println("Please choose a valid directory");
                    return;
                }
                System.out.println("Folder chosen "+chosenDirectory.getName());
            }
        });

        hb1.getChildren().addAll(cbRepoType, btRepoOpen);
        bp.setTop(hb1);

    }
}
