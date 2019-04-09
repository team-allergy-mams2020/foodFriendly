package com.example.foodfriendly;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_Item {
    private String item_name;
    private ArrayList<String> ingredients;

    HashMap<String, Integer> generalIngredients;

    public Menu_Item(){}

    public Menu_Item(String item_name, ArrayList<String> ingredients) {
        this.item_name = item_name;
        this.ingredients = ingredients;

    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Integer> getGeneralIngredients() {
        return generalIngredients;
    }

    public void setGeneralIngredients(HashMap<String, Integer> generalIngredients) {
        this.generalIngredients = generalIngredients;
    }

    @Override
    public String toString() {
        String str =  item_name + " ";
        for(String s : ingredients) {
            if (s != null) {
                str += s + " ";
            }
        }
        return str;
    }
}
