// Joseph Yuhas
// 9/15/2013
// Have fun!


private class ConnectToURL extends AsyncTask <String,Void,String> { // <> Specifies the parameters to the built in AsyncTask functions
	@Override
	// doInBackground is the entry point of the task unless you specify a constructor.
	// This is the multithreaded part of the task
	protected String doInBackground(String... params) { // Params is an array of strings. doInBackground is the entry point of the task unless you specify a constructor
		String url = params[0];
		String setString = null;
		try {
			// This is just some jsoup that will connect to a URL and grab the entire body section from a url
			Document doc = Jsoup.connect(url).get();
			Elements entireSet = doc.select("body");
			setString = entireSet.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		// This is returned automatically to onPostExecute 
		return setString;
	}
	// onPostExecute is executed after doInBackground is done.
	protected void onPostExecute(String setString) {
		 displayCard(setString);
	}
}

// The third function is something that executes while doInBackground is executing, like displaying a progress bar or whatever.