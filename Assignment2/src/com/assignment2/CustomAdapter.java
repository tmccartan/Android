package com.assignment2;

import java.util.ArrayList;

import android.content.Context;
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

         // 1. Create inflater 
         LayoutInflater inflater = (LayoutInflater) context
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         // 2. Get rowView from inflater
         View rowView = inflater.inflate(R.layout.fruit_row_template, parent, false);

         // 3. Get the two text view from the rowView

        // TextView initialView = (TextView) rowView.findViewById(R.id.txtFruitInitial);
         ImageView image = (ImageView) rowView.findViewById(R.id.imgFruitIcon);
         TextView nameView = (TextView) rowView.findViewById(R.id.txtFruitName);
         // 4. Set the text for textView 
         nameView.setText(itemsArrayList.get(position).name);
         //initialView.setText(itemsArrayList.get(position).initial);
         image.setImageResource(itemsArrayList.get(position).image);

         // 5. retrn rowView
         return rowView;
     }


}
