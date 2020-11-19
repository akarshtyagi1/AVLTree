package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        HBox bottom = new HBox();
        AVLTree tree = new AVLTree();
        BackgroundFill bFill = new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(bFill);
        pane.setBackground(background);

        Label Height = new Label("Height: " + (tree.getHeight(tree.root)-1));
        bottom.getChildren().addAll(Height);
        bottom.setPadding(new Insets(5));
        bottom.setTranslateY(600);

        Label insert = new Label("Insert Node: ");
        TextField Insert = new TextField();
        Button Enter = new Button("ENTER");
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.getChildren().addAll(insert,Insert,Enter);

        Label delete = new Label("Delete Node: ");
        TextField Delete = new TextField();
        Button D_Enter = new Button("ENTER");
        hbox2.setSpacing(5);
        hbox2.setPadding(new Insets(5,5,5,5));
        hbox2.getChildren().addAll(delete,Delete,D_Enter);
        hbox2.setTranslateY(50);

        Enter.setOnAction( e ->{
            tree.insert(Integer.parseInt(Insert.getText()),pane);
            Insert.clear();
            Height.setText("Height: " + (tree.getHeight(tree.root)-1));
            tree.printTreeLevelOrder();
        });
        D_Enter.setOnAction(e->{
            tree.delete(Integer.parseInt(Delete.getText()));
            Delete.clear();
        });

        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(hbox,hbox2,bottom);
        stage.setTitle("AVL Tree");
        Scene scene = new Scene(pane,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
