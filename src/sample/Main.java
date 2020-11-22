package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
        VBox left= new VBox();

        bPane.setLeft(left);
        Pane orderPane= new Pane();
        orderPane.setMinWidth(150);
        Label orders= new Label("Orders");
        orders.translateXProperty().bind(left.widthProperty().divide(3));
        orderPane.getChildren().addAll(orders);
        left.getChildren().addAll(orderPane);
        left.setSpacing(200);


//        public void showOrderPane(Pane pane){
//            tree.addOrderToPane(orderPane);
//        }

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

        Pane detailsPane= new Pane();
        BackgroundFill cFill1= new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY,Insets.EMPTY);
        Background oBackground= new Background(cFill1);
        detailsPane.setBackground(oBackground);
//        detailsPane.setPrefHeight(200);
//        detailsPane.setPrefWidth(50);
//        Button getOrder= new Button("Show Order");
//        getOrder.setOnAction(e->{
//            tree.addOrderToPane(detailsPane);
//        });
//        detailsPane.getChildren().addAll(label);
//        center.getChildren().addAll(detailsPane);



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
        bottom.getChildren().addAll(bBox, detailsPane);
        bPane.setBottom(bottom);


        EventHandler<ActionEvent> handler = e ->{
            int key = Integer.parseInt(keyValue.getText());
          if(e.getSource() == insert){
              tree.insert(key,center);
              tree.printTreeLevelOrder();
              tree.addOrderToPane(orderPane);
              tree.addHeightToDetailPane(tree.root, detailsPane);
          }else if(e.getSource() == delete){
              tree.delete(key,center);
              tree.printTreeLevelOrder();
              tree.addOrderToPane(orderPane);
              tree.addHeightToDetailPane(tree.root, detailsPane);
          }else if(e.getSource() == search){
              if(tree.search(tree.root,key) == null){
                  System.out.println(key + " not Found");
                  tree.addOrderToPane(orderPane);
                  tree.addHeightToDetailPane(tree.root, detailsPane);
              }
          }
            keyValue.clear();
        };

        insert.setOnAction(handler);
        delete.setOnAction(handler);
        search.setOnAction(handler);

        stage.setTitle("AVL Tree");
        Scene scene = new Scene(bPane,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
