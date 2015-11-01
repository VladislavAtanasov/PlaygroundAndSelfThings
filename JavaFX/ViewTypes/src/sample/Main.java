package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.color.ProfileDataException;
import java.util.Observable;

public class Main extends Application {

    Stage window;
    ListView<String> list;
    TreeView<String> tree;
    TableView<Product> table;
    TextField nameInput, priceInput, quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Views");

        list = new ListView<>();
        list.getItems().add("Youtube");
        list.getItems().add("Google");
        list.getItems().add("Gong");
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TreeItem<String> root, sites, languages;
        root = new TreeItem<>();
        root.setExpanded(true);

        sites = makebranch("Sites", root);
        makebranch("Facebook", sites);
        makebranch("Google", sites);
        makebranch("Sportal", sites);


        languages = makebranch("Languages", root);
        makebranch("Java", languages);
        makebranch("Python", languages);
        makebranch("C++", languages);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(200);

        TableColumn<Product, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setMinWidth(200);

        TableColumn<Product, String> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setMinWidth(200);

        HBox fields = new HBox();
        fields.setPadding(new Insets(10, 10, 10, 10));
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addBut = new Button("Add");
        Button deleteBut = new Button("Delete");

        fields.getChildren().addAll(nameInput, priceInput, quantityInput, addBut, deleteBut);

        addBut.setOnAction(e -> addRow());
        deleteBut.setOnAction(e -> deleteRow());

        table = new TableView<>();
        table.getItems().addAll(getProduct());
        table.getColumns().addAll(nameCol, priceCol, quantityCol);

        VBox layout = new VBox();
        layout.getChildren().addAll(list, tree, table, fields);

        window.setScene(new Scene(layout, 700, 275));
        window.show();
    }

    private TreeItem<String> makebranch(String text, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(text);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    private ObservableList<Product> getProduct(){
        ObservableList<Product> list = FXCollections.observableArrayList();
        list.add(new Product("Laptop", 1000.0, 2));
        list.add(new Product("Cooker", 455.2, 4));
        list.add(new Product("Washing Machine", 801.0, 1));
        list.add(new Product("Microwave", 312.0, 1));

        return list;
    }

    private void addRow(){
        Product mine = new Product();
        mine.setName(nameInput.getText());
        mine.setPrice(Double.parseDouble(priceInput.getText()));
        mine.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(mine);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    private void deleteRow(){
        ObservableList<Product>  all = table.getItems();
        ObservableList<Product> selected = table.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
