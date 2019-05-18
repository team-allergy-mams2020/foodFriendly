///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              MenuItemRecyclerViewAdapter - RecyclerViewAdapter that controls the display of menu items
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////
package com.example.foodfriendly;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemRecyclerViewAdapter extends RecyclerView.Adapter<MenuItemRecyclerViewAdapter.ViewHolder>{

    //Log Tag - MenuItemRecyclerViewAdapter (Full name too long to use)
    private static final String TAG = "in MIRecyclViewAdapter";

    //RecyclerViewAdapter MUST receive a Context
    private Context mContext;

    //List of menu items
    private ArrayList<Menu_Item> mItems = new ArrayList<>();

    //List of availabilities
    private ArrayList<Boolean> mAvailable = new ArrayList<>();

    //StringBuffer to help display ingredients
    private StringBuffer ingredients = new StringBuffer();

    //Constructor method
    public MenuItemRecyclerViewAdapter(ArrayList<Menu_Item> menu_items, ArrayList<Boolean> available, Context context){
        mItems = menu_items;
        mAvailable = available;
        mContext = context;
    }

    //Creates a ViewHolder based on layout_listitem, which is calibrated for displaying menu items.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Adds custom style and text to each item in RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Displays item name
        holder.itemName.setText(mItems.get(position).getItem_name());

        //Changes color of RecyclerView ViewHolder based on item availability
        if(mAvailable.get(position)) {
            holder.itemAvailability.setText("Safe to eat!");
            holder.itemAvailability.setTextColor(Color.rgb(0,204,0));
            holder.parentLayout.setBackgroundColor(Color.rgb(201,222,193));


        } else {
           holder.itemAvailability.setText("Unsafe to eat");
           holder.itemAvailability.setTextColor(Color.rgb(255,0,0));
            holder.parentLayout.setBackgroundColor(Color.rgb(250,234,230));
        }

        //Uses StringBuffer to build a comma-and-space separated String from ingredients
        for(String s : mItems.get(position).getIngredients()) {
            if(s != null) {
                Log.d(TAG,s);
                ingredients.append(s);
                ingredients.append(", ");
            }
        }
        ingredients.deleteCharAt(ingredients.length()-1);
        ingredients.deleteCharAt(ingredients.length()-1);
        holder.mainIngredients.setText(ingredients);
        ingredients = new StringBuffer();

        //Position in list for iterating through the parameter lists
        final int pos = position;

        //Logs the item that was clicked
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: "+mItems.get(pos));
            }
        });
    }

    //Gets number of items
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    //Links parts of the ViewHolder to proper data
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName, itemAvailability, mainIngredients;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.item_name);
            itemAvailability=itemView.findViewById(R.id.availability);
            mainIngredients=itemView.findViewById(R.id.ingredients);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }


}
