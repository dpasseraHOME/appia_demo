package com.smashingboxes.appiaDemo;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_view);
		
		Bundle extras = getIntent().getExtras();
		String email = extras.getString("email");
	}
	
}
