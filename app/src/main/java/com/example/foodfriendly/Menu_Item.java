///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              Menu_Item - Object representing an individual foodstuff, with various metadata.
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////

package com.example.foodfriendly;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Menu_Item implements Parcelable {

    //Log Tag - Menu_Item
    private final String TAG = "in Menu_Item";

    //Name of item that the user sees
    private String item_name;

    //List of food ingredients that the user sees
    private ArrayList<String> ingredients = new ArrayList<>();

    //Single String of 8 comma-separated Integers (0 or 1) representing the allergies that the foodstuff would trigger.
    //The order of allergies is the same as that of the String array in AllergyViewConfiguration
    //(Dairy, Egg, Fish, Gluten, Meat, Peanut, Tree Nut, Wheat)
    private String allergens;

    //Represents whether the foodstuff is safe to consume for the user.
    private boolean available = true;

    //Null constructor
    public Menu_Item(){}

    @Override
    public int describeContents() {
        return 0;
    }

    //Writes data to a Parcel to implement Parcelable
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(item_name);
        out.writeStringList(ingredients);
        out.writeString(allergens);
        out.writeInt(available ? 1:0);
    }

    //Regenerator method for Parcelable
    public static final Parcelable.Creator<Menu_Item> CREATOR = new Parcelable.Creator<Menu_Item>() {
        public Menu_Item createFromParcel(Parcel in) {
            return new Menu_Item(in);
        }

        public Menu_Item[] newArray(int size) {
            return new Menu_Item[size];
        }
    };

    //Reads data from Parcel to create a Menu_Item
    private Menu_Item(Parcel in) {
        item_name = in.readString();
        in.readStringList(ingredients);
        allergens = in.readString();
        available = (in.readInt() == 1);
    }

    //General constructor
    public Menu_Item(String item_name, ArrayList<String> ingredients, String allergens) {
        this.item_name = item_name;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    //Item name getter
    public String getItem_name() {
        return item_name;
    }

    //Item name setter
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    //Ingredient list getter
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    //Ingredient list setter
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    //Allergens getter - removes commas and returns an int array, not a String
    public int[] getAllergens() {
        String allergensNoCommas = allergens.replace(",","");
        int[] allergenData = new int[allergensNoCommas.length()];
        for(int i = 0; i < allergensNoCommas.length(); i++) {
            allergenData[i] = allergensNoCommas.charAt(i)-'0';
        }
        return allergenData;
    }

    //Allergens setter - requires a String
    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    //Gets whether the item is available to eat
    public boolean isAvailable() {
        return available;
    }

    //Sets whether the item is available to eat
    public void setAvailability(boolean isAvailable) {available = isAvailable;}

}
