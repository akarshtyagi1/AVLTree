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

        left.setPadding(new Insets(5,10,5,10));
        bPane.setLeft(left);
        Pane orderPane= new Pane();
        orderPane.setMinWidth(150);
        orderPane.setMaxWidth(150);
        orderPane.setMinHeight(bPane.getHeight());
        orderPane.setMaxHeight(bPane.getHeight());
        BackgroundFill oFill= new BackgroundFill(Color.rgb(232,232,232), CornerRadii.EMPTY,Insets.EMPTY);
        Background oBackground= new Background(oFill);
        orderPane.setBackground(oBackground);
//        Label orders= new Label("Orders");
        Text text = new Text("Orders");
        text.setStyle("-fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);-fx-stroke: black; -fx-stroke-width: 1;");
        text.setX(40); text.setY(30); text.setFill(Color.rgb(145,66,94));
        text.setStrokeWidth(2);
//        orders.translateXProperty().bind(left.widthProperty().divide(3));
        orderPane.getChildren().addAll( text);
        left.getChildren().addAll(orderPane);
        left.setSpacing(200);


//        public void showOrderPane(Pane pane){
//            tree.addOrderToPane(orderPane);
//        }

        pane.setPrefHeight(70);
        pane.setMaxWidth(99999.999d);
        BackgroundFill bFill = new BackgroundFill(Color.rgb(48,71,94), CornerRadii.EMPTY,Insets.EMPTY);
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
        BackgroundFill cFill = new BackgroundFill(Color.rgb(240,84,84,0.9), CornerRadii.EMPTY,Insets.EMPTY);
        Background cbackground = new Background(cFill);
        center.setBackground(cbackground);
        bPane.setCenter(center);

        Pane detailsPane= new Pane();
        BackgroundFill cFill1= new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY,Insets.EMPTY);
        Background dBackground= new Background(cFill1);
        detailsPane.setBackground(dBackground);
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
//        keyValue.setStyle("-fx-font-family: Quicksand;-fx-font-size: 18; -fx-padding: 1,1,1,1;-fx-border-color: grey; -fx-border-width: 2;-fx-border-radius: 1;-fx-border: gone; -fx-background-color: transparent; -fx-text-fill: grey;");
        keyValue.setMaxWidth(300);
        Button insert = new Button("Insert");
        Button search = new Button("Search");
        Button delete = new Button("Delete");
        insert.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #186A3B   0%, #145A32 100%),#229954 , #1E8449,radial-gradient(center 50% 50%, radius 100%, #229954, #1E8449);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em; -fx-text-fill:white;");
        search.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024, #d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em; -fx-text-fill:white;");
        delete.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #78281F 0%, #7B241C 100%),#922B21, #C0392B ,radial-gradient(center 50% 50%, radius 100%, #CB4335, #922B21);-fx-effect: dropshadow( gaussian , #CB4335 , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em; -fx-text-fill:white;");
        bBox.getChildren().addAll(keyValue,insert,search,delete);
        bottom.getChildren().addAll(bBox, detailsPane);
        bPane.setBottom(bottom);


        EventHandler<ActionEvent> handler = e ->{
            int key = Integer.parseInt(keyValue.getText());
          if(e.getSource() == insert){
              keyValue.clear();
              tree.insert(key,center);
              tree.printTreeLevelOrder();
              tree.addOrderToPane(orderPane);
              tree.addHeightToDetailPane(tree.root, detailsPane);
          }else if(e.getSource() == delete){
              keyValue.clear();
              tree.delete(key,center);
              tree.printTreeLevelOrder();
              tree.addOrderToPane(orderPane);
              tree.addHeightToDetailPane(tree.root, detailsPane);
          }else if(e.getSource() == search){
              keyValue.clear();
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
        Scene scene = new Scene(bPane,1100,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
