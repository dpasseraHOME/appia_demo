package com.smashingboxes.appiaDemo;

import java.util.LinkedList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Appia_demoActivity extends Activity {
	
	private AdvanceHandler _advanceHandler = new AdvanceHandler();
	private String _email = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();
        
        for(Account account : accounts) {
        	possibleEmails.add(account.name);
        }
        
        if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
        	_email = possibleEmails.get(0);
        	String[] parts = _email.split("@");
        	if(parts.length > 0 && parts[0] != null) {
        		_email = parts[0];
        	} else {
        		_email = "no email found";
        	}
        }
        
        _advanceHandler.sleep(1000);
    }
    
    class AdvanceHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		Appia_demoActivity.this.launchHomeActivity();
    	}
    	
    	public void sleep(long delayMillis) {
    		this.removeMessages(0);
    		sendMessageDelayed(obtainMessage(0), delayMillis);
    	}
    	
    }

	public void launchHomeActivity() {
		Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
		homeIntent.putExtra("email", _email);
		startActivity(homeIntent);
	}
    
}