///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              AllergyViewConfiguration - Manages dietary restriction information, writing
//                     into and reading from SharedPreferences.
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////


package com.example.foodfriendly;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class AllergyViewConfiguration {

    //Log Tag - AVC
    public final String TAG = "AVC";

    //Filename
    public final String FILE_NAME = "allergens";

    //A map linking each allergen (denoted by a String) to an Integer acting as a boolean value.
    //(e.g., 1 = true, 0 = false)
    private Map<String, Integer> allergies;

    //Array of Strings denoting allergens, to be mapped to Integers.
    private String[] allergens = {
            "Dairy",
            "Egg",
            "Fish",
            "Gluten",
            "Meat",
            "Peanut",
            "Tree Nut",
            "Wheat"
    };

    //Context for context-sensitive methods
    private Context context;

    //Null constructor
    public AllergyViewConfiguration() {
    }

    //Constructor with context and non-null data
    public AllergyViewConfiguration(Context context) {
        this.context = context;

        //Populates HashMap with Integers mapped to each allergen (a String). All allergens are false
        //by default.
        allergies = new LinkedHashMap<String, Integer>();
        for(int i  = 0; i < allergens.length; i++) {
            //0 False, 1 True
            allergies.put(allergens[i],new Integer(0));
        }
    }

    //Allergy getter method
    public ArrayList<Integer> getAllergies() {
        ArrayList<Integer> userAllergyData = new ArrayList<Integer>();

        //Convert LinkedHashMap to ArrayList of Integer
        int i = 0;
        Iterator it = allergies.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            i = Integer.parseInt(pair.getValue().toString());
            userAllergyData.add(i);
        }

        return userAllergyData;
    }

    //Allergy setter method - because of how Android Studios works, the complete function is broken
    //up into 2 methods. This one overwrites the HashMap with an entered Integer ArrayList.
    public void setAllergies(ArrayList<Integer> userAllergyData) {

        //Iterates over all 8 allergies, overwriting the preexisting value with the parameter's.
        Iterator it = allergies.entrySet().iterator();
        int count = 0;

        while(it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            pair.setValue(userAllergyData.get(count));
            count ++;
        }

        //Call second method to store data while app is closed
        saveAllergenData();

    }

    //Reads SharedPreferences value for allergens into HashMap
    public void readAllergenData() {

        //Accesses allergen string stored in SharedPreferences and converts to array
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String allergyString = sp.getString("allergens","0,0,0,0,0,0,0,0");
        String[] temp = allergyString.split(",");

        //Iterates over array and reads each value into HashMap, overwriting previous allergen value
        Iterator it = allergies.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            pair.setValue(temp[count]);
            count ++;
        }

    }

    //Writes current allergen data to a SharedPreference object, enabling persistent data storage.
    public void saveAllergenData() {

        //Accesses SharedPreferences
        ArrayList<Integer> allergyData = new ArrayList<Integer>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        //Convert HashMap to ArrayList of Integer
        Iterator it = allergies.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            allergyData.add((Integer) pair.getValue());
        }

        //Reads ArrayList into a String that is stored within the SharedPreferences object.
        String allergyString = android.text.TextUtils.join(",",allergyData);
        editor.clear();
        editor.putString("allergens",allergyString);
        editor.commit();

        //Checks to see if the operation completed successfully
        Log.d(TAG, sp.getString("allergens","0,0,0,0,0,0,0,0"));

    }


}
