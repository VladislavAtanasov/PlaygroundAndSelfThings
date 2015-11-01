package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    public static void display(String title, String mes){
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(300);
        window.initModality(Modality.APPLICATION_MODAL);

        Label textBox = new Label();
        textBox.setText(mes);

        Button but = new Button("Close the window!");
        but.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(textBox, but);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

    }

}
