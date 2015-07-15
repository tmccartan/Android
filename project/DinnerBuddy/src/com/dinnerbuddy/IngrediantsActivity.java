package com.dinnerbuddy;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.dinnerbuddy.adapters.IngrediantsListAdapter;
import com.dinnerbuddy.util.YummlyController;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class IngrediantsActivity extends ActionBarActivity {
		
	private final YummlyController _api = new YummlyController();
	ArrayList<String> _items ;
	IngrediantsListAdapter  _adapter;
	private ProgressBar _busyInd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingrediants);
		_items =  new ArrayList<String>();
		ListView lv = (ListView)findViewById(R.id.recipeList);
		_adapter = new IngrediantsListAdapter(this, _items);
		_busyInd = (ProgressBar)findViewById( R.id.busyInd);
		lv.setAdapter(_adapter);
		restoreIngrediants();
	}
	private void restoreIngrediants() {
		SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.dinnerbuddy.ingrediantsActivity", Context.MODE_PRIVATE);
	    String strItems = prefs.getString("items","");   
	    if(strItems != "")
	    {
		    JSONArray jArray;
			try 
			{
				jArray = new JSONArray(strItems);
				if (jArray != null) 
				{ 
			       for (int i=0;i<jArray.length();i++)
			       { 
			    	   _items.add(jArray.get(i).toString());
			       } 
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}
			_adapter.notifyDataSetChanged();
	    }
	}
	@Override
	public void onDestroy()
	{
		saveIngrediants();
		super.onDestroy();
	}
	private void saveIngrediants() {
			SharedPreferences prefs = this.getSharedPreferences(
		    	      "com.dinnerbuddy.ingrediantsActivity", Context.MODE_PRIVATE);
			JSONArray jsArry = new JSONArray(this._items);
		    prefs.edit().putString("items",jsArry.toString()).commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingrediants, menu);
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
	public void AddIngrediant(View view)
	{
		EditText txtResult = (EditText) findViewById(R.id.txtIngrediant);
		String input = txtResult.getText().toString();
		if(input != null && !input.isEmpty())
		{
			this._items.add(input);
	        _adapter.notifyDataSetChanged();
		}
		txtResult.setText("");
	}
	public void ProceedToRecipeList(String recipes)
	{
		_busyInd.setVisibility(View.GONE);	
		Intent recipeList = new Intent(this, RecipeListActivity.class);	
		recipeList.putExtra("INGREDIANTS", recipes);
		startActivity(recipeList);		
	}
	public void SearchClicked (View view)
	{
		if(this._items.size() != 0)
		{
			_busyInd.setVisibility(View.VISIBLE);	
			this._api.SearchRecipes(this, this._items);	
		}
		else
		{
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter ingredients", Toast.LENGTH_SHORT);
			toast.show();
		}
		
	}
}
