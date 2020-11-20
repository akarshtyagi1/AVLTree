package sample;

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
    void updateHeight(AVLTreeNode node) {
        if (node == null) return;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    //rotation of certain branches to balance the tree
    AVLTreeNode rotateRight(AVLTreeNode node) {
        if (node == null) return node;

        AVLTreeNode beta = node.left;
        AVLTreeNode t2 = beta.right;

        beta.right = node;
        node.left = t2;

        updateHeight(node);
        updateHeight(beta);

        return beta;
    }

    AVLTreeNode rotateLeft(AVLTreeNode node) {
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
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        } else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }

    public AVLTreeNode insert(AVLTreeNode node, int key, Pane center,float xCord,float yCord) {

        if (node == null) {
            AVLTreeNode newNode = new AVLTreeNode(key);
            newNode.addNodeToTree(center,xCord,yCord,key);
//            Label value = new Label(Integer.toString(key));
//            value.setTranslateX(xCord); value.setTranslateY(yCord);
//            Circle circle = new Circle(xCord,yCord,20,Color.WHITE);
//            center.getChildren().addAll(circle,value);
            return newNode;
        }
        if(node.data < 0){
            node.data = key;
            node.addNodeToTree(center,xCord,yCord,key);
//            Circle circle = new Circle(xCord,yCord,20,Color.WHITE);
//            Label value = new Label(Integer.toString(key));
//            value.setTranslateX(xCord); value.setTranslateY(yCord);
//            center.getChildren().addAll(circle,value);
        }
        else if (key < node.data) {
            yCord += 60f;
            xCord -= 100f + (node.height*10);
            node.left = insert(node.left, key,center,xCord,yCord);
        }
        else if (key > node.data) {
            yCord += 60f;
            xCord += 100f - (node.height*10);
            node.right = insert(node.right, key,center,xCord,yCord);
        }
        else {
            return node;
        }

        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (key < node.left.data) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        else if (balance < -1) {
            if (key > node.right.data) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }

    public void insert(int key,Pane center) {
        root = insert(this.root, key,center,this.x,this.y);
        this.x= (float) center.getWidth()/2;
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

        AVLTreeNode(int data) {
            this.data = data;
            this.height = 1;
        }

        public void addNodeToTree(Pane center,float xCord, float yCord, int key){
            String num = Integer.toString(key);
            this.value.setText(num);
            value.setFont(Font.font(null, FontWeight.BOLD,10));
            value.setTranslateX(xCord - 5.5);
            value.setTranslateY(yCord - 7);
            this.circle.setStroke(Color.BLACK);
            this.circle.setCenterX(xCord);
            this.circle.setCenterY(yCord);
            center.getChildren().addAll(this.circle,this.value);
        }

        public void deleteNodeFromTree(Pane center){
            center.getChildren().removeAll(this.circle,this.value);
        }
    }
}
