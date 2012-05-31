package com.smashingboxes.appiaDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AppDetailsActivity extends Activity {

	private String _appId;
	private String _name;
	private String _price;
	private String _dev;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_details_view_rel);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			_appId = extras.getString("appId");
			_name = extras.getString("name");
			_price = extras.getString("price");
			_dev = extras.getString("dev");
			//Toast.makeText(getApplicationContext(), _appId + " : " + _name + " : " + _price + " : " + _dev, Toast.LENGTH_SHORT).show();
		}
		
		populateLayout();
	}
	
	private void populateLayout() {
		TextView title = (TextView) findViewById(R.id.details_text_name);
		title.setText(_name);
		
		TextView dev = (TextView) findViewById(R.id.details_text_dev);
		dev.setText(_dev);
	}
	
}
