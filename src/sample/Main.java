package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        HBox hbox = new HBox();
        AVLTree tree = new AVLTree();
        Label insert = new Label("Insert Node: ");
        TextField Insert = new TextField();
        Button Enter = new Button("ENTER");
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.getChildren().addAll(insert,Insert,Enter);

        Enter.setOnAction( e ->{
            tree.insert(Integer.parseInt(Insert.getText()));
            Insert.clear();
            tree.printTreeLevelOrder();
        });


        pane.getChildren().add(hbox);
        stage.setTitle("AVL Tree");
        stage.setScene(new Scene(pane,600,800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
