package com.example.yasym.ez_eats;

import java.util.ArrayList;

/**
 * Created by ymno1 on 2/24/2016.
 * Class that has all of the resulting categories of restaurants.
 * Use hardcoding so far. To be refined later.
 */
public class ResultingCategories {
    private static ArrayList<String> drinks = new ArrayList<String>();
    private static ArrayList<String> foods = new ArrayList<String>();
    private static ArrayList<String> americans = new ArrayList<String>();
    private static ArrayList<String> asian = new ArrayList<String>();
    private static ArrayList<String> european = new ArrayList<String>();
    private static ArrayList<String> all = new ArrayList<String>();
    //More to come

    //temporary hard coding, will be done with xml soon
    static{
        drinks.add("beer_and_wine");
        drinks.add("icecream");
        drinks.add("juicebars");
        foods.add("donuts");
        foods.add("pretzels");
        foods.add("bakeries");
        americans.add("newamerican");
        americans.add("tradamerican");
        americans.add("latin");
        asian.add("chinese");
        asian.add("japaneses");
        asian.add("thai");
        european.add("italian");
        european.add("german");
        european.add("greek");

        all.add("beer_and_wine");
        all.add("icecream");
        all.add("juicebars");
        all.add("donuts");
        all.add("pretzels");
        all.add("bakeries");
        all.add("newamerican");
        all.add("tradamerican");
        all.add("latin");
        all.add("chinese");
        all.add("japaneses");
        all.add("thai");
        all.add("italian");
        all.add("german");
        all.add("greek");
    }

    public static ArrayList<String> getdrinks(){
        return drinks;
    }

    public static ArrayList<String> getFoods(){
        return foods;
    }

    public static ArrayList<String> getAmericans(){
        return americans;
    }

    public static ArrayList<String> getAsian(){
        return asian;
    }

    public static ArrayList<String> getEuropean(){ return european;}

    public static ArrayList<String> getAll(){ return all;}
}
