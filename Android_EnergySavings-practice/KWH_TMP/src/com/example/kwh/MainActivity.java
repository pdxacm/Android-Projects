package com.example.kwh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	
	JSONArray my_data = null;
	Button search, graph;
	TextView cityLabel, stateLabel, state, city, KWHLabel, kwh;
	EditText zipcode;
	String stateFinal;
	String KWHurl;
	
	

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
		KWHLabel = (TextView) findViewById(R.id.tvKWHLabel);
		kwh = (TextView) findViewById(R.id.tvKWH);
		
		search = (Button) findViewById(R.id.btn1);
		graph = (Button) findViewById(R.id.btn2);
		search.setOnClickListener(this);
		graph.setOnClickListener(this);
			
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.equals(graph)){
			int index = 0;
			double years[];
			double cost[];
		
			
			
			//data is an array. find how long the array is
			// and log it for debugging 
			while ( my_data.isNull(index) != true ){
				
				index++;
			}
			Log.v("LENGTH", String.valueOf(index) );
			
			
			
			years = new double[index];
			cost = new double[index];
			
			for( int i = 0 ; i < index ; i++) {
				
				try {
					years[i] = Double.valueOf(my_data.getJSONArray(i).getString(0));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					cost[i] = Double.valueOf(my_data.getJSONArray(i).getString(1));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			
			//debugging individual cost and 
			//year double arrays
			for( int k = 0; k < index ; k++){
			Log.v("YEARS ARRAY", String.valueOf(years[k]));
			
			}
			
			for( int l = 0; l < index ; l++){
			Log.v("COST ARRAY", String.valueOf(cost[l]));
			
			}
			
	
			
			Graph lineGraph = new Graph();
			Intent lineIntent = lineGraph.getIntent(this, cost, years, index );
			
			startActivity(lineIntent);
		}
		else{
			
		
		
			String zip = zipcode.getText().toString();
			new AccessAPI().execute(zip);
			/**
			KWHurl = "http://api.eia.gov/series/?api_key=3FBC89E53B8E3CB592BA2BB733C07050&series_id=ELEC.PRICE."
				+ stateFinal + "-RES.A";
			new AccessAPIKWH().execute(KWHurl);
		
			***/
		}
		
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
				stateFinal = tempState = object.getString("state");
				
				city.setText(tempCity);
				state.setText(tempState);
				
				KWHurl = "http://api.eia.gov/series/?api_key=3FBC89E53B8E3CB592BA2BB733C07050&series_id=ELEC.PRICE."
						+ stateFinal + "-RES.A";
				
				new AccessAPIKWH().execute(KWHurl);
				
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
       }		
    }

	private class AccessAPIKWH extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			
	
			String kwh_s = null;
			HttpResponse response = null;
			DefaultHttpClient myClient_1 = new DefaultHttpClient();
			HttpContext localContext_1 = new BasicHttpContext();
			HttpPost httppost = new HttpPost(url[0]);
		
		
			try {
				response = myClient_1.execute(httppost, localContext_1);
				InputStream jasonStream = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(jasonStream));
				StringBuilder builder = new StringBuilder();
				String in;
				
				while((in = reader.readLine()) != null){
					
					builder.append(in);
					
				}
				
				String jsonData = builder.toString();
				
				Log.v("JASON RESULT", jsonData);
				
				JSONObject jsonO = new JSONObject(jsonData);
				JSONArray series = jsonO.getJSONArray("series");
				JSONObject series_sub = series.getJSONObject(0);
				my_data = series_sub.getJSONArray("data");
	
				//log the value of data for debugging
				//data is an json array containing
				//kwh cost/year values
				String tempData = my_data.toString();
				Log.v("JASON DATA", tempData);
				
				
				//data is an array. find how long the array is
				// and log it for debugging 
			
				
			
				//get and log the first element of the array
				//for debugging
				JSONArray first = my_data.getJSONArray(0);
				String tempFirst = first.toString();
				Log.v("FIRST", tempFirst);
				
				// then get the first string 
				//and print it for debugging.
				kwh_s = first.getString(1);
				Log.v("KWH", kwh_s);

				
				
			
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return kwh_s;
		}
		
		
		protected void onPostExecute(String kwh_r) {
        	
			kwh.setText(kwh_r);
			
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