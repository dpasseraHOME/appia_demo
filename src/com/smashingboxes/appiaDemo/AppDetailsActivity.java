package com.smashingboxes.appiaDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AppDetailsActivity extends Activity {

	private String _appId;
	private String _name;
	private String _price;
	private String _dev;
	
	final Context context = this;
	
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
		
		Button buttonSearch = (Button) findViewById(R.id.details_search_button);
		buttonSearch.setOnClickListener(bOnButtonClicked);
		
		populateLayout();
	}
	
	private void populateLayout() {
		TextView title = (TextView) findViewById(R.id.details_text_name);
		title.setText(_name);
		
		TextView dev = (TextView) findViewById(R.id.details_text_dev);
		dev.setText(_dev);
		
		Button button = (Button) findViewById(R.id.details_purchase_button);
		button.setText("Install " + _price);
		button.setOnClickListener(mOnButtonClicked);
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
	
	private OnClickListener mOnButtonClicked = new OnClickListener() {
		
		public void onClick(View v) {
			ContextThemeWrapper ctw = new ContextThemeWrapper(context, R.style.alert_style);
			AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
			builder.setTitle("Purchase App?")
         	    .setMessage( "Are you sure you want to buy this app? Super sure? Really?" )
				.setCancelable(false)
				.setPositiveButton("Buy App", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
			AlertDialog alert = builder.create();
			alert.show();
		}
		
	};
	
}
