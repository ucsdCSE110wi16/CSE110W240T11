package com.example.yasym.ez_eats.Yelp;

/**
 * Created by simon on 2/7/16.
 */
public class Category {

    public String name;
    public String alias;

    public Category(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String toString() {
        return name;
    }
}
