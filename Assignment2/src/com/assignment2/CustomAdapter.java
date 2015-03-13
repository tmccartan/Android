package com.assignment2;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter<Item> {

	 private final Context context;
     private final ArrayList<Item> itemsArrayList;
     
     public CustomAdapter(Context context, ArrayList<Item> itemsArrayList) {
    	 
         super(context, R.layout.fruit_row_template, itemsArrayList);

         this.context = context;
         this.itemsArrayList = itemsArrayList;
     }
     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater inflater = (LayoutInflater) context
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         View rowView = inflater.inflate(R.layout.fruit_row_template, parent, false);

         
         ImageView image = (ImageView) rowView.findViewById(R.id.imgFruitIcon);
         TextView nameView = (TextView) rowView.findViewById(R.id.txtFruitName);  
         nameView.setText(itemsArrayList.get(position).name);
         image.setImageResource(itemsArrayList.get(position).image);
         nameView.setText(itemsArrayList.get(position).name);
         nameView.setTextColor(Color.parseColor(itemsArrayList.get(position).colour));
         return rowView;
     }


}
