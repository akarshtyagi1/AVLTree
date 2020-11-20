package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //Top heading
        AVLTree tree = new AVLTree();
        BorderPane bPane = new BorderPane();
        VBox top = new VBox();
        HBox pane = new HBox();

        pane.setPrefHeight(70);
        pane.setMaxWidth(99999.999d);
        BackgroundFill bFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(bFill);
        pane.setBackground(background);

        Label heading = new Label("AVL Tree");
        heading.setFont(Font.font("Times New Roman", FontWeight.BOLD,32));
        heading.setPadding(new Insets(10));
        heading.setTextFill(Color.ANTIQUEWHITE);
        pane.setAlignment(Pos.CENTER);

        pane.getChildren().add(heading);
        top.getChildren().add(pane);
        bPane.setTop(top);


        //Center
        Pane center = new Pane();
        BackgroundFill cFill = new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY,Insets.EMPTY);
        Background cbackground = new Background(cFill);
        center.setBackground(cbackground);
        bPane.setCenter(center);

        Pane pane1= new Pane();
//        dialogPane.setHeaderText("Pre-Order");
        BackgroundFill cFill1= new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY,Insets.EMPTY);
        Background oBackground= new Background(cFill1);
        pane1.setBackground(oBackground);
        pane1.setPrefHeight(400);
        pane1.setPrefWidth(200);
        Button getOrder= new Button("Show Order");
        getOrder.setOnAction(e->{
            System.out.println("\nInorder:");
            tree.printInorder(tree.root);
            System.out.println("\nPostorder: ");
            tree.printPreorder(tree.root);
            System.out.println( "\nPreorder");
            tree.printPostorder(tree.root);
        });
        pane1.getChildren().addAll(getOrder);
        center.getChildren().addAll(pane1);



        //bottomBar
        VBox bottom = new VBox();
        bottom.setPadding(new Insets(5));
        HBox bBox = new HBox();
        bBox.setAlignment(Pos.CENTER);
        bBox.setSpacing(5);
        bBox.setPrefSize(800,100);
        bottom.setBackground(background);


        TextField keyValue = new TextField();
        keyValue.setMaxWidth(300);
        Button insert = new Button("Insert");
        Button search = new Button("Search");
        Button delete = new Button("Delete");
        bBox.getChildren().addAll(keyValue,insert,search,delete);
        bottom.getChildren().addAll(bBox);
        bPane.setBottom(bottom);


        EventHandler<ActionEvent> handler = e ->{
            int key = Integer.parseInt(keyValue.getText());
          if(e.getSource() == insert){
              tree.insert(key,center);
              tree.printTreeLevelOrder();
          }else if(e.getSource() == delete){
              tree.delete(key,center);
              tree.printTreeLevelOrder();
          }else if(e.getSource() == search){
              tree.search(tree.root,key);
          }
            keyValue.clear();
        };


        insert.setOnAction(handler);
        delete.setOnAction(handler);
        search.setOnAction(handler);


        stage.setTitle("AVL Tree");
        Scene scene = new Scene(bPane,1200,800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
