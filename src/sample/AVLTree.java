package sample;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public class AVLTree {
    AVLTreeNode root;
    float x=600;
    float y=60;
    ArrayList<Integer> inOrder= new ArrayList<>();
    ArrayList<Integer> preOrder= new ArrayList<>();
    ArrayList<Integer> postOrder= new ArrayList<>();
    Text inOrderLabel= new Text();
    Text preOrderLabel= new Text();
    Text postOrderLabel= new Text();
    Label heightLabel= new Label();
    int flag = 0;

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
        if (node == null || node.data==-1) return 0;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node.height;
    }

    //    counting the total nodes in the tree
    int count(AVLTreeNode tree)
    {
        int c =  1;             //Node itself should be counted
        if (tree ==null)
            return 0;
        else
        {
            c += count(tree.left);
            c += count(tree.right);
            return c;
        }
    }


    //adding height to pane
    void addHeightToDetailPane(AVLTreeNode node,Pane detailsPane, Text prompt){
        if(flag == 1){
            detailsPane.getChildren().remove(heightLabel);
        }

        heightLabel.setText("Height of the Tree: "+ updateHeight(node)+ "\t\t\tTotal nodes in the Tree:"+ count(node));
        heightLabel.setStyle("-fx-font: 25px Tahoma; "
                + "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);"
                + "-fx-stroke: black; -fx-stroke-width: 1;");
        detailsPane.getChildren().addAll(heightLabel);
        flag = 1;
    }


    //rotation of certain branches to balance the tree
    AVLTreeNode rotateRight(AVLTreeNode p_Node,AVLTreeNode node,Pane center) {
        if (node == null) return null;

        AVLTreeNode beta = node.left;
        AVLTreeNode t2 = beta.right;
        AVLTreeNode alpha = node.right;
        AVLTreeNode key = beta.left;

        beta.right = node;
        node.left = t2;

        updateHeight(node);
        updateHeight(beta);

        if(p_Node == node){
            beta.circle.centerXProperty().unbind();
            beta.circle.centerYProperty().unbind();
            beta.circle.setCenterX(center.getWidth()/2);
            beta.circle.setCenterY(60);
            beta.line.startXProperty().bind(beta.circle.centerXProperty());
            beta.line.startYProperty().bind(beta.circle.centerYProperty());
        }else{
            beta.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
            float sep = (float) (p_Node.circle.getCenterY()+60)/60;
            float diff = (float) (node.circle.getCenterX() - p_Node.circle.getCenterX());
            float m = diff/(Math.abs(diff));
            System.out.println("right " + m);
            beta.circle.centerXProperty().bind(p_Node.circle.centerXProperty().add(m*(300f/sep - (sep-1)*15)));
            beta.line.startXProperty().bind(p_Node.circle.centerXProperty());
            beta.line.startYProperty().bind(p_Node.circle.centerYProperty());
        }

        node.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
        float sep = (float) (beta.circle.getCenterY()+60)/60;
        node.circle.centerXProperty().bind(beta.circle.centerXProperty().add(300f/sep - (sep-1)*15));
        node.line.startXProperty().bind(beta.circle.centerXProperty());
        node.line.startYProperty().bind(beta.circle.centerYProperty());

        if(t2 != null){
            t2.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
            sep = (float) (node.circle.getCenterY()+60)/60;
            t2.circle.centerXProperty().bind(node.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));
            t2.line.startXProperty().bind(node.circle.centerXProperty());
            t2.line.startYProperty().bind(node.circle.centerYProperty());
        }

        if( key != null){
            sep = (float) (beta.circle.getCenterY() + 60)/60;
            key.circle.centerXProperty().bind(beta.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));
            key.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
        }

        if(alpha != null){
            sep = (float) (node.circle.getCenterY() + 60)/60;
            alpha.circle.centerXProperty().bind(node.circle.centerXProperty().add(300f/sep - (sep-1)*15));
            alpha.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
        }
        return beta;
    }


    AVLTreeNode rotateLeft(AVLTreeNode p_Node,AVLTreeNode node,Pane center) {
        if (node == null) return node;

        AVLTreeNode beta = node.right;
        AVLTreeNode t2 = beta.left;
        AVLTreeNode alpha = node.left;
        AVLTreeNode key = beta.right;
        beta.left = node;
        node.right = t2;

        updateHeight(node);
        updateHeight(beta);

        if(p_Node == node){
            beta.circle.centerXProperty().unbind();
            beta.circle.centerYProperty().unbind();
            beta.circle.setCenterX(center.getWidth()/2);
            beta.circle.setCenterY(60);
            beta.line.startXProperty().bind(beta.circle.centerXProperty());
            beta.line.startYProperty().bind(beta.circle.centerYProperty());
        }else{
            beta.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
            float sep = (float) (p_Node.circle.getCenterY()+60)/60;
            float diff = (float) (node.circle.getCenterX() - p_Node.circle.getCenterX());
            float m = diff/(Math.abs(diff));
            System.out.println("left  " + m);
            beta.circle.centerXProperty().bind(p_Node.circle.centerXProperty().add(m*(300f/sep - (sep-1)*15)));
            beta.line.startXProperty().bind(p_Node.circle.centerXProperty());
            beta.line.startYProperty().bind(p_Node.circle.centerYProperty());
        }

        node.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
        float sep = (float)(beta.circle.getCenterY()+60)/60;
        node.circle.centerXProperty().bind(beta.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));
        node.line.startXProperty().bind(beta.circle.centerXProperty());
        node.line.startYProperty().bind(beta.circle.centerYProperty());

        if(alpha != null){
            sep = (float) (node.circle.getCenterY() + 60)/60;
            alpha.circle.centerXProperty().bind(node.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));
            alpha.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
        }

        if(t2 != null){
             t2.circle.centerYProperty().bind(node.circle.centerYProperty().add(60));
             sep = (float) (node.circle.getCenterY()+60)/60;
             t2.circle.centerXProperty().bind(node.circle.centerXProperty().add(300f/sep - (sep-1)*15));
             t2.line.startXProperty().bind(node.circle.centerXProperty());
             t2.line.startYProperty().bind(node.circle.centerYProperty());
        }

        if(key != null){
            sep = (float) (beta.circle.getCenterY() + 60)/60;
            key.circle.centerXProperty().bind(beta.circle.centerXProperty().add(300f/sep - (sep-1)*15));
            key.circle.centerYProperty().bind(beta.circle.centerYProperty().add(60));
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
            if (key<node.data){
                //when the key is smaller than the present node value
                 search(node.left, key);
            }else if (key>node.data){
                //when the key value is bigger than the present node value
                search(node.right, key);
            }else if (key==node.data){
                //if the data is found so then return it
                FillTransition ft = new FillTransition(Duration.seconds(2),node.circle);

                ScaleTransition st = new ScaleTransition(Duration.seconds(0.5), node.circle);
                st.setByY(0.5);
                st.setByX(0.5);
                st.setCycleCount(2);
                st.setAutoReverse(true);
                st.play();
                ft.setFromValue(Color.GREENYELLOW);
                ft.setToValue(Color.WHITE);
                ft.play();
                return node;
            }
        return null;
    }


    public AVLTreeNode delete(AVLTreeNode p_Node,AVLTreeNode node, int key,Pane center, Pane detailsPane) {
        Text emptyPrompt=new Text("");
        if (node == null)
        {
         emptyPrompt.setText("Tree is Empty!");
         addHeightToDetailPane(node, detailsPane, emptyPrompt);
            return null;
        }
        if (key < node.data) {
            node.left = delete(node, node.left, key,center, detailsPane);
        } else if (key > node.data) {
            node.right = delete(node, node.right, key,center, detailsPane);
        } else {
            if (node.left == null) {
                node.deleteNodeFromTree(center);
                if(p_Node != node && node.right != null){
                    node.right.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
                    float sep = (float) (p_Node.circle.getCenterY()+60)/60;

                    if(p_Node.data < node.right.data)
                        node.right.circle.centerXProperty().bind(p_Node.circle.centerXProperty().add(300f/sep - (sep-1)*15));
                    else
                        node.right.circle.centerXProperty().bind(p_Node.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));

                    node.right.line.startXProperty().bind(p_Node.circle.centerXProperty());
                    node.right.line.startYProperty().bind(p_Node.circle.centerYProperty());
                }else if(node.right != null){
                    node.right.circle.centerXProperty().bind(center.widthProperty().divide(2));
                    node.right.circle.centerYProperty().unbind();
                    node.right.circle.setCenterY(60);
                    node.right.line.startXProperty().bind(node.right.circle.centerXProperty());
                    node.right.line.startYProperty().bind(node.right.circle.centerYProperty());
                }
                node = node.right;
            } else if (node.right == null) {
                node.deleteNodeFromTree(center);
                if(p_Node != node && node.left != null){
                    node.left.circle.centerYProperty().bind(p_Node.circle.centerYProperty().add(60));
                    float sep = (float) (p_Node.circle.getCenterY()+60)/60;

                    if(p_Node.data > node.left.data)
                        node.left.circle.centerXProperty().bind(p_Node.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));
                    else
                        node.left.circle.centerXProperty().bind(p_Node.circle.centerXProperty().subtract(300f/sep - (sep-1)*15));

                    node.left.line.startXProperty().bind(p_Node.circle.centerXProperty());
                    node.left.line.startYProperty().bind(p_Node.circle.centerYProperty());
                }else if(node.left != null){
                    node.left.circle.centerXProperty().bind(center.widthProperty().divide(2));
                    node.left.circle.centerYProperty().unbind();
                    node.left.circle.setCenterY(60);
                    node.left.line.startXProperty().bind(node.left.circle.centerXProperty());
                    node.left.line.startYProperty().bind(node.left.circle.centerYProperty());
                }
                node = node.left;
            } else {
                int inorderSuccessorValue = getMinValue(node.right);
                node.data = inorderSuccessorValue;
                node.value.setText(Integer.toString(inorderSuccessorValue));
                node.right = delete(node,node.right, inorderSuccessorValue,center, detailsPane);
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
            float sep = yCord/60;
            xCord -= (300f/sep - (sep-1)*15);
            node.left = insert( node, node.left, key, center, xCord, yCord, p_xCord, p_yCord);
        }
        else if (key > node.data) {
            p_xCord = xCord;
            p_yCord = yCord;
            yCord += 60f;
            float sep = yCord/60;
            xCord += (300f/sep - (sep-1)*15);
            node.right = insert( node, node.right, key, center, xCord, yCord, p_xCord, p_yCord);
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
                node.left = rotateLeft( p_Node, node.left, center);
                node = rotateRight( p_Node, node,center);
            }
        }
        else if (balance < -1) {
            if (key > node.right.data) {
                node = rotateLeft( p_Node, node, center);
            } else {
                node.right = rotateRight( p_Node, node.right, center);
                node = rotateLeft( p_Node, node, center);
            }
        }
        return node;
    }


    public void insert(int key,Pane center) {
        AVLTreeNode p_Node = this.root;
        root = insert(p_Node ,this.root, key,center,(float) center.getWidth()/2, this.y,(float) center.getWidth()/2,60);
        this.x= (float)center.getWidth();
        this.y = 60f;
        return;
    }


    public void delete(int key,Pane center, Pane detailsPane) {
        AVLTreeNode p_Node = this.root;
        root = delete(p_Node,this.root, key,center,detailsPane);
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
        if (node == null || node.data==-1)
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
        if (node == null || node.data==-1)
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
        if (node == null || node.data==-1)
            return;

        /* first print data of node */
        System.out.print(node.data + " ");
        preOrder.add(node.data);

        /* then recur on left subtree */
        printPreorder(node.left, pane);

        /* now recur on right subtree */
        printPreorder(node.right, pane);
    }


    public void addOrderToPane(Pane pane){
       VBox orderVBox= new VBox();
       printPostorder(root,pane);
       printInorder(root,pane);
       printPreorder(root, pane);

       inOrderLabel.setText("\n\n\n\nInorder:"+inOrder.toString()+" \n");
       preOrderLabel.setText("\n\n\n\nPreorder:"+preOrder.toString()+ "\n");
       postOrderLabel.setText("\n\n\n\nPostorder:"+postOrder.toString()+ "\n");
       inOrderLabel.setFont(Font.font(("Times New Roman"), FontWeight.BOLD, FontPosture.ITALIC, 14));
       preOrderLabel.setFont(Font.font(("Times New Roman"), FontWeight.BOLD, FontPosture.ITALIC, 14));
       postOrderLabel.setFont(Font.font(("Times New Roman"), FontWeight.BOLD, FontPosture.ITALIC, 14));

       inOrderLabel.setWrappingWidth(140);
       preOrderLabel.setWrappingWidth(140);
       postOrderLabel.setWrappingWidth(140);

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
            }else if(p_node != null){
                this.circle.centerXProperty().bind(p_node.circle.centerXProperty().add(xCord - p_xCord));
                this.circle.centerYProperty().bind(p_node.circle.centerYProperty().add(60));
            }else{
                this.circle.centerXProperty().bind(center.widthProperty().divide(2));
                this.circle.centerYProperty().unbind();
                this.circle.setCenterY(60);
            }

            this.value.setText(num);
            this.value.setFont(Font.font(null, FontWeight.BOLD,12));
            this.value.translateXProperty().bind(this.circle.centerXProperty().subtract(5.5));
            this.value.translateYProperty().bind(this.circle.centerYProperty().subtract(7.5));System.out.println(p_xCord);

            if(this == p_node){
                this.line.startXProperty().bind(this.circle.centerXProperty());
                this.line.startYProperty().bind(this.circle.centerYProperty());
            }else if(p_node != null){
                this.line.startXProperty().bind(p_node.circle.centerXProperty());
                this.line.startYProperty().bind(p_node.circle.centerYProperty());
            }else{
                this.line.startXProperty().bind(this.circle.centerXProperty());
                this.line.startYProperty().bind(this.circle.centerYProperty());
            }

            this.line.endXProperty().bind(this.circle.centerXProperty());
            this.line.endYProperty().bind(this.circle.centerYProperty());
            this.line.setViewOrder(10);
            center.getChildren().addAll(this.line,this.circle,this.value);
        }

        public void deleteNodeFromTree(Pane center){
            center.getChildren().removeAll(this.line,this.circle,this.value);
        }
    }
}
