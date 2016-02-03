package edu.ucsd.cse110_team11.ezeats;

/**
 * Created by JAAASON on 2016/2/2.
 */
public class Node {

    private int value;
    private Node parent;
    private Node left;
    private Node right;

    public Node(int value){
        this.value = value;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }
    public Node getParent(){
        return this.parent;
    }

    public void setLeftChild(Node left){
        this.left = left;
    }
    public Node getLeftChild(){
        return this.left;
    }

    public void setRightChild(Node right){
        this.right = right;
    }
    public Node getRightChild(){
        return this.right;
    }

    public boolean isLeaf(){
        if (this.left == null && this.right == null) return true;
        return false;
    }

    public int getValue(){
        return this.value;
    }

    public boolean lessThan(Node other){
        return (this.value < other.getValue())? true : false;
    }
}
