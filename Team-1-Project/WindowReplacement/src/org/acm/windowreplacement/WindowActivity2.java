package org.acm.windowreplacement;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class WindowActivity2 extends Activity {

	Spinner windowPaneTypeSpinner = null;
	Spinner windowFrameTypeSpinner = null;
	Button nextButton = null;
	Button addWindowButton = null;
	Customer currentCustomer = null;
	EditText windowHeightEditText = null;
	EditText windowWidthEditText = null;
	EditText windowQuantityEditText = null;
	AlertDialog.Builder invalidInputAlert = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_activity2);
		
		//We need to get the bundle from the intent from the last activity 
		Bundle bundle = getIntent().getExtras();
		currentCustomer  = bundle.getParcelable("org.acm.windowreplacement.Customer");
		
		// Finds the spinners by view and populates them with a string array
		initializeSpinners();
		
		// Initialize buttons
		nextButton = (Button) findViewById(R.id.nextButton);
		addWindowButton = (Button) findViewById(R.id.addWindowButton);
		
		// Initialize EditTexts
		windowHeightEditText = (EditText) findViewById(R.id.windowHeightTextEdit);
		windowWidthEditText  = (EditText) findViewById(R.id.windowWidthTextEdit);
		windowQuantityEditText = (EditText) findViewById(R.id.windowQuantityEditText);
				
		// Call listener
		setButtonOnClickListeners();
	}

	// Create a pop up error message
	private void invalidInputAlertCreator(String errorMessage) {
		invalidInputAlert = new AlertDialog.Builder(this);
		invalidInputAlert.setMessage(errorMessage)
	       .setCancelable(false)
	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               dialog.cancel();
	           }
	       });
		AlertDialog alert = invalidInputAlert.create();
		alert.show();
	}
	
	// Populate the two spinners with the pane and frame types
	public void initializeSpinners(){
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
	}


	// Button listener
	public void setButtonOnClickListeners(){
		
		nextButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
	
				}
			});
		
		addWindowButton.setOnClickListener(new OnClickListener(){
			
			@Override
			// Create a new window object and store it in the list
			public void onClick(View v) {
				Window toAdd = new Window();
				
				try {
					// Set all window information
					float windowHeight = Float.parseFloat(windowHeightEditText.getText().toString());
					toAdd.set_window_height(windowHeight);
					
					float windowWidth = Float.parseFloat(windowWidthEditText.getText().toString());
					toAdd.set_window_width(windowWidth);
					
					int windowQuantity = Integer.parseInt(windowQuantityEditText.getText().toString());
					toAdd.set_window_quantity(windowQuantity);
					
					if(windowQuantity < 1) {
						throw new QuantityException();
					}
					
					String windowPaneType  = windowPaneTypeSpinner.getSelectedItem().toString();
					toAdd.set_window_pane_type(windowPaneType);
					
					String windowFrameType = windowFrameTypeSpinner.getSelectedItem().toString();
					toAdd.set_window_frame_type(windowFrameType);
					
					// Add to window list
					currentCustomer.add_window(toAdd);
					
					// Reset values
					reset();
				} 
				catch (QuantityException e) {
					windowQuantityEditText.getText().clear();
					invalidInputAlertCreator("Please enter a quantity of one or greater");
				}
				catch(NumberFormatException e) {
					toAdd = null;
					invalidInputAlertCreator("Invalid Input");
					reset();
				} 
			}	
		});
	}
	
	// Reset all xml fields
	public void reset() {
		windowHeightEditText.getText().clear();
		windowWidthEditText.getText().clear();
		windowQuantityEditText.getText().clear();
		windowFrameTypeSpinner.setSelection(0);
		windowPaneTypeSpinner.setSelection(0);
	}
	
	// Too few windows exception
	public class QuantityException extends Exception {
		private static final long serialVersionUID = 1L;
		public QuantityException() {
	        super();
	    }
	}

}













