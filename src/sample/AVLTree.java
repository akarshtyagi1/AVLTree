package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.LinkedList;

public class AVLTree {

    AVLTreeNode root;
    float x=600;
    float y=60;
    ArrayList<Integer> inOrder= new ArrayList<>();
    ArrayList<Integer> preOrder= new ArrayList<>();
    ArrayList<Integer> postOrder= new ArrayList<>();
    Label inOrderLabel= new Label();
    Label preOrderLabel= new Label();
    Label postOrderLabel= new Label();

    //constructor
    public AVLTree() {
        this.root = new AVLTreeNode(-1);
    }

    //arg constructor
    public AVLTree(int rootValue) {
        this.root = new AVLTreeNode(rootValue);
    }

    //returns the height of the tree
    int getHeight(AVLTreeNode node) {
        if (node == null)
            return 0;

        return node.height;
    }

    //updates the height after a certain operation is performed
    int updateHeight(AVLTreeNode node) {
        if (node == null) return 0;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node.height;
    }

    //rotation of certain branches to balance the tree
    AVLTreeNode rotateRight(AVLTreeNode p_Node,AVLTreeNode node,Pane center) {
        if (node == null) return null;

        AVLTreeNode beta = node.left;
        AVLTreeNode t2 = beta.right;
        AVLTreeNode alpha = node.right;

        beta.right = node;
        node.left = t2;

        updateHeight(node);
        updateHeight(beta);

        //        if(beta.left != null){
//            beta.left.circle.setCenterX(beta.circle.getCenterX());
//            beta.left.circle.setCenterY(beta.circle.getCenterY());
//            //            beta.left.value.setTranslateX(beta.value.getTranslateX());
////            beta.left.value.setTranslateY(beta.value.getTranslateY());
////            beta.left.line.setStartX(node.circle.getCenterX());
////            beta.left.line.setStartY(node.circle.getCenterY());
////            beta.left.line.setEndX(beta.circle.getCenterX());
////            beta.left.line.setEndY(beta.circle.getCenterY(
//        }

        if(p_Node == node){
            beta.circle.centerXProperty().unbind();
            beta.circle.centerYProperty().unbind();
            beta.circle.setCenterX(center.getWidth()/2);
            beta.circle.setCenterY(60);
            beta.line.startXProperty().bind(beta.circle.centerXProperty());
            beta.line.startYProperty().bind(beta.circle.centerYProperty());
        }else{
            beta.circle.centerXProperty().bind(p_Node.circle.centerXProperty().add(node.circle.getCenterX() - p_Node.circle.getCenterX()));
            beta.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
            beta.line.startXProperty().bind(p_Node.circle.centerXProperty());
            beta.line.startYProperty().bind(p_Node.circle.centerYProperty());
        }
        //        beta.value.setTranslateX(node.value.getTranslateX());
//        beta.value.setTranslateY(node.value.getTranslateY());
//        beta.line.setEndX(beta.line.getStartX());
//        beta.line.setEndY(beta.line.getStartY());

        node.circle.centerXProperty().bind(beta.circle.centerXProperty().add(100 + node.height*10));
        node.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
        node.line.startXProperty().bind(beta.circle.centerXProperty());
        node.line.startYProperty().bind(beta.circle.centerYProperty());
        //        node.value.setTranslateY(node.circle.getCenterY());
//        node.value.setTranslateX(node.circle.getCenterX());
//        node.line.setStartX(beta.circle.getCenterX());
//        node.line.setStartY(beta.circle.getCenterY());
//        node.line.setEndX(node.circle.getCenterX());
//        node.line.setEndY(node.circle.getCenterY());

        if(t2 != null){
            t2.circle.centerXProperty().bind(node.circle.centerXProperty().subtract(100 - t2.height+10));
            t2.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
            t2.line.startXProperty().bind(node.circle.centerXProperty());
            t2.line.startYProperty().bind(node.circle.centerYProperty());
        }
        return beta;
    }


