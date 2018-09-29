package com.libill.demos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.libill.demos.R;
import com.libill.demos.service.HomeKeyWatchService;
import com.libill.demos.service.ScreenLockWatchService;

public class KeyWatchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("Home键和关屏幕键监听例子");
		setContentView(textView);
		
		Intent homeServiceIntent = new Intent(this, HomeKeyWatchService.class);
		startService(homeServiceIntent);
		
		Intent screenLockServiceIntent = new Intent(this, ScreenLockWatchService.class);
		startService(screenLockServiceIntent);
	}

	@Override
	public void onBackPressed() {
		stopService(new Intent(this, HomeKeyWatchService.class));
		stopService(new Intent(this, ScreenLockWatchService.class));
		finish();
	}
}
