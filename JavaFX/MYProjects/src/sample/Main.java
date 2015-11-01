package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main extends Application {

    Stage window;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;

        VBox layout1 = new VBox(20);
        Button but1 = new Button("Go to scene 2");
        //but1.setOnAction(e -> window.setScene(scene2));
        layout1.getChildren().addAll(but1);
        scene1 = new Scene(layout1, 400, 400);

        StackPane layout2 = new StackPane();
        Button but2 = new Button("Go to scene 1");
        but2.setOnAction(e -> window.setScene(scene1));
        layout2.getChildren().add(but2);
        scene2 = new Scene(layout2, 400, 400);

        window.setScene(scene1);
        window.setTitle("Buttons FTW");
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
