package com.dinnerbuddy.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dinnerbuddy.objects.Recipe;

public class JSONParser {

	public static ArrayList<Recipe> ParseSearchJSON(String response)
	{
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		try {
			JSONObject results = new JSONObject(response);
			JSONArray matches = results.getJSONArray("matches");
			recipes = new ArrayList<Recipe>();
			for(int i =0; i< matches.length(); i++)
			{
				JSONObject jsRec = matches.getJSONObject(i);
				Recipe curRec = new Recipe();
				curRec.Name = jsRec.getString("recipeName");
				curRec.Id = jsRec.getString("id");
				curRec.SmallImageURL = jsRec.getJSONArray("smallImageUrls").getString(0);
				recipes.add(curRec);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipes;
	}
	public static Recipe parseGetJSON(String response)
	{
		Recipe rec = new Recipe();
		try {
			JSONObject result = new JSONObject(response);
			rec.Id = result.getString("id");
			rec.Name = result.getString("name");
			rec.Yields = result.getInt("numberOfServings");
			rec.Duration = result.getInt("totalTimeInSeconds")/60;
			rec.Rating = result.getInt("rating");
			JSONArray imagesArray = result.getJSONArray("images");
			rec.SmallImageURL = 
					imagesArray.getJSONObject(0).getString("hostedSmallUrl");
			rec.LargeImageURL = 
					imagesArray.getJSONObject(0).getString("hostedLargeUrl");
			rec.Ingrediants  = new ArrayList<String>();
			JSONArray ingArray = result.getJSONArray("ingredientLines");
			for(int i =0; i< ingArray.length();i++)
			{
				rec.Ingrediants.add(ingArray.getString(i));
			}
			JSONObject source =  result.getJSONObject("source");
			rec.RecipeURL = source.getString("sourceRecipeUrl");
					
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rec;
	}
}
