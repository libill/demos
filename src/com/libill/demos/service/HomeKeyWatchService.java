package com.libill.demos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.libill.demos.service.HomeWatch.OnHomePressedListener;

public class HomeKeyWatchService extends Service{
	private String TAG = "HomeKeyWatchService";
	
	HomeWatch homeWatch;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		
		homeWatch =new HomeWatch(HomeKeyWatchService.this);
		homeWatch.setOnHomePressedListener(new OnHomePressedListener() {
			
			@Override
			public void onHomePressed() {
				
				Log.i(TAG, "onHomePressed");
				
			}
			
			@Override
			public void onHomeLongPressed() {
				Log.i(TAG, "onHomeLongPressed");
			}
		});
		homeWatch.startWatch();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		homeWatch.stopWatch();
	}

}
