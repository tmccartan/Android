package com.dinnerbuddy.adapters;

import java.util.ArrayList;

import com.dinnerbuddy.R;
import com.dinnerbuddy.async.ImageDownloadAsyncTask;
import com.dinnerbuddy.objects.Recipe;
import com.dinnerbuddy.util.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {
	
	 private final Context context;
     private final ArrayList<Recipe> itemsArrayList;
     
     public RecipeListAdapter(Context context, ArrayList<Recipe> itemsArrayList) {
    	 
         super(context, R.layout.recipe_list_item_template, itemsArrayList);

         this.context = context;
         this.itemsArrayList = itemsArrayList;
     }
     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater inflater = (LayoutInflater) context
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View rowView = inflater.inflate(R.layout.recipe_list_item_template, parent, false);         
         TextView nameView = (TextView) rowView.findViewById(R.id.txtRecipeTitle);  
         ImageView image = (ImageView) rowView.findViewById(R.id.imgRecipe);
         nameView.setText(itemsArrayList.get(position).Name);         
         //View Holder pattern
         ViewHolder holder = new ViewHolder();
         holder.imageView = image;
         holder.imageURL = itemsArrayList.get(position).SmallImageURL;
         new ImageDownloadAsyncTask().execute(holder);
         return rowView;
     }
    
     
}
