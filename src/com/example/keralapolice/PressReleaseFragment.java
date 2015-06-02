package com.example.keralapolice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PressReleaseFragment extends Fragment {
	 View view;
	 
	    HttpPost httppost;
	    StringBuffer buffer;
	    int idd;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
	    TextView ph;
	    Button bt;
	    ProgressDialog dialog = null;
	    String img,nws;
	    TextView nw;
	    ListView listView;
	    
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        view = inflater.inflate(R.layout.activity_press, container, false);
        nw=(TextView)view.findViewById(R.id.newss);
    	listView = (ListView)view.findViewById(R.id.listnews);
        viewpress();
        
        
        
        return view;
    }
    public void viewpress(){
		InputStream is = null;
		try{
				
				httpclient=new DefaultHttpClient();
				httppost= new HttpPost("http://192.168.236.1/policephp/press.php");
	            nameValuePairs = new ArrayList<NameValuePair>();
	           
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	            
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	        }catch(Exception e){
	           Log.e("log_tag", "Error in http connection"+e.toString());
	        	
	        	 Toast.makeText(getActivity(),"Error in http connection"+e.toString(), Toast.LENGTH_LONG).show();
	        }
	     
	        //convert response to string
	        String result = null;
		try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is));//,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            sb.append(reader.readLine() + "\n");
	            String line="0";
	          
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	             
	            is.close();
	            result=sb.toString();	           
	   }catch(Exception e){
	            Toast.makeText(getActivity(), "Error converting result "+e.toString(), Toast.LENGTH_LONG).show();//("log_tag", );
	   }
	 
	 try{
	        JSONArray jArray = new JSONArray(result);
	        JSONObject json_data=null;
	        final ArrayList<String> list = new ArrayList<String>();
	      
	        String arrayString = null;
	        int idd;
	      
	        for(int i=0;i<jArray.length();i++){
	        	
	            json_data = jArray.getJSONObject(i);
	           
	            idd=json_data.getInt("id");
                
                
	            nws=json_data.getString("pressnews");
	           
	      	
	            arrayString=nws;	
	           
            	list.add(arrayString);
            	
	        	nw.setText("");
	        	
	        				
	        				
          Toast. makeText(getActivity(), "done",Toast. LENGTH_SHORT). show();
            	
			       	
	            }
	                
	    
	        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, list);
	        listView.setAdapter(adapter);
	       
	   }catch(JSONException e1){
		   		nw.setText("No Appointment Found");
	            //Toast.makeText(getApplicationContext(),"No Appointment "+ e1.getMessage(), Toast.LENGTH_LONG).show();
	   }catch (ParseException e1){
	            e1.printStackTrace();
	            Toast.makeText(getActivity(),"Error in fetching"+e1.getMessage(), Toast.LENGTH_LONG).show();
	   }
	}
}

