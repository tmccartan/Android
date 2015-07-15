package com.dinnerbuddy;

import java.util.ArrayList;

import com.dinnerbuddy.adapters.RecipeListAdapter;
import com.dinnerbuddy.objects.Recipe;
import com.dinnerbuddy.util.JSONParser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class RecipeListActivity extends ActionBarActivity {

	private String _recipeJson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);
		SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.dinnerbuddy.recipeListActivity", Context.MODE_PRIVATE);
		Intent intent = getIntent();
		if(intent != null && intent.getExtras() != null)
		{
			_recipeJson = intent.getExtras().getString("INGREDIANTS");
			prefs.edit().putString("INGREDIANTS",_recipeJson);
		}
		else
		{			
			_recipeJson = prefs.getString("INGREDIANTS", "");	
		}
		ArrayList<Recipe> recipes = JSONParser.ParseSearchJSON(_recipeJson);
		ListView list = (ListView)findViewById(R.id.recipeList);
		RecipeListAdapter adapter = new RecipeListAdapter(this,recipes);
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
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  
	}
	@Override
	public void onDestroy()
	{
		SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.dinnerbuddy.recipeListActivity", Context.MODE_PRIVATE);
		prefs.edit().putString("INGREDIANTS",_recipeJson).commit();
		super.onDestroy();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_list, menu);
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
