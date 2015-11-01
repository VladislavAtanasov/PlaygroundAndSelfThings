package sample;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Menu");

        Menu fileMenu = new Menu("_File");
        fileMenu.getItems().add(new MenuItem("New"));
        fileMenu.getItems().add(new MenuItem("Open"));
        fileMenu.getItems().add(new MenuItem("Settings"));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Exit"));


        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Undo"));
        editMenu.getItems().add(new MenuItem("Redo"));
        editMenu.getItems().add(new MenuItem("Copy"));
        MenuItem paste = new MenuItem("Paste");
        paste.setDisable(true);
        editMenu.getItems().add(paste);

        Menu settingsMenu = new Menu("_Settings");
        settingsMenu.getItems().add(new CheckMenuItem("Show Row Numbers"));
        CheckMenuItem check = new CheckMenuItem("Show Menu");
        check.setSelected(true);
        settingsMenu.getItems().add(check);

        Menu difficultyMenu = new Menu("_Difficulty");
        ToggleGroup group = new ToggleGroup();
        RadioMenuItem easy = new RadioMenuItem("Easy");
        RadioMenuItem medium = new RadioMenuItem("Medium");
        RadioMenuItem hard = new RadioMenuItem("Hard");
        easy.setToggleGroup(group);
        medium.setToggleGroup(group);
        hard.setToggleGroup(group);
        easy.setSelected(true);
        difficultyMenu.getItems().addAll(easy, medium, hard);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);

        TextField nameInput = new TextField();
        nameInput.setPromptText("username");
        GridPane.setConstraints(nameInput, 1, 1);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 2);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 2);

        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 3);


        VBox bindings = new VBox();
        TextField userText = new TextField();
        userText.setMaxWidth(200);
        Label firstLabel = new Label("Welcome to the site, ");
        Label secondLabel = new Label();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(firstLabel, secondLabel);
        bindings.getChildren().addAll(userText,hbox);
        secondLabel.textProperty().bind(userText.textProperty());
        GridPane.setConstraints(bindings, 0, 4);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, settingsMenu, difficultyMenu);
        GridPane.setHalignment(menuBar, HPos.LEFT);
        GridPane.setValignment(menuBar, VPos.TOP);


        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, menuBar, bindings);
        Scene scene = new Scene(grid, 600, 475);

        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
