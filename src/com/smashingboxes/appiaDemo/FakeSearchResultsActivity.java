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
import android.widget.TextView;

public class FakeSearchResultsActivity extends Activity {
	
	private String _query = null;
	private Object[] dataObjects;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_view);
		
		Bundle extras = getIntent().getExtras();
		_query = extras.getString("query");
		
		TextView tv = (TextView) findViewById(R.id.results_header);
		tv.setText("Results for '" + _query + "'");
		
		Button buttonSearch = (Button) findViewById(R.id.details_search_button);
		buttonSearch.setOnClickListener(bOnButtonClicked);
		
		dataObjects = new Object[]{
				new AppInfo("01", "Good " + _query, "$0.99", "App Co."), new AppInfo("02", "Fun " + _query, "Free", "Computer Inc."), new AppInfo("03", "Hot " + _query, "$1.99", "Mega Apps"),
				new AppInfo("04", "Pierre " + _query, "$0.99", "App Co."), new AppInfo("05", "Rudy " + _query, "$0.99", "Computer Inc."), new AppInfo("06", "Matheiu " + _query, "$0.99", "Mega Apps"),
				new AppInfo("01", "Good " + _query, "$0.99", "App Co."), new AppInfo("02", "Fun " + _query, "Free", "Computer Inc."), new AppInfo("03", "Hot " + _query, "$1.99", "Mega Apps"),
				new AppInfo("04", "Pierre " + _query, "$0.99", "App Co."), new AppInfo("05", "Rudy " + _query, "$0.99", "Computer Inc."), new AppInfo("06", "Matheiu " + _query, "$0.99", "Mega Apps"),
				new AppInfo("01", "Good " + _query, "$0.99", "App Co."), new AppInfo("02", "Fun " + _query, "Free", "Computer Inc."), new AppInfo("03", "Hot " + _query, "$1.99", "Mega Apps"),
				new AppInfo("04", "Pierre " + _query, "$0.99", "App Co."), new AppInfo("05", "Rudy " + _query, "$0.99", "Computer Inc."), new AppInfo("06", "Matheiu " + _query, "$0.99", "Mega Apps")
		};
		
		GridView appsGrid = (GridView) findViewById(R.id.home_app_grid);
		appsGrid.setAdapter(new GridAdapter());
	}
	
	private OnClickListener bOnButtonClicked = new OnClickListener() {
		
		public void onClick(View v) {
			String query = null;
			EditText et = (EditText) findViewById(R.id.editText1);
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
