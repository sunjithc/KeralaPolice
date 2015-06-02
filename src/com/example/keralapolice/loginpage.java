package com.example.keralapolice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginpage extends Activity {
	EditText username,password;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 username = (EditText) findViewById(R. id. uname);
		 password = (EditText) findViewById(R. id. pass);
		login = (Button) findViewById(R.id.blogin);
		 login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				login();
			}
		});}
		 
		 public void login(){
		 if(username. getText(). toString(). equals("a") &&
		 password. getText(). toString(). equals("b")){
			 
		 Toast. makeText(getApplicationContext(), "Success",Toast. LENGTH_SHORT). show();
         Intent i=new Intent(loginpage.this,home.class);
         startActivity(i);
		 }
		 else{
			 Toast. makeText(getApplicationContext(), "Wrong Details",Toast. LENGTH_SHORT). show();
		 }
		 }
		 
	
	
	
	}
