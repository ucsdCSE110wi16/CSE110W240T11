package com.example.yasym.ez_eats;

/**
 * Created by ymno1 on 2/24/2016.
 * Class representing the question tree.
 * Use hardcoding so far, to be refined later.
 */
public class QuestionTree {

    private QuestionNode root;

    QuestionTree(){
        ConstructTree();
    }

    void ConstructTree(){
        root = new QuestionNode("Do you want a full meal?", false);
        QuestionNode DorE = new QuestionNode("Do you want solid foods?", false);
        QuestionNode Ame_N = new QuestionNode("Do you want a hearty, protein packed meal?", false); //do you want American food
        QuestionNode Asi_N = new QuestionNode("Do you want food with lots of different spices? ", false); //do you want Asian food
        QuestionNode Eur_N = new QuestionNode("Do you want something new and exciting?", false); //do you want European food
        QuestionNode Ame = new QuestionNode("You might like American food!",true);
        QuestionNode Asi = new QuestionNode("You might like Asian food!!",true);
        QuestionNode Eur = new QuestionNode("You might like European food!!", true);
        QuestionNode drink = new QuestionNode("You might be thirsty!", true);
        QuestionNode eat = new QuestionNode("You're hungry!", true);
        QuestionNode end = new QuestionNode("I don't think you're hungry!", true);
        root.setLeft(DorE);
        DorE.setLeft(drink);
        DorE.setRight(eat);
        root.setRight(Ame_N);
        Ame_N.setLeft(Ame);
        Ame_N.setRight(Asi_N);
        Asi_N.setLeft(Asi);
        Asi_N.setRight(Eur_N);
        Eur_N.setLeft(Eur);
        Eur_N.setRight(end);

        drink.setResults(ResultingCategories.getdrinks());
        eat.setResults(ResultingCategories.getFoods());
        Ame.setResults(ResultingCategories.getAmericans());
        Eur.setResults(ResultingCategories.getEuropean());
        Asi.setResults(ResultingCategories.getAsian());
        end.setResults(ResultingCategories.getAll());
        root.setResults(ResultingCategories.getAll());
    }

    QuestionNode getRoot(){
        return root;
    }

}
