package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Login Form");

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(8);

        Label nameLabel = new Label("Username: ");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField username = new TextField();
        username.setPromptText("потребителско име");
        GridPane.setConstraints(username, 1, 0);

        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0, 1);

        TextField password = new TextField();
        password.setPromptText("парола");
        GridPane.setConstraints(password, 1, 1);

        Button login = new Button("Влез");
        GridPane.setConstraints(login, 1, 2);

        CheckBox check1 = new CheckBox("Сок");
        GridPane.setConstraints(check1, 4, 3);
        CheckBox check2 = new CheckBox("Круши");
        GridPane.setConstraints(check2, 2, 3);
        CheckBox check3 = new CheckBox("Ябълки");
        GridPane.setConstraints(check3, 3, 3);

        ChoiceBox<Integer> ch = new ChoiceBox<>();
        for (int i = 1; i < 11; i++)
            ch.getItems().add(i);

        GridPane.setConstraints(ch, 1, 4);

        Button bt2 = new Button("SELECT");
        bt2.setOnAction(e -> System.out.println(ch.getValue()));
        GridPane.setConstraints(bt2, 1, 5);

        ComboBox<String> cb = new ComboBox<>();
        cb.setEditable(true);
        cb.setPromptText("What do you choose?");
        cb.getItems().addAll("Potatoes", "Tomatoes", "Apples");

        GridPane.setConstraints(cb,1, 6);
//        ToggleButton tg = new ToggleButton();
//        GridPane.setConstraints(tg, 1, 2);
//        tg.setMaxWidth(50);
//
//        Image unselected = new Image("http://www.thecinemas.aw/main/images/login-button.png");
//        ImageView iv = new ImageView(unselected);
//        tg.setGraphic(iv);
        grid.getChildren().addAll(passLabel, cb, bt2, login, check1, check2, check3, ch, password, nameLabel, username);

        Scene scene = new Scene(grid, 600, 300);

        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
