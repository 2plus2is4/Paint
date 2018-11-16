package eg.edu.alexu.csd.oop.draw;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import eg.edu.alexu.csd.oop.draw.cs04.*;

public class controller extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1000,800);


        return root;
    }

    public static void main(String[] args){
        launch(args);
    }

    public static class lancher{
        public static void main(String[] args) {
            controller.main(args);
        }
    }
}
