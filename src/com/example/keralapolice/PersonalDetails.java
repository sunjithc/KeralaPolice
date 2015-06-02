package com.example.keralapolice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PersonalDetails extends Activity {
	ExpandableListView expandableListView;
	ExpandableListAdapter expandableListAdapter;
	List expandableListTitle;
	HashMap expandableListDetail;
	final Context context = this;
	static String penno, name, fathersname, sex, relegion, caste, dob,
			bloodgroup, address;
	static String policestation, rank, eis, pancard;
	static String mobile, email;
	static String arrayString1;
	static String arrayString;
	static String arrayString2;
	static String arrayString3;
	static String arrayString4;
	static String arrayString5;
	static String arrayString6;
	static String arrayString7;
	static String arrayString8;
	static String arrayString9;
	static String arrayString10;
	static String arrayString11;
	static String arrayString12;
	static String arrayString13;
	static String arrayString14;
	HttpPost httppost;
	static HashMap<String, String> list1;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;
	String id, uname;

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

		Intent intent = getIntent();

		uname = intent.getStringExtra("uname");
		id = intent.getStringExtra("uid");
		Toast.makeText(getApplicationContext(), uname, Toast.LENGTH_LONG)
				.show();
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
		viewlist();

		expandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		expandableListDetail = getData();
		expandableListTitle = new ArrayList(expandableListDetail.keySet());
		expandableListAdapter = new com.example.keralapolice.ExpandableListAdapter(
				this, expandableListTitle, expandableListDetail);
		expandableListView.setAdapter(expandableListAdapter);
		expandableListView.setDividerHeight(2);
		// expandableListView.setGroupIndicator(null);
		expandableListView.setClickable(true);
		expandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {

						Toast.makeText(
								getApplicationContext(),
								expandableListTitle.get(groupPosition)
										+ " List Expanded.", Toast.LENGTH_SHORT)
								.show();
					}

				});

		expandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					@Override
					public void onGroupCollapse(int groupPosition) {
						Toast.makeText(
								getApplicationContext(),
								expandableListTitle.get(groupPosition)
										+ " List Collapsed.",
								Toast.LENGTH_SHORT).show();

					}
				});

		expandableListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						expandableListTitle.get(groupPosition)
								+ " -> "
								+ expandableListDetail.get(expandableListTitle
										.get(childPosition)),
						Toast.LENGTH_SHORT).show();

				return false;

			}
		});
	}

	public void viewlist() {
		InputStream is = null;
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://192.168.245.1/policephp/details.php");
			nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id", id));
			nameValuePairs.add(new BasicNameValuePair("name", uname));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			// Log.e("log_tag", "Error in http connection"+e.toString());

			Toast.makeText(getApplicationContext(),
					"Error in http connection" + e.toString(),
					Toast.LENGTH_LONG).show();
		}

		// convert response to string
		String result = null;
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));// ,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";

			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error converting result " + e.toString(),
					Toast.LENGTH_LONG).show();// ("log_tag", );
		}

		try {
			JSONArray jArray = new JSONArray(result);
			JSONObject json_data = null;
			final ArrayList<String> list = new ArrayList<String>();
			arrayString = null;
			arrayString1 = null;
			arrayString2 = null;
			arrayString3 = null;
			arrayString4 = null;
			arrayString5 = null;
			arrayString6 = null;
			arrayString7 = null;
			arrayString8 = null;
			arrayString9 = null;
			arrayString10 = null;
			arrayString11 = null;
			arrayString12 = null;
			arrayString13 = null;
			arrayString14 = null;
			list1 = new HashMap<String, String>();

			int idd;

			for (int i = 0; i < jArray.length(); i++) {

				json_data = jArray.getJSONObject(i);

				penno = json_data.getString("penno");
				name = json_data.getString("name");
				fathersname = json_data.getString("fathersname");
				sex = json_data.getString("sex");
				relegion = json_data.getString("religion");
				caste = json_data.getString("cast");
				dob = json_data.getString("dob");
				bloodgroup = json_data.getString("bloodgroup");
				address = json_data.getString("address");
				policestation = json_data.getString("station");
				rank = json_data.getString("rank");
				eis = json_data.getString("eis");
				pancard = json_data.getString("pancard");
				mobile = json_data.getString("mob");
				email = json_data.getString("email");

				arrayString = "Pen No:" + penno;
				arrayString1 = "Name:" + name;
				arrayString2 = "Father Name:" + fathersname;
				arrayString3 = "Sex:" + sex;
				arrayString4 = "Religion:" + relegion;
				arrayString5 = "Cast:" + caste;
				arrayString6 = "DOB:" + dob;
				arrayString7 = "Blood Group:" + bloodgroup;
				arrayString8 = "Address:" + address;
				arrayString9 = "Police Station:" + policestation;
				arrayString10 = "Rank:" + rank;
				arrayString11 = "Entry Into Service:" + eis;
				arrayString12 = "Pan Card:" + pancard;
				arrayString13 = "Mobile:" + mobile;
				arrayString14 = "Email:" + email;

				Toast.makeText(getApplicationContext(), arrayString,
						Toast.LENGTH_LONG).show();
				// Toast.makeText(getApplicationContext(), password,
				// Toast.LENGTH_LONG).show();
				// list1.put("password",password);

				Toast.makeText(getApplicationContext(), "done",
						Toast.LENGTH_SHORT).show();

			}

			// ListView listView = (ListView) findViewById(R.id.listView1);
			// final ArrayAdapter<String> adapter = new
			// ArrayAdapter<String>(UserPage.this,
			// android.R.layout.simple_list_item_1, list);
			// listView.setAdapter(adapter);
			//
		} catch (JSONException e1) {
			// content.setText("No Appointment Found");
			Toast.makeText(getApplicationContext(),
					"No Appointment " + e1.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}

	public void showAlert() {
		PersonalDetails.this.runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PersonalDetails.this);
				builder.setTitle("Login Error.");
				builder.setMessage("User not Found.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	public static HashMap getData() {
		HashMap expandableListDetail = new HashMap();

		List p = new ArrayList();
		p.add(arrayString);
		p.add(arrayString1);
		p.add(arrayString2);
		p.add(arrayString3);
		p.add(arrayString4);
		p.add(arrayString5);
		p.add(arrayString6);
		p.add(arrayString7);
		p.add(arrayString8);

		List d = new ArrayList();

		d.add(arrayString9);
		d.add(arrayString10);
		d.add(arrayString11);
		d.add(arrayString12);

		List c = new ArrayList();

		c.add(arrayString13);
		c.add(arrayString14);

		expandableListDetail.put("PERSONAL DETAILS", p);
		expandableListDetail.put("OFFICIAL DETAILS", d);
		expandableListDetail.put("CONTACT DETAILS", c);

		return expandableListDetail;
	}

}
