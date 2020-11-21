package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;

public class AVLTree {

    AVLTreeNode root;
    float x=600;
    float y=60;

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
    AVLTreeNode rotateRight(AVLTreeNode node,Pane center) {
        if (node == null) return node;

        AVLTreeNode beta = node.left;
        AVLTreeNode t2 = beta.right;

        beta.right = node;
        node.left = t2;

        updateHeight(node);
        updateHeight(beta);


        beta.left.circle.setCenterX(beta.circle.getCenterX());
        beta.left.circle.setCenterY(beta.circle.getCenterY());
        beta.left.value.setTranslateX(beta.value.getTranslateX());
        beta.left.value.setTranslateY(beta.value.getTranslateY());
        beta.left.line.setStartX(node.circle.getCenterX());
        beta.left.line.setStartY(node.circle.getCenterY());
        beta.left.line.setEndX(beta.circle.getCenterX());
        beta.left.line.setEndY(beta.circle.getCenterY());

        beta.circle.setCenterX(node.circle.getCenterX());
        beta.circle.setCenterY(node.circle.getCenterY());
        beta.value.setTranslateX(node.value.getTranslateX());
        beta.value.setTranslateY(node.value.getTranslateY());

        node.circle.setCenterY(60*(node.height+1));
        node.circle.setCenterX(node.circle.getCenterX() + 100 + node.height*10);
        node.value.setTranslateY(60*(node.height+1));
        node.value.setTranslateX(node.circle.getCenterX());
        node.line.setStartX(beta.circle.getCenterX());
        node.line.setStartY(beta.circle.getCenterY());
        node.line.setEndX(node.circle.getCenterX());
        node.line.setEndY(node.circle.getCenterY());

        return beta;
    }


    AVLTreeNode rotateLeft(AVLTreeNode node,Pane center) {
        if (node == null) return node;

        AVLTreeNode beta = node.right;
        AVLTreeNode t2 = beta.left;

        beta.left = node;
        node.right = t2;

        updateHeight(node);
        updateHeight(beta);





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

    public void search(AVLTreeNode node, int key){
        if (node==null) System.out.println("Tree is empty!");  ;
            if (key<node.data){
                //when the key is smaller than the present node value
                 node=node.left;
                 search(node, key);
            }else if (key>node.data){
                //when the key value is bigger than the present node value
                node=node.right;
                search(node, key);
            }else if (key==node.data){
                //if the data is found so then return it
                System.out.println("\nFound " +key+ " at tree-height= "+ node.height);
            }
    }
    public AVLTreeNode delete(AVLTreeNode node, int key,Pane center) {

        if (node == null) return null;

        if (key < node.data) {
            node.left = delete(node.left, key,center);
        } else if (key > node.data) {
            node.right = delete(node.right, key,center);
        } else {

            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                int inorderSuccessorValue = getMinValue(node.right);
                node.data = inorderSuccessorValue;
                node.right = delete(node.right, inorderSuccessorValue,center);
            }
        }

        if (node == null) {
            return null;
        }


        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                node = rotateRight(node,center);
            } else {
                node.left = rotateLeft(node.left,center);
                node = rotateRight(node,center);
            }
        } else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                node = rotateLeft(node,center);
            } else {
                node.right = rotateRight(node.right,center);
                node = rotateLeft(node,center);
            }
        }
        return node;
    }

    public AVLTreeNode insert(AVLTreeNode node, int key, Pane center,float xCord,float yCord,float p_xCord,float p_yCord) {

        if (node == null) {
            AVLTreeNode newNode = new AVLTreeNode(key);
            System.out.println("\n"+newNode.data+" coords, x: "+xCord+ " y: "+yCord+" node's height: "+ newNode.height);
            newNode.addNodeToTree(center,xCord,yCord,p_xCord,p_yCord,key);
            return newNode;
        }
        if(node.data < 0){
            node.data = key;
            node.addNodeToTree(center,xCord,yCord,p_xCord,p_yCord,key);

        }
        else if (key < node.data) {
            p_xCord = xCord;
            p_yCord = yCord;
            yCord += 60f;
            xCord -= (100f + updateHeight(node)*10);
            System.out.println("Height of "+ node.data+ " is: "+ updateHeight(node));
            node.left = insert(node.left, key,center,xCord,yCord,p_xCord,p_yCord);
        }
        else if (key > node.data) {
            p_xCord = xCord;
            p_yCord = yCord;
            yCord += 60f;
            xCord += (100f + updateHeight(node)*10);
            System.out.println("Height of "+ node.data+ " is: "+ updateHeight(node));
            node.right = insert(node.right, key,center,xCord,yCord,p_xCord,p_yCord);
        }
        else {
            return node;
        }

        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (key < node.left.data) {
                node = rotateRight(node,center);
            } else {
                node.left = rotateLeft(node.left,center);
                node = rotateRight(node,center);
            }
        }
        else if (balance < -1) {
            if (key > node.right.data) {
                node = rotateLeft(node,center);
            } else {
                node.right = rotateRight(node.right,center);
                node = rotateLeft(node,center);
            }
        }
        return node;
    }

    public void insert(int key,Pane center) {
        root = insert(this.root, key,center,(float) center.getWidth()/2,this.y,(float) center.getWidth()/2,60);
        this.x= (float)center.getWidth();
        this.y = 60f;
        return;
    }

    public void delete(int key,Pane center) {
        root = delete(this.root, key,center);
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
    void printPostorder(AVLTreeNode node)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.data + " ");
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(AVLTreeNode node)
    {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(AVLTreeNode node)
    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.data + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
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
        Line line ;
        Label value = new Label();
        int height;

        AVLTreeNode(int data) {
            this.data = data;
            this.height = 1;
        }

        public void addNodeToTree(Pane center,float xCord, float yCord,float p_xCord, float p_yCord, int key){
            String num = Integer.toString(key);
            this.value.setText(num);
            value.setFont(Font.font(null, FontWeight.BOLD,10));
            value.setTranslateX(xCord - 5.5);
            value.setTranslateY(yCord - 7);
            this.line = new Line(xCord,yCord,p_xCord,p_yCord);
            this.circle.setStroke(Color.BLACK);
            this.circle.setCenterX(xCord);
            this.circle.setCenterY(yCord);
            center.getChildren().addAll(this.line,this.circle,this.value);
        }

        public void deleteNodeFromTree(Pane center){
            center.getChildren().removeAll(this.circle,this.value);
        }
    }
}
