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
        root = new QuestionNode("Do you want snack?", false);
        QuestionNode DorE = new QuestionNode("Do you want to Drink or Eat?", false);
        QuestionNode Ame_N = new QuestionNode("Do you want American food?", false);
        QuestionNode Asi_N = new QuestionNode("Do you want Asian food?", false);
        QuestionNode Eur_N = new QuestionNode("Do you want European food?", false);
        QuestionNode Ame = new QuestionNode("American results!",true);
        QuestionNode Asi = new QuestionNode("Asian results!",true);
        QuestionNode Eur = new QuestionNode("European results!", true);
        QuestionNode drink = new QuestionNode("Drink results!", true);
        QuestionNode eat = new QuestionNode("Eat results!", true);
        QuestionNode end = new QuestionNode("Query ends!", true);
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
    }

    QuestionNode getRoot(){
        return root;
    }

}
