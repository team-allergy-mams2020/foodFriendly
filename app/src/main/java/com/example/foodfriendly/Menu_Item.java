package com.example.foodfriendly;

import java.util.ArrayList;

public class Menu_Item {
    private String item_name;
    private ArrayList<String> ingredients;
    private String allergens;

    public Menu_Item(){}

    public Menu_Item(String item_name, ArrayList<String> ingredients, String allergens) {
        this.item_name = item_name;
        this.ingredients = ingredients;
        this.allergens = allergens;


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

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    @Override
    public String toString() {
        String str =  item_name + " ";
        for(String s : ingredients) {
            if (s != null) {
                str += s + " ";
            }
        }
        str += " " + allergens;
        return str;
    }
}
