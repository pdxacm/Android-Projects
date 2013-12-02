package org.acm.windowreplacement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class WindowActivity2 extends Activity {

	Spinner windowPaneTypeSpinner = null;
	Spinner windowFrameTypeSpinner = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_activity2);
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.window_activity2, menu);
		return true;
	}

}
