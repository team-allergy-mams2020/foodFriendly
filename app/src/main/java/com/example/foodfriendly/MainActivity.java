///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              MainActivity - the controller for the activity_main view,
//                     which allows users to select their dietary restrictions.
//
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef

// Online sources (for whole application):
// 1. Stack Overflow
// 2. Youtube - "Firebase Android Tutorial [2/5] : Reading Data From Realtime Database"
//    https://www.youtube.com/watch?v=eCfJMseN0-8&t=695s
// 3. Android Developers
//    https://developer.android.com/
///////////////////////////////////////////////////////////////////////////////

package com.example.foodfriendly;

//Import statements
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //MainActivity Log tag
    public final String TAG = "MainActivity";

    //ArrayList of Type restaurant - will store the list of restaurants in the Firebase database.
    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    /**
     * Overriden onCreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AllergyViewConfiguration controller class instance -
        //used to flag allergenic foods in each restaurant's menu.
        final AllergyViewConfiguration avc = new AllergyViewConfiguration(this);

        //Obtain an instance of the Firebase database.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference root = database.getReference("restaurants");

        // Read from the database using an EventListener
        root.addValueEventListener(new ValueEventListener() {

            /** This method is called once with the initial value and again
             * whenever data at this location is updated.
             * Return the entire database using overridden toString() method.
             * @param dataSnapshot, an instance of the database.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Clear old restaurant data
                restaurants.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Restaurant restaurant = ds.getValue(Restaurant.class);
                    restaurants.add(restaurant);

                }

            }

            /**
             * Failed to read data from Firebase.
             * @param error
             */
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Re-read user's dietary restrictions and store them in the checklist upon opening the application.
        setChecks();

    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item, not to be confused with the Menu_Item class
     * developed for this app.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param avc, an instance of a controller
     * class that contains the functionality of the checkbox list view
     * @param rests, a list of restaurants to be manipulated upon.
     */
    public void flagAllergenicItems(AllergyViewConfiguration avc, ArrayList<Restaurant> rests) {
        //Searches through each menu item of each menu of each restaurant and determines which foods do and do not adhere to
        //the specified user allergens.

        avc.readAllergenData();  //Read allergen data from sharedPreference and store data in class variable
        ArrayList<Integer> userAllergies = avc.getAllergies(); // return allergy data that was just read.

        for(Restaurant r : rests) { //loop through restaurants
            for(Menu_Item mi : r.getMenu()) { //loop through menu of each restaurant
                if(mi != null) { //Eliminate null menu items from the data.
                    int[] menuAllergens = mi.getAllergens();
                    for(int i = 0; i < menuAllergens.length; i++) { //loop through allergens of each menu item
                        if(menuAllergens[i] == 1 && userAllergies.get(i) == 1) {
                            mi.setAvailability(false); //marks menu item with a flag for display purposes
                            break; //As soon as one ingredient matches user allergy, break.
                        }
                        else {
                            mi.setAvailability(true);
                        }

                    }
                }
            }
        }
    }

    /**
     * Store user dietary restrictions and displays the Food Friendly Restaurants page
     * after flagging allergenic menu items.
     * @param v, the View that is to be associated with the method.
     */
    public void convertToFile(View v) {

        LinearLayout checkList = (LinearLayout) findViewById(R.id.checkList);
        ArrayList<Integer> userAllergyData = new ArrayList<>();

        //Loop through checklist to add dietary rescrictions to an ArrayList
        for(int i = 0; i < checkList.getChildCount(); i++) {
            if (checkList.getChildAt(i) instanceof CheckBox) {
                if (((CheckBox) checkList.getChildAt(i)).isChecked()) {
                    userAllergyData.add(1);
                } else {
                    userAllergyData.add(0);

                }
            }
        }
        //Instance of AllergyViewConfiguration - used to store the user allergies in the class.
        AllergyViewConfiguration allergyViewConfiguration = new AllergyViewConfiguration(this);
        allergyViewConfiguration.setAllergies(userAllergyData);

        //Flags unsafe items on the menu.
        flagAllergenicItems(allergyViewConfiguration, restaurants);

        //Sends restaurant ArrayList to DisplayActivity
        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
        intent.putParcelableArrayListExtra("restaurants", restaurants);
        MainActivity.this.startActivity(intent);


    }

    /**
     * Checks pre-defined user dietary restrictions upon re-opening of application.
     */
    public void setChecks() {

        LinearLayout checkList = (LinearLayout) findViewById(R.id.checkList);

        //Instantiate SharedPreference to obtain allergens from "allergens"
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String[] temp = sp.getString("allergens","0,0,0,0,0,0,0,0").split(",");

        //Convert result from Array to ArrayList
        ArrayList<Integer> userAllergyData = new ArrayList<>();

        for(int i = 0; i < temp.length; i++) {
            userAllergyData.add(Integer.parseInt(temp[i]));
        }

        //Check the respective fields of the dietary restriction checklist
        for(int i = 0; i < checkList.getChildCount(); i++) {
            if (checkList.getChildAt(i) instanceof CheckBox) {
                if(userAllergyData.get(i) == 1)
                    ((CheckBox) checkList.getChildAt(i)).setChecked(true);
            }
        }
    }
}