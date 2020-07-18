package com.libill.demos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.libill.demos.service.ScreenLockWatch.OnScreenLockListener;

public class ScreenLockWatchService extends Service {
	private String TAG = "ScreenLockWatchService";

	ScreenLockWatch screenLockWatch;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		screenLockWatch = new ScreenLockWatch(ScreenLockWatchService.this);

		screenLockWatch.setOnScreenLockListener(new OnScreenLockListener() {

			@Override
			public void onScreenPresent() {
				Log.i(TAG, "onScreenPresent");
			}

			@Override
			public void onScreenOn() {
				Log.i(TAG, "onScreenOn");
			}

			@Override
			public void onScreenOff() {
				Log.i(TAG, "onScreenOff");

			}
		});
		screenLockWatch.startWatch();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		screenLockWatch.stopWatch();
	}

}