    AVLTreeNode rotateLeft(AVLTreeNode p_Node,AVLTreeNode node,Pane center) {
        if (node == null) return node;

        AVLTreeNode beta = node.right;
        AVLTreeNode t2 = beta.left;
        AVLTreeNode alpha = node.left;

        beta.left = node;
        node.right = t2;

        updateHeight(node);
        updateHeight(beta);

        //        if(beta.right != null){
//            beta.right.circle.setCenterX(beta.circle.getCenterX());
//            beta.right.circle.setCenterY(beta.circle.getCenterY());
//            //            beta.right.value.setTranslateX(beta.value.getTranslateX());
////            beta.right.value.setTranslateY(beta.value.getTranslateY());
////            beta.right.line.setStartX(node.circle.getCenterX());
////            beta.right.line.setStartY(node.circle.getCenterY());
////            beta.right.line.setEndX(beta.circle.getCenterX());
////            beta.right.line.setEndY(beta.circle.getCenterY());
//        }
//
//        if(alpha != null){
//            alpha.circle.setCenterX(alpha.circle.getCenterX() - 100 - alpha.height*10);
//            alpha.circle.setCenterY(alpha.circle.getCenterY() + 60f);
//            //            alpha.value.setTranslateX(alpha.circle.getCenterX());
////            alpha.value.setTranslateY(alpha.circle.getCenterY());
////            alpha.line.setStartX(node.circle.getCenterX());
////            alpha.line.setStartY(node.circle.getCenterY());
////            alpha.line.setEndX(alpha.circle.getCenterX());
////            alpha.line.setEndY(alpha.circle.getCenterY());
//        }

        if(p_Node == node){
            beta.circle.centerXProperty().unbind();
            beta.circle.centerYProperty().unbind();
            beta.circle.setCenterX(center.getWidth()/2);
            beta.circle.setCenterY(60);
            beta.line.startXProperty().bind(beta.circle.centerXProperty());
            beta.line.startYProperty().bind(beta.circle.centerYProperty());
        }else{
            beta.circle.centerXProperty().bind(p_Node.circle.centerXProperty().add(node.circle.getCenterX() - p_Node.circle.getCenterX()));
            beta.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
            beta.line.startXProperty().bind(p_Node.circle.centerXProperty());
            beta.line.startYProperty().bind(p_Node.circle.centerYProperty());
        }
        //beta.value.setTranslateX(node.value.getTranslateX());
//        beta.value.setTranslateY(node.value.getTranslateY());
//        beta.line.setEndX(beta.line.getStartX());
//        beta.line.setEndY(beta.line.getStartY());

        node.circle.centerXProperty().bind(beta.circle.centerXProperty().subtract(100 + node.height*10));
        node.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
        node.line.startXProperty().bind(beta.circle.centerXProperty());
        node.line.startYProperty().bind(beta.circle.centerYProperty());
        //        node.value.setTranslateY(node.circle.getCenterY());
//        node.value.setTranslateX(node.circle.getCenterX());
//        node.line.setStartX(beta.circle.getCenterX());
//        node.line.setStartY(beta.circle.getCenterY());
//        node.line.setEndX(node.circle.getCenterX());
//        node.line.setEndY(node.circle.getCenterY());

        if(t2 != null){
             t2.circle.centerXProperty().bind(node.circle.centerXProperty().add(100 + t2.height*10));
             t2.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
             t2.line.startXProperty().bind(node.circle.centerXProperty());
             t2.line.startYProperty().bind(node.circle.centerYProperty());
             //            t2.value.setTranslateX(t2.circle.getCenterX());
//            t2.value.setTranslateY(t2.circle.getCenterY());
//            t2.line.setStartX(node.circle.getCenterX());
//            t2.line.setStartY(node.circle.getCenterY());
//            t2.line.setEndX(t2.circle.getCenterX());
//            t2.line.setEndY(t2.circle.getCenterY());
        }

        return beta;
    }

