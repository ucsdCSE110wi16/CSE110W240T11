package com.example.yasym.ez_eats;
/**
 * Created by ymno1 on 2/24/2016.
 * Class representing the question tree.
 * Use hardcoding so far, to be refined later.
 */
public class QuizTree {

    private QuestionNode root;

    QuizTree(){
        ConstructTree();
    }

    void ConstructTree(){
        root = new QuestionNode("Are you super hungry?", false);

        QuestionNode thirsty = new QuestionNode("Are you thirsty?", false); //root no
        QuestionNode hurry = new QuestionNode("Are you in a hurry?", false); //root yes

        QuestionNode lightMeal = new QuestionNode("Would you like a light meal?", false);//thirsty no
        QuestionNode healthDrink = new QuestionNode("Are you trying to be healthy?", false); //thirsty yes

        QuestionNode snack = new QuestionNode("I think you want a snack!", true); //light meal no
        QuestionNode lightFoods = new QuestionNode("You want something light!", true); //light meal yes

        QuestionNode fastFood = new QuestionNode("Do you want fast food?", false); //hurry yes
        QuestionNode sitDownRest = new QuestionNode("Do you want to go to a sit down restaurant?",
                false); //hurry no

        QuestionNode normalFoods = new QuestionNode("Here are quick things you can grab!", true);//fastFood no
        QuestionNode junkFood = new QuestionNode("Here's are local fast foods!", true);//fastFood yes

        QuestionNode healthDrinkRes = new QuestionNode("Here are some healthy drink options for you!",
                true);//healthDrink yes
        QuestionNode drinks = new QuestionNode("Here are your drink options!", true); //healthDrink no


        QuestionNode vegetarian = new QuestionNode("Are you a vegetarian?", false); //sitDownRest yes
        //sitDownRest no goes to fastFood node

        QuestionNode vegFoods = new QuestionNode("Here are some vegtastic options!", true);//vegetarian yes
        QuestionNode american = new QuestionNode("Are you in the mood for American food?",
                false);//vegetarian no

        QuestionNode americanFoods = new QuestionNode("Here are good ol' American restaurats!",
                true);//american yes
        QuestionNode asian = new QuestionNode("Are you in the mood for Asian food?", false);//american no

        QuestionNode asianFoods = new QuestionNode("Here are Asian foods near you!", true);//asian yes
        QuestionNode allElse = new QuestionNode("You may like one of these!", true);//asian no

        root.setLeft(thirsty);
        root.setRight(hurry);

        thirsty.setLeft(lightMeal);
        thirsty.setRight(healthDrink);

        healthDrink.setRight(healthDrinkRes);
        healthDrink.setLeft(drinks);

        lightMeal.setLeft(snack);
        lightMeal.setRight(lightFoods);

        hurry.setRight(fastFood);
        hurry.setLeft(sitDownRest);

        fastFood.setLeft(normalFoods);
        fastFood.setRight(junkFood);

        sitDownRest.setLeft(fastFood);
        sitDownRest.setRight(vegetarian);

        vegetarian.setRight(vegFoods);
        vegetarian.setLeft(american);

        american.setRight(americanFoods);
        american.setLeft(asian);

        asian.setRight(asianFoods);
        asian.setLeft(allElse);

        //TODO: set snack results to: grocery store, gas station, frozen yogurt
        //TODO: set lightFoods result to: salads, soups, frozen yogurt, sandwiches
        //TODO: set healthDrinkRes result to: juices, organic drinks
        //TODO: set drinks result to: coffee shops, juices, any drinks
        //TODO: set normalFoods: pizza, subway, chipotle, etc.
        //TODO: set junkFood to: burgers, taco bell, del taco, any other fast food places
        //TODO: set vegOptions: vegetarian stuff
        //TODO: set americanFoods : american foods
        //TODO: set asianFoods : asian foods
        //TODO: set allElse : European, African, and all other foods not American or Asian

        snack.setResults(YelpCategories.getSnack());
        lightFoods.setResults(YelpCategories.getLightFoods());
        healthDrinkRes.setResults(YelpCategories.getHealthDrink());
        drinks.setResults(YelpCategories.getDrink());
        normalFoods.setResults(YelpCategories.getNormalFoods());
        junkFood.setResults(YelpCategories.getJunkFood());
        vegFoods.setResults(YelpCategories.getVegOptions());
        americanFoods.setResults(YelpCategories.getAmericanFoods());;
        asianFoods.setResults(YelpCategories.getAsianFoods());
        allElse.setResults(YelpCategories.getAllElse());
        root.setResults(YelpCategories.getAll());
    }

    QuestionNode getRoot(){
        return root;
    }

}