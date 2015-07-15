package com.dinnerbuddy;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView txtMain = (TextView)findViewById(R.id.txtMain);
		Typeface font = Typeface.createFromAsset(getAssets(), "custom_font.ttf");
		txtMain.setTypeface(font);
		txtMain.setText("Dinner Buddy");
	}
	//Events
	public void IngrediantsClicked(View view)
	{
		Intent intent = new Intent(this, IngrediantsActivity.class);
		startActivity(intent);
	}
	public void FavouritesClicked(View view)
	{
		Intent intent = new Intent(this, FavouritesActivity.class);
		startActivity(intent);
	}
	
}
