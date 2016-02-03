package edu.ucsd.cse110_team11.ezeats;

/**
 * Created by JAAASON on 2016/2/2.
 */
public class Tree {

    private Node root;
    private int size;

    public Tree() {

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
}
