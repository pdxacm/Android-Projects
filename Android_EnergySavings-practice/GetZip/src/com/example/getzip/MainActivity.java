package com.example.getzip;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	
	Button search;
	TextView cityLabel, stateLabel, state, city;
	EditText zipcode;
	String myResponse;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void initialize(){
	
		zipcode = (EditText) findViewById(R.id.etZip);
		cityLabel = (TextView) findViewById(R.id.tvCityLabel);
		city = (TextView) findViewById(R.id.tvCity);
		stateLabel = (TextView) findViewById(R.id.tvStateLabel);
		state = (TextView) findViewById(R.id.tvState);
		
		search = (Button) findViewById(R.id.btn1);
		search.setOnClickListener(this);
			
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String zip = zipcode.getText().toString();
		new AccessAPI().execute(zip);
		
		
	}
		
	private class AccessAPI extends AsyncTask<String, Void, String> {
        @Override
		protected String doInBackground(String... yourZip) {
			// TODO Auto-generated method stub
        	String reply = null;
        	HttpEntity entity = null;
       
        	
        	DefaultHttpClient myClient = new DefaultHttpClient();
        	HttpContext localContext = new BasicHttpContext();
        	HttpGet get = new HttpGet("http://ZiptasticAPI.com/" + yourZip[0]);
    		HttpResponse response = null; 
    		
    		try {
    			response = myClient.execute(get, localContext);
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
				entity = response.getEntity();
				reply = EntityUtils.toString(entity);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
    		return reply;
		}
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	
        	String tempCity = null, tempState = null;
        	
        	try {
				JSONObject object = (JSONObject) new JSONTokener(result).nextValue();
				tempCity = object.getString("city");
				tempState = object.getString("state");
				
				city.setText(tempCity);
				state.setText(tempState);
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
       }

		
    }



	
	/*************************
	 * The code below shows the beginning steps
	 * to an alternate method to call an HTTP Web
	 * API. ( the implementation below does not have 
	 * the full code required to call an HTTP Web API).
	 *************************/
	
	/****
	
	private class AccessAPI extends AsyncTask<Void, Void, String> {
        @Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
        	
        	Object content = null;
        	
        	URL myURL = null;
			try {
				myURL = new URL("http://ZiptasticAPI.com/97213");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
				content = myURL.getContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return content.toString();
        	
		}
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	
    		city.setText(result);

        	
       }
        
        ***/
	
	
	
	
	
	
	
	
	
	

}
