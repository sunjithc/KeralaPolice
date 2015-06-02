package com.example.keralapolice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txt;
	Animation fade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt = (TextView) findViewById(R.id.textView1);

		fade = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fadein);

		txt.startAnimation(fade);
		Thread welcomeThread = new Thread() {

			@Override
			public void run() {
				try {
					super.run();
					sleep(1000); // Delay of 10 seconds
				} catch (Exception e) {

				} finally {

					Intent i = new Intent(MainActivity.this, home.class);
					startActivity(i);
					finish();
				}
			}
		};
		welcomeThread.start();

	}

}
