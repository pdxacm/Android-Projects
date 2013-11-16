/** A file which gives the basic functionality for INSERT, SELECT and login checks from a MySQL DB using PHP   
 * scripts.
 * @author Sean Walsh
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;

public class DBHandling {
	/**
	 * Posts DB information given from an activity and stores it a database
	 * specified by the PHP file. The data is sent via a hashtable in the 
	 * form of nameValuePairs, which will then be interpreted by the PHP
	 * file as the names given (first argument in the name/value pair) as
	 * a $_POST value in the PHP file.
	 * @param value Some value, can be any type, that will be sent to the PHP post script. Multiple args are okay as well.
	 */
	public static void postFunction(String value) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		// For Android, 10.0.2.2 is Localhost (IPv6 addressing)
		HttpPost httppost = new HttpPost("http://10.0.2.2/somePostScript.php");

		try {
			// These hashes will get sent to the PHP script as $_POST values.
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("value",
					value));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			Log.e("log_tag", e.toString());
		} catch (IOException e) {
			Log.e("log_tag", e.toString());
		}
	}

	/**
	 * 		Gets data from DB using PHP script.
	 */
	public static String[] getBasicResults() {
		StringBuilder result = new StringBuilder("");
		// Set the array size to the number of columns returned from the DB table.
		String[] results = new String[1];
		InputStream isr = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(
					"http://10.0.2.2/someGetScript.php");
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			isr = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					isr, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder("");
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			isr.close();
			result.append(sb.toString());
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		try {
			JSONArray jArray = new JSONArray(result.toString());

			/* json.getString() can be represented as json.getInt
			 * and other types too.
			 */
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json = jArray.getJSONObject(i);
				results[0] = json.getString("columnName");
			}
			// For testing purposes you can print the output to LogCat.
			System.out.println(results[0]);
		} catch (Exception e) {
			Log.e("log_tag", "Error Parsing Data" + e.toString());
		}
		return results;
	}

	/**
	  * A simple login function for username/password validation using a PHP script.
	  * @param username The username being sent to the PHP script to validate.
	  * @param password The password being sent and checked against the returned username from the DB query.
      */
	public static boolean checkLoginCredentials(String username, String password) {
		StringBuilder result = new StringBuilder("");
		String[] results = new String[2];
		InputStream isr = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://10.0.2.2/checkLogin.php");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", username));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			isr = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					isr, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder("");
			String line = reader.readLine();
			sb.append(line);
			isr.close();
			result.append(sb.toString());
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		try {
			JSONArray jArray = new JSONArray(result.toString());
			JSONObject json = jArray.getJSONObject(0);
			results[0] = json.getString("username");
			results[1] = json.getString("password");
		} catch (Exception e) {
			Log.e("log_tag", "JSON Error Parsing Data" + e.toString());
		}
		if (username.equals(results[0]) && password.equals(results[1]))
			return true;
		else
			return false;
	}
}