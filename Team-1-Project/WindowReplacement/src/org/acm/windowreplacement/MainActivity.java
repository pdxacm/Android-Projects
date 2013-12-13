package org.acm.windowreplacement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	EditText state = null;
	EditText monthlyHeatingCost = null;
	Spinner spinner = null;
	Button nextButton = null;
	Customer currentCustomer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//container to hold collected data
		currentCustomer = new Customer();
		
		spinner = (Spinner) findViewById(R.id.energySpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.energy_source_string, android.R.layout.simple_spinner_item);
		
		nextButton = (Button) findViewById(R.id.nextButton);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		addItemSelectedListenerToSpinner();	
		setButtonOnClickListeners();
		
	}
	
	//--------------------------------------------------------------
	// Function: is_valid_state()
	// Purpose: Runs through an array of 50 states 
	public boolean is_valid_state(String state){
		boolean valid = false;
		String[] stateArray = null;
		try {
			stateArray = fill_state_array();
		} catch (FileNotFoundException e) {
			System.out.println("failed at calling fill_state_array()");
			e.printStackTrace();
		}
		
		// Scan array to check for a match
		for(int i = 0; i < stateArray.length; i++) {
			if(state.toLowerCase().equals(stateArray[i].toLowerCase())) {
				valid = true;
			}
		}
		// costumer.new
		// seans new comment
		currentCustomer.set_state(state);
		// Commen
		// butt!
		return valid;
	}
	
	// Function: fill_state_array()
	// Purpose: Reads in states from text file
	public String [] fill_state_array() throws FileNotFoundException {
		BufferedReader br = null;
		String [] stateArray = new String[50];
		InputStream inputStream = getResources().openRawResource(R.raw.states);
		br = new BufferedReader(new InputStreamReader(inputStream));
		
		// Read in the file to an array line by line
		String line = null;
		try {
			int i = 0;
			while ((line = br.readLine()) != null) {
				stateArray[i] = line;	
				i++;
			}
		} catch (IOException e) {
			System.out.println("failed while reading in states");
			e.printStackTrace();
		}
		
		// Close buffer reader
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("buffer reader failed to close");
			e.printStackTrace();
		}
		
		return stateArray;
	}
	
	//--------------------------------------------------------------
	//Listens for button press and then executes the onClick function
	public void setButtonOnClickListeners(){
		
		nextButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				state = (EditText) findViewById(R.id.editText1);
				String custState = state.getText().toString();
				
				if(is_valid_state(custState))
				{
					currentCustomer.set_state(custState);
				
					monthlyHeatingCost = (EditText) findViewById(R.id.monthlyCostEditText);
				
					try{
				
						double monthlyCost = Double.parseDouble(monthlyHeatingCost.getText().toString());
						currentCustomer.set_monthly_heating_cost(monthlyCost);
						Intent intent = new Intent("org.acm.windowreplacement.WindowActivity2");
						intent.putExtra("org.acm.windowreplacement.currentCustomer", currentCustomer);
						startActivity(intent);
					}
					catch(NumberFormatException e){
						
						//clear the text field and put a red hint of invalid input.
						monthlyHeatingCost.getText().clear();
						monthlyHeatingCost.setHintTextColor(Color.parseColor("#FF0000"));
						monthlyHeatingCost.setHint("Invalid Input");
					}
				}
				else
				{
					//clear the text field and put a red hint of invalid input.
					state.getText().clear();
					state.setHintTextColor(Color.parseColor("#FF0000"));
					state.setHint("Invalid State");
				}
			
				
			}});
		
	}

	//------------------------------------------------------------------
	//Listens for Item selection and then does the function
	public void addItemSelectedListenerToSpinner(){
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				currentCustomer.set_heating_type(spinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	//-------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
