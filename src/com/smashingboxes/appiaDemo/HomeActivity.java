package com.smashingboxes.appiaDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	private static Object[] dataObjects = new Object[]{
		new AppInfo("01", "Good App", "$0.99", "App Co."), new AppInfo("02", "Fun App", "Free", "Computer Inc."), new AppInfo("03", "Hot App", "$1.99", "Mega Apps"),
		new AppInfo("04", "Pierre App", "$0.99", "App Co."), new AppInfo("05", "Rudy App", "$0.99", "Computer Inc."), new AppInfo("06", "Matheiu App", "$0.99", "Mega Apps"),
		new AppInfo("07", "Dog App", "$0.99", "App Co."), new AppInfo("08", "Cat App", "Free", "Computer Inc."), new AppInfo("09", "Shoe App", "$0.99", "Mega Apps"),
		new AppInfo("01", "Good App", "$1.99", "App Co."), new AppInfo("04", "Fun App", "$0.99", "Computer Inc."), new AppInfo("12", "Hot App", "$0.99", "Mega Apps"),
		new AppInfo("02", "Pierre App", "$0.99", "App Co."), new AppInfo("05", "Rudy App", "$0.99", "Computer Inc."), new AppInfo("15", "Matheiu App", "$0.99", "Mega Apps"),
		new AppInfo("03", "Rain App", "Free", "App Co."), new AppInfo("06", "Sun App", "$2.99", "Computer Inc."), new AppInfo("07", "Shoe App", "$0.99", "Mega Apps")
	};	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_view);
		
		Bundle extras = getIntent().getExtras();
		String email = extras.getString("email");
		
		Button buttonSearch = (Button) findViewById(R.id.button1);
		buttonSearch.setOnClickListener(bOnButtonClicked);
		
		TextView greeting = (TextView) findViewById(R.id.home_welcome_text);
		greeting.setText(getString(R.string.home_greeting) + " " + email + ".");
		
		GridView appsGrid = (GridView) findViewById(R.id.home_app_grid);
		appsGrid.setAdapter(new GridAdapter());
		
		LinearLayout featured = (LinearLayout) findViewById(R.id.featured);
		int numFeatured = 3;
		for(int i=0; i<numFeatured; i++) {
			LinearLayoutExtended lle = (LinearLayoutExtended) LayoutInflater.from(getBaseContext()).inflate(R.layout.icon_view, null);
			lle._appId = ((AppInfo) dataObjects[i])._id;
			lle._name = ((AppInfo) dataObjects[i])._name;
			lle._price = ((AppInfo) dataObjects[i])._price;
			lle._dev = ((AppInfo) dataObjects[i])._dev;
			
			TextView name = (TextView) lle.findViewById(R.id.icon_text_name);
			name.setText(((AppInfo) dataObjects[i])._name);
			
			TextView price = (TextView) lle.findViewById(R.id.icon_text_price);
			price.setText(((AppInfo) dataObjects[i])._price); 
			
			lle.setOnClickListener(lOnButtonClicked);
			
			featured.addView(lle);
		}
	}
	
	private OnClickListener lOnButtonClicked = new OnClickListener() {
		
		public void onClick(View v) {
			LinearLayoutExtended ll = (LinearLayoutExtended) v;
			//Toast.makeText(getApplicationContext(), "Click: " + ll._appId + " : " + ll._name + " : " + ll._price + " : " + ll._dev, Toast.LENGTH_SHORT).show();
			Intent appDetailsIntent = new Intent(getApplicationContext(), AppDetailsActivity.class);
			appDetailsIntent.putExtra("appId",ll._appId);
			appDetailsIntent.putExtra("name",ll._name);
			appDetailsIntent.putExtra("price",ll._price);
			appDetailsIntent.putExtra("dev", ll._dev);
			startActivity(appDetailsIntent);
		}
		
	};
	
	private OnClickListener bOnButtonClicked = new OnClickListener() {
		
		public void onClick(View v) {
			String query = null;
			EditText et = (EditText) findViewById(R.id.home_search_field);
			query = et.getText().toString();
			//Toast.makeText(getApplicationContext(), "Click: " + query, Toast.LENGTH_SHORT).show();
		
			Intent resultsIntent = new Intent(getApplicationContext(), FakeSearchResultsActivity.class);
			resultsIntent.putExtra("query", query);
			startActivity(resultsIntent);
		}
		
	};
	
	private class GridAdapter extends BaseAdapter {
		
		public GridAdapter() {
			super();
		}
		
		private OnClickListener mOnButtonClicked = new OnClickListener() {
			
			public void onClick(View v) {
				LinearLayoutExtended ll = (LinearLayoutExtended) v;
				//Toast.makeText(getApplicationContext(), "Click: " + ll._appId + " : " + ll._name + " : " + ll._price + " : " + ll._dev, Toast.LENGTH_SHORT).show();
				Intent appDetailsIntent = new Intent(getApplicationContext(), AppDetailsActivity.class);
				appDetailsIntent.putExtra("appId",ll._appId);
				appDetailsIntent.putExtra("name",ll._name);
				appDetailsIntent.putExtra("price",ll._price);
				appDetailsIntent.putExtra("dev", ll._dev);
				startActivity(appDetailsIntent);
			}
			
		};

		@Override
		public int getCount() {
			return dataObjects.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return Long.valueOf(((AppInfo) dataObjects[position])._id);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayoutExtended v = (LinearLayoutExtended) LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_view, null);
			
			v._appId = ((AppInfo) dataObjects[position])._id;
			v._name = ((AppInfo) dataObjects[position])._name;
			v._price = ((AppInfo) dataObjects[position])._price;
			v._dev = ((AppInfo) dataObjects[position])._dev;
			
			TextView name = (TextView) v.findViewById(R.id.icon_text_name);
			name.setText(((AppInfo) dataObjects[position])._name);
			
			TextView price = (TextView) v.findViewById(R.id.icon_text_price);
			price.setText(((AppInfo) dataObjects[position])._price); 
			
			v.setOnClickListener(mOnButtonClicked);
			
			return v;
		}
		
		
		
	}
	
}
