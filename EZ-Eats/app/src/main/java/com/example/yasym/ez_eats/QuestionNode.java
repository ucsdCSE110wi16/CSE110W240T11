package com.example.yasym.ez_eats;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ymno1 on 2/24/2016.
 * The class representing a single question.
 * Contains two children and a parent, a string question
 * a boolean value indicating if this is a result node
 * and a arraylist of results if a result node.
 */
public class QuestionNode {

    private final int RIGHT = 1;
    private final int LEFT = 0;

    private QuestionNode left;
    private QuestionNode right;
    private QuestionNode parent;

    private String question;
    private boolean isResultNode;//Indicates if there is still questions to be asked.
    private ArrayList<String> results;

    QuestionNode(String question, boolean isResultNode){
        this.question = question;
        this.isResultNode = isResultNode;
    }

    void setRight(QuestionNode right){
        this.right = right;
    }

    void setLeft(QuestionNode left){
        this.left = left;
    }

    void setParent(QuestionNode parent){
        this.parent = parent;
    }

    void setResults(ArrayList<String> results){
        this.results = results;
    }

    QuestionNode getNextQuestion(int direction){
        if (direction == LEFT){
            return this.left;
        }else if (direction == RIGHT){
            return this.right;
        }else {
            return null;
        }
    }

    String getQuestion(){
        return question;
    }

    ArrayList<String> getResults() {return results;}

    boolean isResult(){
        return isResultNode;
    }
}
