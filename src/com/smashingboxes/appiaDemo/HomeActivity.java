package com.smashingboxes.appiaDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_view);
		
		Bundle extras = getIntent().getExtras();
		String email = extras.getString("email");
		
		TextView greeting = (TextView) findViewById(R.id.home_welcome_text);
		greeting.setText(getString(R.string.home_greeting) + " " + email + ".");
	}
	
	private static Object[] dataObjects = new Object[]{
		new AppInfo("01", "Good App", "$0.99"), new AppInfo("02", "Fun App", "Free"), new AppInfo("03", "Hot App", "$1.99"),
		new AppInfo("04", "Pierre App", "$0.99"), new AppInfo("05", "Rudy App", "$0.99"), new AppInfo("06", "Matheiu App", "$0.99"),
		new AppInfo("07", "Dog App", "$0.99"), new AppInfo("08", "Cat App", "Free"), new AppInfo("09", "Shoe App", "$0.99"),
		new AppInfo("10", "Good App", "$1.99"), new AppInfo("11", "Fun App", "$0.99"), new AppInfo("12", "Hot App", "$0.99"),
		new AppInfo("13", "Pierre App", "$0.99"), new AppInfo("14", "Rudy App", "$0.99"), new AppInfo("15", "Matheiu App", "$0.99"),
		new AppInfo("16", "Rain App", "Free"), new AppInfo("17", "Sun App", "$2.99"), new AppInfo("Cloud", "Shoe App", "$0.99")
	};
	
	private class GridAdapter extends BaseAdapter {
		
		public GridAdapter() {
			super();
		}

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
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_view, null);
			
			TextView name = (TextView) v.findViewById(R.id.icon_text_name);
			name.setText(((AppInfo) dataObjects[position])._name);
			
			TextView price = (TextView) v.findViewById(R.id.icon_text_price);
			price.setText(((AppInfo) dataObjects[position])._price); 
			
			return null;
		}
		
		
		
	}
	
}
