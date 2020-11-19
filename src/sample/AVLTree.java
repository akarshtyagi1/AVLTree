package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.LinkedList;

public class AVLTree {

    AVLTreeNode root;

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
    private AVLTreeNode delete(AVLTreeNode node, int key) {

        if (node == null) return null;

        if (key < node.data) {
            node.left = delete(node.left, key);
        } else if (key > node.data) {
            node.right = delete(node.right, key);
        } else {

            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                int inorderSuccessorValue = getMinValue(node.right);
                node.data = inorderSuccessorValue;
                node.right = delete(node.right, inorderSuccessorValue);
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

    public AVLTreeNode insert(AVLTreeNode node, int key, Pane center) {

        if (node == null) {
            return new AVLTreeNode(key);
        }
        if(node.data < 0){
            node.data = key;

        } else if (key < node.data) {
            node.left = insert(node.left, key,center);
        } else if (key > node.data) {
            node.right = insert(node.right, key,center);
        } else {
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
        } else if (balance < -1) {
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
        root = insert(this.root, key,center);
        this.root.line.setStartX(600);
        this.root.line.setStartY(60);
        this.root.line.setEndX(560);
        this.root.line.setEndY(80);
        center.getChildren().addAll(this.root.circle,this.root.line);
        return;
    }

    public void delete(int key) {
        root = delete(this.root, key);
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

    private class QueueNode {
        AVLTreeNode treeNode;
        int level;

        QueueNode(AVLTreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    private class AVLTreeNode {
        int data;
        AVLTreeNode left;
        AVLTreeNode right;
        float x = 600;
        float y = 60;
        Circle circle = new Circle(x,y,20,Color.WHITE);
        Line line = new Line();
        Label value = new Label();
        int height;

        AVLTreeNode(int data) {
            this.data = data;
            this.height = 1;
        }
    }
}
