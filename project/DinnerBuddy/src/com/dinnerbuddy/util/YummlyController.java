package com.dinnerbuddy.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import com.dinnerbuddy.IngrediantsActivity;
import com.dinnerbuddy.RecipeDetailActivity;
import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class YummlyController {

	private final String YUMMLY_SEARCH_BASE_URI = "http://api.yummly.com/v1/api/recipes?_app_id=9025e66c&_app_key=fc7f68e152a7d3e088cd75f5da008cb5&requirePictures=true%s";
	private final String YUMMLY_GET_BASE_URI = "http://api.yummly.com/v1/api/recipe/%s?_app_id=9025e66c&_app_key=fc7f68e152a7d3e088cd75f5da008cb5";

	private final String YUMMLY_INGREDIANT_PARAM = "&allowedIngredient[]=";
	private HttpClient _client = new DefaultHttpClient();
	private IngrediantsActivity context ;
	private RecipeDetailActivity recipeDetailContext;
	
	public YummlyController()
	{
		
	}
	@SuppressLint("DefaultLocale")
	public void SearchRecipes(IngrediantsActivity caller, ArrayList<String> ingrediants)
	{
		this.context = caller; 
		String ingSearchParams = "";
		for(String ing:ingrediants)
		{
			String request = "";			
			try {
				request = URLEncoder.encode(ing.toLowerCase(),"UTF-8");
				ingSearchParams += this.YUMMLY_INGREDIANT_PARAM + request;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		String request = String.format(this.YUMMLY_SEARCH_BASE_URI ,ingSearchParams);
		new DownloadSearchAPIAsync().execute(request);
	}
 	public void GetRecipe(RecipeDetailActivity caller, String recipeID)
 	{
 		this.recipeDetailContext = caller;
 		String request = String.format(this.YUMMLY_GET_BASE_URI ,recipeID);
		new DownloadRecipeAPIAsyc().execute(request); 		
 	}
	public void SearchRecipeCompleted(String searchResponse)
	{
		this.context.ProceedToRecipeList(searchResponse);
	}
	public void GetRecipeCompleted(String result) {
		
		this.recipeDetailContext.GetRecipeCompleted(JSONParser.parseGetJSON(result));
	}

	public class DownloadSearchAPIAsync extends AsyncTask <String, Integer, String> {

		@Override
		protected String doInBackground(String... urls) {
			
			HttpGet httpGet = new HttpGet(urls[0]);  
			ResponseHandler<String> handler = new BasicResponseHandler();  
			String response = "";
			try {
				 response = _client.execute(httpGet,handler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			SearchRecipeCompleted(result);
		}
	 }
	public class DownloadRecipeAPIAsyc extends AsyncTask <String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			HttpGet httpGet = new HttpGet(params[0]);  
			ResponseHandler<String> handler = new BasicResponseHandler();  
			String response = "";
			try {
				 response = _client.execute(httpGet,handler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
		protected void onPostExecute(String result) {
	        GetRecipeCompleted(result);
	     }
	}
}
