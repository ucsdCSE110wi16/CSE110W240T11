package com.example.yasym.ez_eats.YelpTask.Yelp;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * Created by simon on 2/7/16.
 */
public class Category {

//    @SerializedName("categories")
    private String name;
    private String alias;

    public Category(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public String toString() {
        return name;
    }
}
