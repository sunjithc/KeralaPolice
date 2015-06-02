package com.example.keralapolice;

import java.util.ArrayList;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class home extends Activity {
	ArrayAdapter<String> aAdpt;
	private ActionBar actionBar;
	String[] menu;
	DrawerLayout dLayout;
	ListView dList;
	ArrayAdapter<String> adapter;
	private ActionBarDrawerToggle actbardrawertoggle = null;
	String searchmode;
	AlertDialog levelDialog;

	private TitleNavigationAdapter adapter1;
	// Title navigation Spinner data
	private ArrayList<SpinnerNavItem> navSpinner;

	// Title navigation Spinner data

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		dList = (ListView) findViewById(R.id.left_drawer);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(true);
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		menu = new String[] {"Home", "Name", "Pen No", "Police Station", "Blood Group" };

		actbardrawertoggle = new ActionBarDrawerToggle(this, dLayout,
				R.drawable.ic_drawer, 0, 0) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);

			}

		};
		dLayout.setDrawerListener(actbardrawertoggle);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menu);
		dList.setAdapter(adapter);
		dList.setSelector(android.R.color.holo_blue_dark);
		dList.setOnItemClickListener(new OnItemClickListener() {
			int position=0;
			@Override
			
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				displayView(position);
			}
		});
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

	}

	@SuppressWarnings("unused")
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
	//	position = 0;
		switch (position) {
		case 0:
            fragment = new DetailFragment();
            break;
		case 1:

			searchmode = "name";

			Toast.makeText(getApplicationContext(), searchmode,
					Toast.LENGTH_LONG).show();
			Intent i1 = new Intent(home.this, ListResult.class);
			i1.putExtra("key1", searchmode);
			
			i1.putExtra("id", 0);
			startActivity(i1);
			// Your code when first option seletced
			break;
		case 2:
			// Your code when 2nd option seletced
			searchmode = "penno";
			Toast.makeText(getApplicationContext(), searchmode,
					Toast.LENGTH_LONG).show();
			Intent i2 = new Intent(home.this, ListResult.class);
			i2.putExtra("key1", searchmode);
			i2.putExtra("id", 1);
			startActivity(i2);
			break;
		case 3:
			searchmode = "station";
			Toast.makeText(getApplicationContext(), searchmode,
					Toast.LENGTH_LONG).show();

			Intent i3 = new Intent(home.this, ListResult.class);
			i3.putExtra("key1", searchmode);
			i3.putExtra("id", 2);
			startActivity(i3);
			// Your code when 3rd option seletced
			break;
		case 4:
			searchmode = "bloodgroup";
			Toast.makeText(getApplicationContext(), searchmode,
					Toast.LENGTH_LONG).show();

			Intent i4 = new Intent(home.this, ListResult.class);
			i4.putExtra("key1", searchmode);
			i4.putExtra("id", 3);
			startActivity(i4);
			// Your code when 4th option seletced
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			dList.setItemChecked(position, true);
			dList.setSelection(position);
			// setTitle(navMenuTitles[position]);
			dLayout.closeDrawer(dList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menuhome, menu);

		// Associate searchable configuration with the SearchView
		// SearchManager searchManager = (SearchManager)
		// getSystemService(Context.SEARCH_SERVICE);
		// SearchView searchView = (SearchView)
		// menu.findItem(R.id.action_search).getActionView();
		// searchView.setSearchableInfo(searchManager
		// .getSearchableInfo(getComponentName()));
		//
		//
		//
		//
		//
		// searchView.setOnQueryTextListener(new OnQueryTextListener() {
		//
		// public boolean onQueryTextChange(String query) {
		// //
		// Intent searchIntent = new Intent(home.this, ListResult.class);
		// //send the keyword to the next screen
		// searchIntent.putExtra("keyword",query);
		// // qr=query;
		// //call the screen for listing
		// startActivity(searchIntent);
		// //
		//
		// return true;
		//
		// }
		//
		// @Override
		// public boolean onQueryTextSubmit(String query) {
		// //
		// return true;
		// }
		//
		// });
		//
		//
		//
		//
		//

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (actbardrawertoggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_s:
			Intent i1 = new Intent(home.this, ListResult.class);
			startActivity(i1);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showoptions() {

		// Strings to Show In Dialog with Radio Buttons
		final CharSequence[] items = { "Name", "Pen No ", "Police Station",
				"Blood Group" };

		// Creating and Building the Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
		builder.setTitle("Search By");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						switch (item) {
						case 0:

							searchmode = "name";

							Toast.makeText(getApplicationContext(), searchmode,
									Toast.LENGTH_LONG).show();
							Intent i1 = new Intent(home.this, ListResult.class);
							i1.putExtra("key1", searchmode);
							startActivity(i1);
							// Your code when first option seletced
							break;
						case 1:
							// Your code when 2nd option seletced
							searchmode = "penno";
							Toast.makeText(getApplicationContext(), searchmode,
									Toast.LENGTH_LONG).show();
							Intent i2 = new Intent(home.this, ListResult.class);
							i2.putExtra("key1", searchmode);
							startActivity(i2);
							break;
						case 2:
							searchmode = "station";
							Toast.makeText(getApplicationContext(), searchmode,
									Toast.LENGTH_LONG).show();

							Intent i3 = new Intent(home.this, ListResult.class);
							i3.putExtra("key1", searchmode);
							startActivity(i3);
							// Your code when 3rd option seletced
							break;
						case 3:
							searchmode = "bloodgroup";
							Toast.makeText(getApplicationContext(), searchmode,
									Toast.LENGTH_LONG).show();

							Intent i4 = new Intent(home.this, ListResult.class);
							i4.putExtra("key1", searchmode);
							startActivity(i4);
							// Your code when 4th option seletced
							break;

						}
						levelDialog.dismiss();
					}
				});
		levelDialog = builder.create();
		levelDialog.show();

	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = dLayout.isDrawerOpen(dList);
		menu.findItem(R.id.action_s).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	// @Override
	// public void setTitle(CharSequence title) {
	// mTitle = title;
	// getActionBar().setTitle(mTitle);
	// }

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actbardrawertoggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		actbardrawertoggle.onConfigurationChanged(newConfig);
	}

}
