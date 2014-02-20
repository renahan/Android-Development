package com.assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LogonActivity extends Activity {

	//This variable holds the value that the user inputs into loginEditText
	EditText loginValue;
	private View userEntry;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		
		//get access to loginEditText field where user enters name
		loginValue = (EditText) findViewById(R.id.loginEditText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logon, menu);
		return true;
	}

	public void enterLogin(View v)
	{
		//Get data user entered
		String strLoginValue = loginValue.getText().toString();

		//if the user hasn't entered their name in loginEditText, let them know
		if( strLoginValue.length() == 0 )
		{
			//Display toast to direct user to input name
			Toast.makeText(this, "You must enter your name in the name field", Toast.LENGTH_LONG).show();
		}
		else
		{
			//create an explicit intent
			Intent confirmationIntent = new Intent(this, ConfirmationActivity.class);

			//pass data to confirmation activity
			confirmationIntent.putExtra("Login_Value", strLoginValue);

			//start the activity
			startActivity(confirmationIntent);
		}

	}

}
