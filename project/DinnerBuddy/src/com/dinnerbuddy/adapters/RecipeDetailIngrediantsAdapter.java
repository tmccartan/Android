package com.dinnerbuddy.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dinnerbuddy.R;

public class RecipeDetailIngrediantsAdapter extends ArrayAdapter<String> {

	private final Context context;
    private final ArrayList<String> itemsArrayList;
    
    public RecipeDetailIngrediantsAdapter(Context context, ArrayList<String> itemsArrayList) {
   	 
        super(context, R.layout.recipe_detail_ingrediants_list_template, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.recipe_detail_ingrediants_list_template, parent, false);
        
        //ImageView image = (ImageView) rowView.findViewById(R.id.imgFruitIcon);
        TextView nameView = (TextView) rowView.findViewById(R.id.txtIngrediants);  
        nameView.setText(new String(itemsArrayList.get(position)));
        return rowView;
    }
}

