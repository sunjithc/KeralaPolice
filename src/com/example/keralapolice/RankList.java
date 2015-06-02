package com.example.keralapolice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.keralapolice.ListResult.LoadIdioms;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RankList extends ListActivity {


	private ProgressDialog pDialog;
	 
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> idiomsList;
 
    // url to get the idiom list
    private static String url_search = "http://192.168.433.251/policephp/ranklist.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_IDIOMS = "idioms";
    private static final String TAG_NAME = "name";
  
 
    // products JSONArray
    JSONArray idioms = null;
    //search key value
    public String district,rank;
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhome);
    
        Intent myIntent = getIntent(); 
        // gets the arguments from previously created intent
        district = myIntent.getStringExtra("key1");
        rank = myIntent.getStringExtra("key2");
        if(android.os.Build.VERSION.SDK_INT>9)
  			{
  				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
  				StrictMode.setThreadPolicy(policy);
  			}
  	     
 
        // Hashmap for ListView
        idiomsList = new ArrayList<HashMap<String, String>>();
 
        // Loading idioms in Background Thread
        new LoadIdioms().execute();
 
        // Get listview
        ListView lv = getListView();
 
        // on seleting single idioms
        // to do something 
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText()
                        .toString();
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                Intent i=new Intent(RankList.this,PersonalDetails.class);
                i.putExtra("uname",name);
	        
                startActivity(i);
 
            }
        });
 
	
	
    }
    
    
    
    class LoadIdioms extends AsyncTask<String, String, String> {
    	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RankList.this);
            pDialog.setMessage("Loading IDIOMS. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting Idioms from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //value captured from previous intent
            params.add(new BasicNameValuePair("district",district));
            params.add(new BasicNameValuePair("rank",rank));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_search, "GET", params);
 
            // Check your log cat for JSON response
            Log.d("Search idioms: ", json.toString());
           // Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_LONG).show();
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    idioms = json.getJSONArray(TAG_IDIOMS);
 
                    // looping through All Products
                    for (int i = 0; i < idioms.length(); i++) {
                        JSONObject c = idioms.getJSONObject(i);
 
                        // Storing each json item in variable
                        String name = c.getString(TAG_NAME);
                       
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_NAME, name);
                       
 
                        // adding HashList to ArrayList
                        idiomsList.add(map);
                    }
                } else {
                    // no idioms found
                    //do something
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            //return "success";
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting the related idioms
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            RankList.this, idiomsList,
                            R.layout.list_item, new String[] { TAG_NAME},
                            new int[] { R.id.name});
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    
    
}
    @Override
    protected void onStop() {
        super.onStop();

        if(pDialog!= null)
            pDialog.dismiss();
    }

	
	
}
