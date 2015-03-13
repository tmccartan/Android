package com.assignment2;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		ListView list = (ListView)findViewById(R.id.fruitList);
		CustomAdapter adapter = new CustomAdapter(this,generateData());
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			 	@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
			 		  Intent i = new Intent(getApplicationContext(), FruitDetail.class);
		              // sending data to new activity
			 		  Item selctedItem = (Item) parent.getItemAtPosition(position);
		              i.putExtra("FRUIT", selctedItem );
		              startActivity(i);
				}
			});
	}
   private ArrayList<Item> generateData(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(getString(R.string.txtBanana),R.drawable.banana_icon,"#FFFF00"));
        items.add(new Item(getString(R.string.txtOrange), R.drawable.orange_icon,"#FF6633"));
        items.add(new Item(getString(R.string.txtApple),R.drawable.apple_icon,"#33FF00"));
        items.add(new Item(getString(R.string.txtStrawberry),R.drawable.strawberry_icon,"#FF0000"));
        return items;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
