package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();
        VBox vbox = new VBox();
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
        Button Enter = new Button("Enter");
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.getChildren().addAll(insert,Insert,Enter);

        Label delete = new Label("Delete Node: ");
        TextField Delete = new TextField();
        Button D_Enter = new Button("Delete");
        hbox2.setSpacing(5);
        hbox2.setPadding(new Insets(5,5,5,5));
        hbox2.getChildren().addAll(delete,Delete,D_Enter);
        hbox2.setTranslateY(50);

        Label search= new Label("Search Node: ");
        TextField Search= new TextField();
        Button S_Enter = new Button("Search");
        hbox3.setSpacing(5);
        hbox3.setPadding(new Insets(5,5,5,5));
        hbox3.getChildren().addAll(search, Search, S_Enter);
        hbox3.setTranslateY(100);

        Button printButton=new Button("Print");
        Button clearButton=new Button("Clear");

        clearButton.setOnAction(e-> {
            System.out.println("button worked");
            stage.setScene(new Scene(new Pane(),600,800));
        });
//        ToggleGroup toggleGroup= new ToggleGroup();
//        RadioButton  degree3= new RadioButton("Max Degree= 3");
//        RadioButton  degree4= new RadioButton("Max Degree= 4");
//        RadioButton  degree5= new RadioButton("Max Degree= 5");
//        RadioButton  degree6= new RadioButton("Max Degree= 6");
//        RadioButton  degree7= new RadioButton("Max Degree= 7");
//        degree3.setToggleGroup(toggleGroup);
//        degree4.setToggleGroup(toggleGroup);
//        degree5.setToggleGroup(toggleGroup);
//        degree6.setToggleGroup(toggleGroup);
//        degree7.setToggleGroup(toggleGroup);
//
//
//        vbox.setPadding(new Insets(10));
//        vbox.getChildren().addAll(degree3,degree4,degree5,degree6,degree7);

        hbox4.setPadding(new Insets(10));
        hbox4.setTranslateX(350);
        hbox4.getChildren().addAll(printButton,clearButton, vbox);

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

        S_Enter.setOnAction(e->{
            tree.search(tree.root,Integer.parseInt(Search.getText()));
        });


        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(hbox,hbox2,hbox3,hbox4,bottom);
        stage.setTitle("AVL Tree");
        Scene scene = new Scene(pane,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
