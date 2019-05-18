///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              Restaurant - represents a restaurant containing a menu of various items
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
import java.util.ArrayList;
import java.io.Serializable;

public class Restaurant implements Parcelable {

    //Log Tag - Restaurant
    private final String TAG = "in Restaurant";

    //Name of restaurant
    private String restaurant_name;

    //List of items on restaurant menu
    private ArrayList<Menu_Item> menu = new ArrayList<Menu_Item>();

    //Null constructor
    public Restaurant() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Writes data to a Parcel to implement Parcelable
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(restaurant_name);
        out.writeTypedList(menu);
    }

    //Regenerator method to create Restaurant from Parcel
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    //Creates Restaurant from a Parcel
    private Restaurant(Parcel in) {
        restaurant_name = in.readString();
        in.readTypedList(menu, Menu_Item.CREATOR);
    }

    //Constructor
    public Restaurant(String restaurant_name, ArrayList<Menu_Item> menu) {
        this.restaurant_name = restaurant_name;
        this.menu = menu;
    }

    //Getter method for restaurant name
    public String getRestaurant_name() {
        return restaurant_name;
    }

    //Setter method for restaurant name
    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    //Getter method for list of menu items
    public ArrayList<Menu_Item> getMenu() {
        return menu;
    }

    //Setter method for list of menu items
    public void setMenu(ArrayList<Menu_Item> menu) {
        this.menu = menu;
    }

    //Displays restaurant as a string
    @Override
    public String toString() {
        String str = restaurant_name;
        for(Menu_Item mi : menu) {
            if(mi != null)
                str += mi.toString();
        }
        return str;
    }

}
