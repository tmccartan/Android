package com.example.assignment1;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EmailComposition extends ActionBarActivity {

	static final String STATE_TO = "to";
	static final String STATE_FROM = "from";
	static final String STATE_CC = "cc";
	static final String STATE_BCC = "bcc";
	static final String STATE_SUBJECT = "subject";
	static final String STATE_BODY = "body";
	
	EditText txtTo;
	EditText txtFrom;
	EditText txtCC;
	EditText txtBCC;
	EditText txtSubject;
	EditText txtBody;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_composition);
		
		txtTo = (EditText)findViewById(R.id.txtTo);
		txtFrom = (EditText)findViewById(R.id.txtFrom);
		txtCC = (EditText)findViewById(R.id.txtCC);
		txtBCC = (EditText)findViewById(R.id.txtBCC);
		txtSubject = (EditText)findViewById(R.id.txtSubject);
		txtBody = (EditText)findViewById(R.id.txtBody);
		if(savedInstanceState != null)
		{
			RestoreForm(savedInstanceState);
		}
		else
		{
			SharedPreferences prefs = this.getSharedPreferences(
		    	      "com.example.app", Context.MODE_PRIVATE);
			txtTo.setText(prefs.getString(STATE_TO,""));
			txtFrom.setText(prefs.getString(STATE_FROM,""));
			txtCC.setText(prefs.getString(STATE_CC,""));
			txtBCC.setText(prefs.getString(STATE_BCC,""));
			txtSubject.setText(prefs.getString(STATE_SUBJECT,""));
			txtBody.setText(prefs.getString(STATE_BODY,""));
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_composition, menu);
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
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    // Save the user's current game state
	    savedInstanceState.putString(STATE_TO, txtTo.getText().toString());
	    savedInstanceState.putString(STATE_FROM, txtFrom.getText().toString());
	    savedInstanceState.putString(STATE_CC, txtCC.getText().toString());
	    savedInstanceState.putString(STATE_BCC, txtBCC.getText().toString());
	    savedInstanceState.putString(STATE_SUBJECT, txtSubject.getText().toString());
	    savedInstanceState.putString(STATE_BODY, txtBody.getText().toString());
	    // Always call the superclass so it can save the view hierarchy state
	    super.onSaveInstanceState(savedInstanceState);
	    SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.example.app", Context.MODE_PRIVATE);
	    prefs.edit().putString(STATE_TO, txtTo.getText().toString()).commit();
	    prefs.edit().putString(STATE_FROM, txtFrom.getText().toString()).commit();
	    prefs.edit().putString(STATE_CC, txtCC.getText().toString()).commit();
	    prefs.edit().putString(STATE_BCC, txtBCC.getText().toString()).commit();
	    prefs.edit().putString(STATE_SUBJECT, txtSubject.getText().toString()).commit();
	    prefs.edit().putString(STATE_BODY, txtBody.getText().toString()).commit();
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	    // Always call the superclass so it can restore the view hierarchy
	    super.onRestoreInstanceState(savedInstanceState);
	    RestoreForm(savedInstanceState);
	    
	}
	@Override
	public void onDestroy()
	{
		SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.example.app", Context.MODE_PRIVATE);
	    prefs.edit().putString(STATE_TO, txtTo.getText().toString()).commit();
	    prefs.edit().putString(STATE_FROM, txtFrom.getText().toString()).commit();
	    prefs.edit().putString(STATE_CC, txtCC.getText().toString()).commit();
	    prefs.edit().putString(STATE_BCC, txtBCC.getText().toString()).commit();
	    prefs.edit().putString(STATE_SUBJECT, txtSubject.getText().toString()).commit();
	    prefs.edit().putString(STATE_BODY, txtBody.getText().toString()).commit();
	    super.onDestroy();
	}
	public void RestoreForm(Bundle savedInstanceState)
	{
		txtTo.setText(savedInstanceState.getString(STATE_TO));
		txtFrom.setText(savedInstanceState.getString(STATE_FROM));
		txtCC.setText(savedInstanceState.getString(STATE_CC));
		txtBCC.setText(savedInstanceState.getString(STATE_BCC));
		txtSubject.setText(savedInstanceState.getString(STATE_SUBJECT));
		txtBody.setText(savedInstanceState.getString(STATE_BODY));
	}
	public void SendEmail(View view)
	{
		Intent intent = new Intent(this, EmailReading.class);
		intent.putExtra(STATE_TO, txtTo.getText().toString());
		intent.putExtra(STATE_FROM, txtFrom.getText().toString());
		intent.putExtra(STATE_CC, txtCC.getText().toString());
		intent.putExtra(STATE_BCC, txtBCC.getText().toString());
		intent.putExtra(STATE_SUBJECT, txtSubject.getText().toString());
		intent.putExtra(STATE_BODY, txtBody.getText().toString());
    	startActivity(intent);	
	}
	public void ClearEmail(View view)
	{
		txtTo.setText(null);
		txtFrom.setText(null);
		txtCC.setText(null);
		txtBCC.setText(null);
		txtSubject.setText(null);
		txtBody.setText(null);
	}
}
