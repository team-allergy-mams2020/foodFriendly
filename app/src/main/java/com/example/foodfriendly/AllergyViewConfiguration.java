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
    public final String TAG = "AVC";
    public final String FILE_NAME = "allergens";

    private Map<String, Integer> allergies;
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
    private Context context;

    public AllergyViewConfiguration() {

    }

    public AllergyViewConfiguration(Context context) {
        this.context = context;
        allergies = new LinkedHashMap<String, Integer>();
        for(int i  = 0; i < allergens.length; i++) {
            //0 False, 1 True
            allergies.put(allergens[i],new Integer(0));
        }
    }

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

    public void setAllergies(ArrayList<Integer> userAllergyData) {

        Iterator it = allergies.entrySet().iterator();
        int count = 0;

        while(it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            pair.setValue(userAllergyData.get(count));
            count ++;
        }

        saveAllergenData();

    }

    public void readAllergenData() {
//        allergies.clear();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String allergyString = sp.getString("allergens","0,0,0,0,0,0,0,0");
        String[] temp = allergyString.split(",");

        Iterator it = allergies.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            pair.setValue(temp[count]);
            count ++;
        }

    }

    public void saveAllergenData() {

        ArrayList<Integer> allergyData = new ArrayList<Integer>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        //Convert HashMap to ArrayList of Integer
        Iterator it = allergies.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            allergyData.add((Integer) pair.getValue());
        }
        String allergyString = android.text.TextUtils.join(",",allergyData);
        editor.clear();
        editor.putString("allergens",allergyString);
        editor.commit();
        Log.d(TAG, sp.getString("allergens","0,0,0,0,0,0,0,0"));

    }


}
