package com.example.foodfriendly;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AllergyViewConfiguration {
    public final String TAG = "AVC";

    private HashMap<String, Integer> allergies;
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
        allergies = new HashMap<String, Integer>();

        for(int i  = 0; i < allergens.length; i++) {
            //0 False, 1 True
            allergies.put(allergens[i],new Integer(0));
        }
    }

    public HashMap<String, Integer> getAllergies() {
        return allergies;
    }

    public void readAllergenData() {
        InputStream is = context.getResources().openRawResource(R.raw.allergens);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {

                //Split by comma "," to get user allergies
                String fields[] = line.split(",");

                //Read data
                for(int i = 0; i < fields.length; i++) {
                    allergies.put(allergens[i], Integer.parseInt(fields[i]));
                }

            }
        }
        catch (IOException e) {
            Log.wtf(TAG, "ERROR reading data on line" + line);
        }
    }

    public void saveAllergenData() {
        try {

            FileOutputStream fos = context.openFileOutput("allergens.csv", Context.MODE_PRIVATE);
            ArrayList<Integer> allergyData = new ArrayList<Integer>();

            //Convert HashMap to array of int
            Iterator it = allergies.entrySet().iterator();
             while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                allergyData.add((Integer) pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }

            //Save data to file
            for(int i = 0; i < allergyData.size(); i++) {
                try {
                    fos.write((int) allergyData.get((i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            Log.d(TAG, "Failed to retrieve file...");
        }

    }

}
