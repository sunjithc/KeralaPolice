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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EmergenyFragment extends Fragment {
	private String name,place,phone,dt;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
	View view;
	String message = null;
	EditText alert;
	Button bt;
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        view = inflater.inflate(R.layout.activity_emergency, container, false);
        alert = (EditText)view.findViewById(R.id.al);
        bt=(Button)view.findViewById(R.id.bal);
      
        if(android.os.Build.VERSION.SDK_INT>9)
		{
			StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
        bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				loadnumbers();
			}
		});
     


	return view;
} 
	public void loadnumbers()
	{
		InputStream is = null;
		try{
				
				httpclient=new DefaultHttpClient();
				httppost= new HttpPost("http://192.168.245.1/policephp/phone.php");
	            nameValuePairs = new ArrayList<NameValuePair>(2);
	          
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	            
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	        }catch(Exception e){
	          //  Log.e("log_tag", "Error in http connection"+e.toString());
	        	
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
	      
	     
	        int idd;
	      
	        for(int i=0;i<jArray.length();i++){
	        	
	            json_data = jArray.getJSONObject(i);
	           
	            idd=json_data.getInt("id");
                
                
	            name=json_data.getString("name");
	            phone=json_data.getString("phone");
	        
	        
	      	
	           message="Hello"+""+name+"."+alert.getText().toString();	
	           
	           Toast. makeText(getActivity(),message,Toast. LENGTH_LONG). show();
	        				
	           sendMessage();			
               Toast. makeText(getActivity(), "done",Toast. LENGTH_SHORT). show();
            	
			       	
	            }
	                
	    	
	       
	   }catch(JSONException e1){
		   		//content.setText("No Appointment Found");
	            Toast.makeText(getActivity(),"No Appointment "+ e1.getMessage(), Toast.LENGTH_LONG).show();
	   }catch (ParseException e1){
	            e1.printStackTrace();
	            Toast.makeText(getActivity(),"Error in fetching"+e1.getMessage(), Toast.LENGTH_LONG).show();
	   }
	}

  
public void showAlert(){
    getActivity().runOnUiThread(new Runnable() {
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Login Error.");
            builder.setMessage("User not Found.")  
                   .setCancelable(false)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                       }
                   });                     
            AlertDialog alert = builder.create();
            alert.show();               
        }
    });
}
public void sendMessage() {
	// TODO Auto-generated method stub
	
 

 String phoneNo= phone ;
 String Message=message;
 try {
 SmsManager smsManager = SmsManager. getDefault();
 smsManager. sendTextMessage(phoneNo, null, Message, null, null);
 Toast. makeText(getActivity(), "SMS sent.",Toast. LENGTH_LONG). show();
 } catch (Exception e) {
 Toast. makeText(getActivity(),"SMS faild, please try again.", Toast. LENGTH_LONG). show();
 e. printStackTrace();
 }
 }
	}
       
	
