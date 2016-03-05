package com.example.yasym.ez_eats;

import java.util.ArrayList;


import java.util.ArrayList;

/**
 * Created by ymno1 on 2/24/2016.
 * Class that has all of the resulting categories of restaurants.
 * Use hardcoding so far. To be refined later.
 */
public class YelpCategories {
    private static ArrayList<String> lightFoods = new ArrayList<String>();
    private static ArrayList<String> snack = new ArrayList<String>();
    private static ArrayList<String> healthDrink = new ArrayList<String>();
    private static ArrayList<String> drink = new ArrayList<String>();
    private static ArrayList<String> normalFoods = new ArrayList<String>();

    private static ArrayList<String> junkFood = new ArrayList<String>();
    private static ArrayList<String> vegOptions = new ArrayList<String>();
    private static ArrayList<String> americanFoods = new ArrayList<String>();
    private static ArrayList<String> asianFoods = new ArrayList<String>();

    private static ArrayList<String> allElse = new ArrayList<String>();
    private static ArrayList<String> all = new ArrayList<String>();
    //More to come

    //temporary hard coding, will be done with xml soon
    static{
        lightFoods.add("icecream");
        lightFoods.add("soup");
        lightFoods.add("salad");
        lightFoods.add("sandwich");

        snack.add("grocery");
        snack.add("icecream");
        snack.add("gasstation");

        healthDrink.add("juicebars");
        healthDrink.add("organic_stores");

        drink.add("coffeeshops");
        drink.add("juicebars");
        drink.add("coffee");
        drink.add("internetcafe");
        drink.add("tea");
        drink.add("grocery");

        normalFoods.add("pizza");
        normalFoods.add("subway");
        normalFoods.add("chipotle");

        junkFood.add("fastfood");

        vegOptions.add("vegetarian");

        americanFoods.add("newamerican");
        americanFoods.add("tradamerican");

        asianFoods.add("asianfusion");
        asianFoods.add("chinese");
        asianFoods.add("thai");
        asianFoods.add("japanese");
        asianFoods.add("korean");

        allElse.add("european");
        allElse.add("african");

        all.add("icecream");
        all.add("soup");
        all.add("salad");
        all.add("grocery");
        all.add("icecream");
        all.add("juicebars");
        all.add("organic_stores");
        all.add("coffeeshops");
        all.add("coffee");
        all.add("internetcafe");
        all.add("tea");
        all.add("pizza");
        all.add("italian");
        all.add("fastfood");
        all.add("vegetarian");
        all.add("newamerican");
        all.add("tradamerican");
        all.add("asianfusion");
        all.add("chinese");
        all.add("thai");
        all.add("japanese");
        all.add("korean");
        all.add("gasstation");
        all.add("chipotle");


    }

    public static ArrayList<String> getLightFoods(){
        return lightFoods;
    }

    public static ArrayList<String> getSnack(){
        return snack;
    }

    public static ArrayList<String> getHealthDrink(){
        return healthDrink;
    }

    public static ArrayList<String> getDrink(){
        return drink;
    }

    public static ArrayList<String> getNormalFoods(){ return normalFoods;}

    public static ArrayList<String> getJunkFood(){ return junkFood;}

    public static ArrayList<String> getVegOptions(){return vegOptions;}

    public static ArrayList<String> getAmericanFoods(){return americanFoods;}

    public static ArrayList<String> getAsianFoods(){return asianFoods;}

    public static ArrayList<String> getAllElse(){return allElse;}

    public static ArrayList<String> getAll(){ return all;}
}
