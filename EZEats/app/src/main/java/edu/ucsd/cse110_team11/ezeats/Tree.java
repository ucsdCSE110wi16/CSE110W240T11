package edu.ucsd.cse110_team11.ezeats;

/**
 * Created by JAAASON on 2016/2/2.
 */
public class Tree {

    private Node root;
    private int size = 0;
    private String traversal = "";

    public Tree() {
    }

    public int getSize(){
        return this.size;
    }

    public Node getRoot(){
        return this.root;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void insert(int value){
        Node node = new Node(value);
        if (this.isEmpty()){
            this.root = node;
            this.size ++;
            return;
        }
        Node parent;
        Node curr;
        curr = root;
        parent = root;
        curr = (value < curr.getValue())? curr.getLeftChild() : curr.getRightChild();

        while(curr != null){
            parent = curr;
            curr = (value < curr.getValue())? curr.getLeftChild() : curr.getRightChild();
        }

        if (value < parent.getValue()){
            parent.setLeftChild(node);
            node.setParent(parent);
            this.size ++;
        }else{
            parent.setRightChild(node);
            node.setParent(parent);
            this.size ++;
        }
    }

    public void traverse(Node n){
        if (n == null) return;
        if (n.getLeftChild() != null) traverse(n.getLeftChild());
        this.traversal += (n.getValue() + ",");
        if (n.getRightChild() != null) traverse(n.getRightChild());
    }

    public String getTraversal(){
        return this.traversal;
    }
}
