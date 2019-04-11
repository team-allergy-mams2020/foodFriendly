package com.example.foodfriendly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final AllergyViewConfiguration avc = new AllergyViewConfiguration(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference root = database.getReference("restaurants");

        // Read from the database
        root.addValueEventListener(new ValueEventListener() {
            ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Return the entire database using overridden toString() method.

                restaurants.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Restaurant restaurant = ds.getValue(Restaurant.class);
                    restaurants.add(restaurant);
                    Log.d(TAG, "Restaurant: " + restaurant.toString());

                }
                flagAllergenicItems(avc,restaurants);

                //Check to determine if flagAllergenicItems functions properly.
                for(Restaurant r : restaurants) { //loop through restaurants
                    for(Menu_Item mi : r.getMenu()) { //loop through menu of each restaurant
                        if(mi != null) { //Eliminate null menu items from the data set (bug).
                            Log.d(TAG, mi.getItem_name() + ", Availability: " + mi.isAvailable());
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void flagAllergenicItems(AllergyViewConfiguration avc, ArrayList<Restaurant> rests) {

        avc.readAllergenData();  //Read allergen data from allergens.csv and store data in class variable
        ArrayList<Integer> userAllergies = avc.getAllergies(); // return allergy data that was just read.

        for(Restaurant r : rests) { //loop through restaurants
            for(Menu_Item mi : r.getMenu()) { //loop through menu of each restaurant
                if(mi != null) { //Eliminate null menu items from the data.
                    int[] menuAllergens = mi.getAllergens();
                    ArrayList<Integer> userAllergiesList = avc.getAllergies();
                    for(int i = 0; i < menuAllergens.length; i++) { //loop through allergens of each menu item
                        if(menuAllergens[i] == 1 && userAllergiesList.get(i) == 1) {
                            mi.setAvailability(false); //marks menu item with a flag for display purposes
                            break; //As soon as one ingredient matches user allergy, break.
                        }

                    }
                }
            }
        }
    }
}