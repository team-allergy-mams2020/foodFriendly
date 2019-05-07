package com.example.foodfriendly;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Menu_Item implements Parcelable {
    private String item_name;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String allergens;

    private boolean available = true;

    public Menu_Item(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(item_name);
        out.writeStringList(ingredients);
        out.writeString(allergens);
        out.writeInt(available ? 1:0);
    }

    public static final Parcelable.Creator<Menu_Item> CREATOR = new Parcelable.Creator<Menu_Item>() {
        public Menu_Item createFromParcel(Parcel in) {
            return new Menu_Item(in);
        }

        public Menu_Item[] newArray(int size) {
            return new Menu_Item[size];
        }
    };

    private Menu_Item(Parcel in) {
        item_name = in.readString();
        in.readStringList(ingredients);
        allergens = in.readString();
        available = (in.readInt() == 1);
    }

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

    //@Override
    /*public String toString() {
        String str =  item_name + " ";
        for(String s : ingredients) {
            if (s != null) {
                str += s + " ";
            }
        }
        str += " " + allergens;
        return str;
    }*/

    /*private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }*/
}
