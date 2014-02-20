package com.assignment_1;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		//associate confirmation.xml (layout) with this activity
		setContentView(R.layout.confirmation);

		//Retrieve value entered by user from previous activity 
		String loginValue = getIntent().getStringExtra("Login_Value");

		//load the users value into lbluserEntry
		TextView userDisplay = (TextView)findViewById(R.id.lbluserEntry);

		//Concatenate string to display "Hello <User>!"
		userDisplay.setText("Hello " + loginValue + "!");

		//Get access to lblTimestamp
		TextView timestampTextView = (TextView)findViewById(R.id.lblTimestamp);

		//Set up timestamp format
		SimpleDateFormat systemTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		//load system date and time into timestamp
		String timestamp = systemTime.format(new Date());

		//Display timestamp in lblTimestamp
		timestampTextView.setText("Time of entry: " + timestamp);
	}

}
