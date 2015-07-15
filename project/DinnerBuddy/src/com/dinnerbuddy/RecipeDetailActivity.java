package com.dinnerbuddy;

import java.util.ArrayList;
import java.util.Arrays;

import com.dinnerbuddy.adapters.RecipeDetailIngrediantsAdapter;
import com.dinnerbuddy.async.ImageDownloadAsyncTask;
import com.dinnerbuddy.objects.Recipe;
import com.dinnerbuddy.util.ViewHolder;
import com.dinnerbuddy.util.YummlyController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

public class RecipeDetailActivity extends Activity {

	private Recipe _selectedRec;
	private final YummlyController _api = new YummlyController();
	private ProgressBar _busyInd;
	private SharedPreferences _favouritesPreferences;
	private ArrayList<Recipe> _favourites;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);
		Intent current = getIntent();
		Recipe selectedItem = (Recipe)current.getExtras().getParcelable("RECIPE");
		_api.GetRecipe(this, selectedItem.Id);
		_busyInd = (ProgressBar)findViewById( R.id.ctrlActivityIndicator);
		_busyInd.setVisibility(View.VISIBLE);	
		_favouritesPreferences = this.getSharedPreferences(
	    	      "com.dinnerbuddy.favourites", Context.MODE_PRIVATE);
		String strItems = _favouritesPreferences.getString("favourites","");
		_favourites = GetFavourites(strItems);
				
		
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
	private void RemoveFromFavourites(Recipe fav)
	{
		for(int i = 0; i< _favourites.size(); i++)
		{
			if(_favourites.get(i).Name.equals(fav.Name))
			{
				_favourites.remove(i);
			}
		}
		Gson gson = new Gson();	
		_favouritesPreferences.edit().putString("favourites", gson.toJson(_favourites)).commit();
	}
	private void AddToFavourites(Recipe rec)
	{		
		_favourites.add(rec);
		Gson gson = new Gson();		
		_favouritesPreferences.edit().putString("favourites", gson.toJson(_favourites)).commit();
	}
	private boolean IsRecipeInFavourites(Recipe rec)
	{
		for(int i = 0; i< _favourites.size(); i++)
		{
			//should be id
			if(_favourites.get(i).Name.equals(rec.Name))
			{
				return true;
			}
		}
		return false;
	}
	

	public void GetRecipeCompleted(Recipe detailRec)
	{
		_selectedRec = detailRec;
		final TextView recipeName = (TextView)findViewById(R.id.txtRecipeTitle);
		recipeName.setText(detailRec.Name);
		final TextView txtSubText = (TextView)findViewById(R.id.txtRecipeSubText);
		txtSubText.setText("Duration : "+ detailRec.Duration +" Minutes");
		final TextView txtRating = (TextView)findViewById(R.id.txtRating);
		txtRating.setText("Rating : "+ detailRec.Rating);
		final ImageView img = (ImageView)findViewById(R.id.imgMain);
		ViewHolder holder = new ViewHolder();
        holder.imageView = img;
        holder.imageURL = detailRec.LargeImageURL;
        new ImageDownloadAsyncTask().execute(holder);
        final ListView lstIngrediants = (ListView)findViewById(R.id.lstIngrediants);
        RecipeDetailIngrediantsAdapter adapter = new RecipeDetailIngrediantsAdapter(this, detailRec.Ingrediants);
        lstIngrediants.setAdapter(adapter);		
        _busyInd.setVisibility(View.GONE);
        final Button btnSave = (Button)findViewById(R.id.btnSave);
        if(IsRecipeInFavourites(_selectedRec))
        {
        	btnSave.setText("Remove");
        }
       
	}
	public void ViewRecipe(View view)
	{
		if(_selectedRec != null)
		{
			Uri uri = Uri.parse(_selectedRec.RecipeURL);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}
	public void SaveRecipe(View view)
	{
		if(_selectedRec != null)
		{
			final Button btnSave = (Button)findViewById(R.id.btnSave);
	        if(IsRecipeInFavourites(_selectedRec))
	        {
	        	RemoveFromFavourites(_selectedRec);
	        	btnSave.setText("Save");
	        }
	        else
	        {
	        	//save to android storage
	        	AddToFavourites(_selectedRec);
	        	btnSave.setText("Remove");
			}
		}
        
	}
	public void BackClicked(View view)
	{
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_detail, menu);
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
