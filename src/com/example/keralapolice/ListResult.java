package com.example.keralapolice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class ListResult extends ListActivity {
   private TitleNavigationAdapter adapter1;
   private ActionBar actionBar;
    private ArrayList<SpinnerNavItem> navSpinner;
	AlertDialog levelDialog; 
	private ProgressDialog pDialog;
	 
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> idiomsList;
 
    // url to get the idiom list
    private static String url_search;
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_IDIOMS = "idioms";
    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
  
    String idd;
    // products JSONArray
    JSONArray idioms = null;
    //search key value
    public String searchkey,searchmode;
    ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhome);
//    
        if(android.os.Build.VERSION.SDK_INT>9)
  			{
  				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
  				StrictMode.setThreadPolicy(policy);
  			}
  	   
//        // Get listview
           showoptions();
           lv = getListView();

          
     
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText()
                        .toString();
                String d = ((TextView) view.findViewById(R.id.d)).getText()
                        .toString();
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), d, Toast.LENGTH_LONG).show();
                Intent i=new Intent(ListResult.this,PersonalDetails.class);
                i.putExtra("uname",name);
                i.putExtra("uid",d);
                startActivity(i);
 
            }
        });
 
	
	
    }

    public void showoptions()
	   {
		

		// Strings to Show In Dialog with Radio Buttons
		final CharSequence[] items = {"Name","Pen No ","Police Station","Blood Group"};
		           
		                // Creating and Building the Dialog
		                AlertDialog.Builder builder = new AlertDialog.Builder(ListResult.this);
		                builder.setTitle("Search By");
		                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int item) {
		                  
		                   
		                    switch(item)
		                    {
		                        case 0:
		                        
		                        searchmode="name";
                                url_search= "http://192.168.245.1/policephp/listhome.php";
	
		                        // Your code when first option seletced
		                                 break;
		                        case 1:
		                                // Your code when 2nd  option seletced
		                       searchmode="penno";
		                       url_search= "http://192.168.245.1/policephp/listpen.php";
		                        	break;	
			
		                        case 2:
		                        	 searchmode="station";
		                        	 url_search= "http://192.168.245.1/policephp/liststation.php";
				                        // Your code when 3rd option seletced
		                                break;
		                        case 3:
		                        	 searchmode="bloodgroup";
		                        	 url_search= "http://192.168.236.1/policephp/listblood.php";
				                        // Your code when 4th  option seletced           
		                                break;
		                       
		                    }
		                    levelDialog.dismiss();   
		                    }
		                });
		                levelDialog = builder.create();
		                levelDialog.show();




		   
	   }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconified(false);
	
			
			
	        	searchView.setOnQueryTextListener(new OnQueryTextListener() {
	        		 
	                public boolean onQueryTextChange(String query) {
	                	searchkey=query;
	                	if(query.length()==0)
	                	{
	                		Toast.makeText(getApplicationContext(), "Enter values to search",Toast.LENGTH_LONG).show();
	                	}
	                	else if(query.length()>0)
	                	{
	                    new LoadIdioms().execute();
	                    lv = getListView();
	                	}
                   	     
	                    return true;
	     
                }

					@Override
					public boolean onQueryTextSubmit(String query) {
				
						return true;
					}
	        	
	            });
		
		

	
		

		
	 
	      
		return super.onCreateOptionsMenu(menu);
	     
	
	     
	 
	}

	public boolean onOptionsItemSelected(MenuItem item) {
      
        switch (item.getItemId()) {
        case R.id.action_searchmode:
         
          	showoptions();
            //send the keyword to the next screen
            
             
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	
    
    class LoadIdioms extends AsyncTask<String, String, String> {
    	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            idiomsList = new ArrayList<HashMap<String, String>>();
            pDialog = new ProgressDialog(ListResult.this);
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
            params.add(new BasicNameValuePair("keyword", searchkey));
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
                        idd=c.getString(TAG_ID);
                        
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_NAME, name);
                        map.put(TAG_ID, idd);
 
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
                            ListResult.this, idiomsList,
                            R.layout.list_item, new String[] { TAG_NAME,TAG_ID},
                            new int[] { R.id.name,R.id.d});
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