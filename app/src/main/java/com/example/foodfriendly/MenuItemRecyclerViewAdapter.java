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

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Menu_Item> mItems = new ArrayList<>();
    private ArrayList<Boolean> mAvailable = new ArrayList<>();
    private StringBuffer ingredients = new StringBuffer();

    public MenuItemRecyclerViewAdapter(ArrayList<Menu_Item> menu_items, ArrayList<Boolean> available, Context context){
        mItems = menu_items;
        mAvailable = available;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.itemName.setText(mItems.get(position).getItem_name());
        if(mAvailable.get(position)) {
            holder.itemAvailability.setText("Edible!");
            holder.itemAvailability.setTextColor(Color.rgb(0,204,0));
            holder.parentLayout.setBackgroundColor(Color.rgb(201,222,193));


        } else {
           holder.itemAvailability.setText("Not edible");
           holder.itemAvailability.setTextColor(Color.rgb(255,0,0));
            holder.parentLayout.setBackgroundColor(Color.rgb(250,234,230));
        }
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

        final int pos = position;

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: "+mItems.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

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
