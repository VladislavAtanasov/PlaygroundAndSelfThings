package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        VBox layout1 = new VBox(20);
        Button but1 = new Button("Go to scene 2");
        but1.setOnAction(e -> AlertBox.display("My title", "This is alert label window!"));
        layout1.getChildren().addAll(but1);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 400, 400);

        window.setScene(scene1);
        window.setTitle("Buttons FTW");
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
