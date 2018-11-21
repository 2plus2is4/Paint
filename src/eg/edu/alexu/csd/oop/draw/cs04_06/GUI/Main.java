package eg.edu.alexu.csd.oop.draw.cs04_06.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        //fxmlLoader.setRoot(new BorderPane());
        Parent root = (Parent) fxmlLoader.load();
        Controller c = fxmlLoader.getController();
        c.begin(primaryStage);
        primaryStage.setTitle("Paynet");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 1067, 670);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
