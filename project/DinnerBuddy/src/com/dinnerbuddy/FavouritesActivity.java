package com.dinnerbuddy;

import java.util.ArrayList;
import java.util.Arrays;

import com.dinnerbuddy.adapters.RecipeListAdapter;
import com.dinnerbuddy.objects.Recipe;
import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FavouritesActivity extends ActionBarActivity {

	private SharedPreferences _favouritesPreferences;
	private ArrayList<Recipe> _favourites;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);
		_favouritesPreferences = this.getSharedPreferences(
	    	      "com.dinnerbuddy.favourites", Context.MODE_PRIVATE);
		String strItems = _favouritesPreferences.getString("favourites","");
		_favourites = GetFavourites(strItems);
		ListView list = (ListView)findViewById(R.id.recipeList);
		RecipeListAdapter adapter = new RecipeListAdapter(this,_favourites);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
		 	@Override
		 	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		 	
			  Intent i = new Intent(getApplicationContext(), RecipeDetailActivity.class);
			  // sending data to new activity
			  Recipe selctedItem = (Recipe) parent.getItemAtPosition(position);
			  i.putExtra("RECIPE", selctedItem );
			  startActivity(i);
			}
		});
		
	}
	private ArrayList<Recipe> GetFavourites(String fav)
	{
		ArrayList<Recipe> favourites = new ArrayList<Recipe>();
		if(fav != "")
		{
			Gson gson = new Gson();
			Recipe[] favouriteItems = gson.fromJson(fav,
					Recipe[].class);
			favourites = new ArrayList<Recipe>(Arrays.asList(favouriteItems));
		} 
		return favourites;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourites, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
