package com.example.firstproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity 
{

	Button submitBtn;
	TextView label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) 
		    {
		    	submitClk(v);
		    }
		});
		label = (TextView)findViewById(R.id.label);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void submitClk(View v)
	{
		label.setText("You clicked me");
		Intent intent = new Intent(this, SecondActivity.class);
		startActivityForResult(intent, 0);
	}
}
