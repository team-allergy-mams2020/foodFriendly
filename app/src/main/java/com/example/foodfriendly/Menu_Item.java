package com.example.foodfriendly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Menu_Item implements Serializable {
    private String item_name;
    private ArrayList<String> ingredients;
    private String allergens;

    private boolean available = true;

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

    public int[] getAllergens() {
        String allergensNoCommas = allergens.replace(",","");
        int[] allergenData = new int[allergensNoCommas.length()];
        for(int i = 0; i < allergensNoCommas.length(); i++) {
            allergenData[i] = allergensNoCommas.charAt(i)-'0';
        }
        return allergenData;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean isAvailable) {available = isAvailable;}

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

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }
}
