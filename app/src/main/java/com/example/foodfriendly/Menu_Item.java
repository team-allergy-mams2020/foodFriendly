package com.example.foodfriendly;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_Item {
    private String item_name;
    private ArrayList<String> ingredients = new ArrayList<String>();
    private HashMap<String, Boolean> allergies;
//    private String[] allergens = {
//            "Dairy",
//            "Egg",
//            "Fish",
//            "Gluten",
//            "Meat",
//            "Peanut",
//            "Soy",
//            "Tree Nut",
//            "Wheat"
//    };

    public Menu_Item(){}

    public Menu_Item(String item_name, ArrayList<String> ingredients) {
        this.item_name = item_name;
        this.ingredients = ingredients;
//        allergies = new HashMap<>();
//
//        for(int i  = 0; i < allergens.length; i++) {
//            allergies.put(allergens[i],Boolean.FALSE);
//        }
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
