package org.acm.windowreplacement;

import android.os.Bundle;
import android.os.Parcel;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class WindowActivity2 extends Activity {

	Spinner windowPaneTypeSpinner = null;
	Spinner windowFrameTypeSpinner = null;
	Button nextButton = null;
	Customer currentCustomer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_activity2);
		
		//We need to get the bundle from the last intent 
		Bundle thisBundle = getIntent().getExtras();
		
		currentCustomer  = thisBundle.getParcelable("org.acm.windowreplacement.Customer");
		
		if(currentCustomer == null){
			Log.w("currentCustomer", "is null");
		}
		if(currentCustomer != null){
			
			Log.w("currentCustomer", "Not equal to null");
		
			if(currentCustomer.get_state() != null){
				Log.w("state", currentCustomer.get_state());
			}
			else{
				Log.w("state", "state not set in activityMain");
			}
		}
		else{
			Log.w("currentCustomer", "currentCustomer is null");
		}
		//Log.w("monthlyHeatingCost", Double.toString(currentCustomer.get_monthly_heating_cost()));
		//Log.w("Type", currentCustomer.get_heating_type());
		
		windowPaneTypeSpinner = (Spinner) findViewById(R.id.windowPaneTypeSpinner);
		windowFrameTypeSpinner = (Spinner) findViewById(R.id.windowFrameTypeSpinner);
		
		ArrayAdapter<CharSequence> paneAdapter = ArrayAdapter.createFromResource(this,
				R.array.window_pane_type_string_array, android.R.layout.simple_spinner_item);
		paneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		windowPaneTypeSpinner.setAdapter(paneAdapter);
		
		ArrayAdapter<CharSequence> frameAdapter = ArrayAdapter.createFromResource(this,
				R.array.window_frame_type_string_array, android.R.layout.simple_spinner_item);
		frameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		windowFrameTypeSpinner.setAdapter(frameAdapter);
		
		nextButton = (Button) findViewById(R.id.nextButton);
		
		setButtonOnClickListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.window_activity2, menu);
		return true;
	}
	
public void setButtonOnClickListeners(){
		
		nextButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
			
	
			}});
		
	}

}
