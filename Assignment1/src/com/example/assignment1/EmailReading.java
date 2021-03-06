package com.example.assignment1;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EmailReading extends ActionBarActivity {

	TextView txtTo;
	TextView txtFrom;
	TextView txtCC;
	TextView txtSubject;
	TextView txtBody;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_reading);
		txtTo = (TextView)findViewById(R.id.txtTo);
		txtFrom = (TextView)findViewById(R.id.txtFrom);
		txtCC = (TextView)findViewById(R.id.txtCC);
		txtSubject = (TextView)findViewById(R.id.txtSubject);
		txtBody = (TextView)findViewById(R.id.txtBody);
		
		Intent intent = getIntent();
		txtTo.setText(intent.getStringExtra((EmailComposition.STATE_TO)));
		txtFrom.setText(intent.getStringExtra((EmailComposition.STATE_FROM)));
		txtCC.setText(intent.getStringExtra((EmailComposition.STATE_CC)));
		txtSubject.setText(intent.getStringExtra((EmailComposition.STATE_SUBJECT)));
		txtBody.setText(intent.getStringExtra((EmailComposition.STATE_BODY)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_reading, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
	public void GoBack(View view)
	{
		finish();
	}
}
