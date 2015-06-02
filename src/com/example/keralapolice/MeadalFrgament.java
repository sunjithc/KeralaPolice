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
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MeadalFrgament extends Fragment {
	 HttpPost httppost;
	    StringBuffer buffer;
	    int idd;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
	    TextView ph;
	    Button ne,pre;
	    ProgressDialog dialog = null;
	    String img,details;
		ImageView im;
		TextView nw;
		EditText detail;
		int position;
		 Canvas canvas;
		int id=1;
		String customid=Integer.toString(id);
		byte[] imageAsBytes;
		View view;
		  @Override
		    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
		        view = inflater.inflate(R.layout.activity_wanted, container, false);
		        detail=(EditText)view.findViewById(R.id.des);
		        ne=(Button)view.findViewById(R.id.n);
		        
		        ne.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
					next();	
					}
				});
		        pre=(Button)view.findViewById(R.id.p);
	            pre.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
					previous();	
					}
				});

		        viewnews();
		     
		        return view;
		    }
		  
		  
		  public void viewnews()
		    {
		    	
		    	
		    	InputStream is = null;
		    	try{
		    			
		    			httpclient=new DefaultHttpClient();
		    			httppost= new HttpPost("http://192.168.43.251/policephp/medal.php");
		                nameValuePairs = new ArrayList<NameValuePair>(1);
		                nameValuePairs.add(new BasicNameValuePair("id",customid));
		         
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	
		                ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                final String resp = httpclient.execute(httppost, responseHandler);
		                System.out.println("Response : " + response); 
		                //ph.setText(resp);
		                HttpResponse response = httpclient.execute(httppost);
		                HttpEntity entity = response.getEntity();
		                is = entity.getContent();
		            }catch(Exception e){
		              //  Log.e("log_tag", "Error in http connection"+e.toString());
		            	 // ph.setText(e.toString());
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
		          
		          
		            for(int i=0;i<jArray.length();i++){
		            	
		                json_data = jArray.getJSONObject(i);
		              
		                idd=json_data.getInt("id");
		               
		                img=json_data.getString("img");
		               details=json_data.getString("details");
		               
		                      
		             
		               
		            
		                imageAsBytes = Base64.decode(img.getBytes(), i);
		              
		                
		                im=(ImageView)view.findViewById(R.id.imageView1);
		                
		                im.setImageBitmap(
		                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
		                Bitmap bitmap =BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
		               
		               // im.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap, pixels));
		                
		                detail.setText(details);
		             Toast. makeText(getActivity(), "done",Toast. LENGTH_SHORT). show(); 

		         	 	
		            }  
		          
		            
		    //
		           // de.setText(des);
		          
		            
		           
		       }catch(JSONException e1){
		    	   		//content.setText("No Appointment Found");
		                //Toast.makeText(getApplicationContext(),"No Appointment "+ e1.getMessage(), Toast.LENGTH_LONG).show();
		       }catch (ParseException e1){
		                e1.printStackTrace();
		                Toast.makeText(getActivity(),"Error in fetching"+e1.getMessage(), Toast.LENGTH_LONG).show();
		       
		            	
		       
		    	}  	
		    	
		    	
		    }
		    
		    public void next()
		    {
		    	
		    	
		    	InputStream is = null;
		    	try{
		    		int nid=idd+1;
		    		String nextid=Integer.toString(nid);
		    			httpclient=new DefaultHttpClient();
		    			httppost= new HttpPost("http://192.168.43.251/policephp/medal.php");
		                nameValuePairs = new ArrayList<NameValuePair>(1);
		                nameValuePairs.add(new BasicNameValuePair("id",nextid));
		         
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	
		                ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                final String resp = httpclient.execute(httppost, responseHandler);
		                System.out.println("Response : " + response); 
		                //ph.setText(resp);
		                HttpResponse response = httpclient.execute(httppost);
		                HttpEntity entity = response.getEntity();
		                is = entity.getContent();
		            }catch(Exception e){
		              //  Log.e("log_tag", "Error in http connection"+e.toString());
		            	 // ph.setText(e.toString());
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
		          
		          
		            for(int i=0;i<jArray.length();i++){
		            	
		                json_data = jArray.getJSONObject(i);
		              
		               idd=json_data.getInt("id");
		               
		                img=json_data.getString("img");
		                details=json_data.getString("details");
		               
		                      
		             
		               
		            
		                imageAsBytes = Base64.decode(img.getBytes(), i);
		              
		                
		                im=(ImageView)view.findViewById(R.id.imageView1);
		                
		                im.setImageBitmap(
		                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
		            //    Bitmap bitmap =BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
		                detail.setText(details);
		                
		             Toast. makeText(getActivity(), "done",Toast. LENGTH_SHORT). show(); 

		         	 	
		            }  
		          
		            
		    //
		           // de.setText(des);
		          
		            
		           
		       }catch(JSONException e1){
		    	   		//content.setText("No Appointment Found");
		                //Toast.makeText(getApplicationContext(),"No Appointment "+ e1.getMessage(), Toast.LENGTH_LONG).show();
		       }catch (ParseException e1){
		                e1.printStackTrace();
		                Toast.makeText(getActivity(),"Error in fetching"+e1.getMessage(), Toast.LENGTH_LONG).show();
		       
		            	
		       
		    	}  	
		    	
		    	
		    } 
		    public void previous()
		    {
		    	
		    	
		    	InputStream is = null;
		    	try{
		    		int nid=idd-1;
		    		String nextid=Integer.toString(nid);
		    			httpclient=new DefaultHttpClient();
		    			httppost= new HttpPost("http://192.168.43.251/policephp/medal.php");
		                nameValuePairs = new ArrayList<NameValuePair>(1);
		                nameValuePairs.add(new BasicNameValuePair("id",nextid));
		         
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	
		                ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                final String resp = httpclient.execute(httppost, responseHandler);
		                System.out.println("Response : " + response); 
		                //ph.setText(resp);
		                HttpResponse response = httpclient.execute(httppost);
		                HttpEntity entity = response.getEntity();
		                is = entity.getContent();
		            }catch(Exception e){
		              //  Log.e("log_tag", "Error in http connection"+e.toString());
		            	  //ph.setText(e.toString());
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
		          
		          
		            for(int i=0;i<jArray.length();i++){
		            	
		                json_data = jArray.getJSONObject(i);
		              
		               idd=json_data.getInt("id");
		               
		                img=json_data.getString("img");
		                details=json_data.getString("details");
		               
		                      
		             
		               
		            
		                imageAsBytes = Base64.decode(img.getBytes(), i);
		              
		                
		                im=(ImageView)view.findViewById(R.id.imageView1);
		                
		                im.setImageBitmap(
		                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
		            //    Bitmap bitmap =BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
		               
		                detail.setText(details);
		             Toast. makeText(getActivity(), "done",Toast. LENGTH_SHORT). show(); 

		         	 	
		            }  
		          
		            
		    //
		           // de.setText(des);
		          
		            
		           
		       }catch(JSONException e1){
		    	   		//content.setText("No Appointment Found");
		                //Toast.makeText(getApplicationContext(),"No Appointment "+ e1.getMessage(), Toast.LENGTH_LONG).show();
		       }catch (ParseException e1){
		                e1.printStackTrace();
		                Toast.makeText(getActivity(),"Error in fetching"+e1.getMessage(), Toast.LENGTH_LONG).show();
		       
		            	
		       
		    	}  	
		    	
		    	
		    } 
		  
		  

		        
		        
}