    //returns the balance height
    int getBalance(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }
        int balance;
        balance = getHeight(node.left) - getHeight(node.right);
        return balance;
    }

    //returns the min value present in the tree
    int getMinValue(AVLTreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        if (node.left == null) return node.data;

        return getMinValue(node.left);
    }

    public AVLTreeNode search(AVLTreeNode node, int key){
        if (node==null) {
            System.out.println("Tree is empty!");
            return null;
        }
            if (key<node.data){                          //when the key is smaller than the present node value
                 search(node.left, key);
            }else if (key>node.data){                    //when the key value is bigger than the present node value
                search(node.right, key);
            }else if (key==node.data){                   //if the data is found so then return it
                node.circle.setFill(Color.RED);
                node.value.setTextFill(Color.WHITE);
                System.out.println("\nFound " +key+ " at tree-height= "+ node.height);
                return node;
            }
        return null;
    }

    public AVLTreeNode delete(AVLTreeNode p_Node,AVLTreeNode node, int key,Pane center) {

        if (node == null) return null;

        if (key < node.data) {
            node.left = delete(node,node.left, key,center);
        } else if (key > node.data) {
            node.right = delete(node,node.right, key,center);
        } else {
            if (node.left == null) {
                node.deleteNodeFromTree(center);
                node = node.right;
            } else if (node.right == null) {
                node.deleteNodeFromTree(center);
                node = node.left;
            } else {
                int inorderSuccessorValue = getMinValue(node.right);
                node.data = inorderSuccessorValue;
                node.value.setText(Integer.toString(inorderSuccessorValue));
                node.right = delete(node,node.right, inorderSuccessorValue,center);
            }
        }
        if (node == null) {
            return null;
        }

        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                node = rotateRight(p_Node,node,center);
            } else {
                node.left = rotateLeft(p_Node,node.left,center);
                node = rotateRight(p_Node,node,center);
            }
        } else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                node = rotateLeft(p_Node,node,center);
            } else {
                node.right = rotateRight(p_Node,node.right,center);
                node = rotateLeft(p_Node,node,center);
            }
        }
        return node;
    }

    public AVLTreeNode insert(AVLTreeNode p_Node, AVLTreeNode node, int key, Pane center,float xCord,float yCord,float p_xCord,float p_yCord) {

        if (node == null) {
            AVLTreeNode newNode = new AVLTreeNode(key);
            System.out.println("\n"+newNode.data+" coords, x: "+xCord+ " y: "+yCord+" node's height: "+ newNode.height);
            newNode.addNodeToTree(p_Node,center,xCord,yCord,p_xCord,p_yCord,key);
            return newNode;
        }

        if(node.data < 0){
            node.data = key;
            node.addNodeToTree(node,center,xCord,yCord,p_xCord,p_yCord,key);
        }
        else if (key < node.data) {
            p_xCord = xCord;
            p_yCord = yCord;
            yCord += 60f;
            xCord -= (100f + updateHeight(node)*10);
            System.out.println("Height of "+ node.data+ " is: "+ updateHeight(node));
            node.left = insert(node,node.left, key,center,xCord,yCord,p_xCord,p_yCord);
        }
        else if (key > node.data) {
            p_xCord = xCord;
            p_yCord = yCord;
            yCord += 60f;
            xCord += (100f + updateHeight(node)*10);
            System.out.println("Height of "+ node.data+ " is: "+ updateHeight(node));
            node.right = insert(node,node.right, key,center,xCord,yCord,p_xCord,p_yCord);
        }
        else {
            return node;
        }

        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (key < node.left.data) {
                node = rotateRight(p_Node,node,center);
            } else {
                node.left = rotateLeft(p_Node,node.left,center);
                node = rotateRight(p_Node,node,center);
            }
        }
        else if (balance < -1) {
            if (key > node.right.data) {
                node = rotateLeft(p_Node,node,center);
            } else {
                node.right = rotateRight(p_Node,node.right,center);
                node = rotateLeft(p_Node,node,center);
            }
        }
        return node;
    }

    public void insert(int key,Pane center) {
        AVLTreeNode p_Node = this.root;
        root = insert(p_Node ,this.root, key,center,(float) center.getWidth()/2,this.y,(float) center.getWidth()/2,60);
        this.x= (float)center.getWidth();
        this.y = 60f;
        return;
    }

    public void delete(int key,Pane center) {
        AVLTreeNode p_Node = this.root;
        root = delete(p_Node,this.root, key,center);
        return;
    }

    public void printTreeLevelOrder() {
        if (root == null) return;

        LinkedList queue = new LinkedList();
        queue.add(new QueueNode(root, 0));

        int maxLevelVisited = -1;

        while (!queue.isEmpty()) {
            QueueNode currentNode = (QueueNode) queue.remove();

            if (currentNode.level > maxLevelVisited) {
                maxLevelVisited = currentNode.level;
                System.out.print("\nlevel-" + currentNode.level + " nodes: ");
            }
            System.out.print(" " + currentNode.treeNode.data);

            if (currentNode.treeNode.left != null) {
                queue.add(new QueueNode(currentNode.treeNode.left, currentNode.level + 1));
            }

            if (currentNode.treeNode.right != null) {
                queue.add(new QueueNode(currentNode.treeNode.right, currentNode.level + 1));
            }
        }
    }

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    void printPostorder(AVLTreeNode node, Pane pane)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left, pane);

        // then recur on right subtree
        printPostorder(node.right, pane);

        // now deal with the node
        System.out.print(node.data + " ");
        postOrder.add(node.data);
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(AVLTreeNode node, Pane pane)
    {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left, pane);

        /* then print the data of node */
        System.out.print(node.data + " ");
        inOrder.add(node.data);

        /* now recur on right child */
        printInorder(node.right, pane);
    }

    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(AVLTreeNode node, Pane pane)
    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.data + " ");
        preOrder.add(node.data);

        /* then recur on left sutree */
        printPreorder(node.left, pane);

        /* now recur on right subtree */
        printPreorder(node.right, pane);
    }


   public void addOrderToPane(Pane pane){
       VBox orderVBox= new VBox();
       printPostorder(root,pane);
       printInorder(root,pane);
       printPreorder(root, pane);

       inOrderLabel.setText("\n\n\nInorder:"+inOrder.toString()+" \n");
       preOrderLabel.setText("Preorder:"+preOrder.toString()+ "\n");
       postOrderLabel.setText("Postorder:"+postOrder.toString()+ "\n");

       orderVBox.getChildren().addAll(inOrderLabel, preOrderLabel,postOrderLabel);
       pane.getChildren().addAll(orderVBox);
       preOrder.clear();
       inOrder.clear();
       postOrder.clear();

   }


    public class QueueNode {
        AVLTreeNode treeNode;
        int level;

        QueueNode(AVLTreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }


    public class AVLTreeNode {
        int data;
        AVLTreeNode left;
        AVLTreeNode right;
        Circle circle = new Circle(20,Color.WHITE);
        Line line = new Line();
        Label value = new Label();
        int height;


        AVLTreeNode(){
            this.data = -1;
        }


        AVLTreeNode(int data) {
            this.data = data;
            this.height = 1;
        }


        public void addNodeToTree(AVLTreeNode p_node,Pane center,float xCord, float yCord,float p_xCord, float p_yCord, int key){
            String num = Integer.toString(key);
            this.circle.setStroke(Color.BLACK);

            if(this == p_node){
                this.circle.setCenterX(xCord);
                this.circle.setCenterY(yCord);
            }else{
                this.circle.centerXProperty().bind(p_node.circle.centerXProperty().add(xCord - p_xCord));
                this.circle.centerYProperty().bind(p_node.circle.centerYProperty().add(60));
            }

            this.value.setText(num);
            this.value.setFont(Font.font(null, FontWeight.BOLD,10));

            this.value.translateXProperty().bind(this.circle.centerXProperty());
            this.value.translateYProperty().bind(this.circle.centerYProperty());System.out.println(p_xCord);

            if(this == p_node){
                this.line.startXProperty().bind(this.circle.centerXProperty());
                this.line.startYProperty().bind(this.circle.centerYProperty());
            }else{
                this.line.startXProperty().bind(p_node.circle.centerXProperty());
                this.line.startYProperty().bind(p_node.circle.centerYProperty());
            }
            this.line.endXProperty().bind(this.circle.centerXProperty());
            this.line.endYProperty().bind(this.circle.centerYProperty());

//            value.setTranslateX(xCord - 5.5);
//            value.setTranslateY(yCord - 7);
//            this.line = new Line(p_xCord,p_yCord,this.circle.getCenterX(),this.circle.getCenterY());
            this.line.setViewOrder(10);
//            System.out.print("\n" + this.data + " X-endPoints: " + this.line.endXProperty() + );
            center.getChildren().addAll(this.line,this.circle,this.value);
        }


        public void deleteNodeFromTree(Pane center){
            center.getChildren().removeAll(this.circle,this.value);
        }

    }
}
