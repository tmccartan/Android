package com.dinnerbuddy.adapters;

import java.util.ArrayList;

import com.dinnerbuddy.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class IngrediantsListAdapter extends ArrayAdapter<String> {

		 private final Context context;
	     private final ArrayList<String> itemsArrayList;
	     
	     public IngrediantsListAdapter(Context context, ArrayList<String> itemsArrayList) {
	    	 
	         super(context, R.layout.ingrediants_list_template, itemsArrayList);

	         this.context = context;
	         this.itemsArrayList = itemsArrayList;
	     }
	     @Override
	     public View getView(final int position, View convertView, ViewGroup parent) {

	         LayoutInflater inflater = (LayoutInflater) context
	             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	         View rowView = inflater.inflate(R.layout.ingrediants_list_template, parent, false);
	         
	         Button deleteBtn = (Button)rowView.findViewById(R.id.btnSearch);
	         deleteBtn.setOnClickListener(new View.OnClickListener(){
	             @Override
	             public void onClick(View v) { 
	                 //do something
	            	 itemsArrayList.remove(position); //or some other task
	                 notifyDataSetChanged();
	             }
	         });
	         
	         //ImageView image = (ImageView) rowView.findViewById(R.id.imgFruitIcon);
	         TextView nameView = (TextView) rowView.findViewById(R.id.txtIngrediants);  
	         nameView.setText(new String(itemsArrayList.get(position)));
	         //image.setImageResource(itemsArrayList.get(position).image);
	         //nameView.setText(itemsArrayList.get(position).name);
	         //nameView.setTextColor(Color.parseColor(itemsArrayList.get(position).colour));
	         return rowView;
	     }


	
}
