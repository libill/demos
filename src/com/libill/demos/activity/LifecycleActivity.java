package com.libill.demos.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class LifecycleActivity extends Activity {
	public String TAG = "LifecycleActivity";
	private TextView textView;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss SSS");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		printlnLog("onCreate", getIntent());

		textView = new TextView(this);
		// 使TextView可以滚动
		textView.setMovementMethod(new ScrollingMovementMethod());
		setContentView(textView);
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifecycleActivity.this, LifecycleActivity.class);
				intent.putExtra("aTestContent","This is a test content.");
				startActivity(intent);
			}
		});
		logAndSetText("onCreate");
	}


	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		printlnLog("onNewIntent", intent);
	}

	@Override
	protected void onStart() {
		super.onStart();
		logAndSetText("onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		logAndSetText("onResume");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			logAndSetText("onWindowFocusChanged");
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		logAndSetText("onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		logAndSetText("onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		logAndSetText("onDestroy");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Intent intent = new Intent(this, LifecycleActivity.class);
		intent.putExtra("aTestContent","This is a test content.");
		startActivity(intent);
		return super.onTouchEvent(event);
	}

	public void logAndSetText(String text) {
		Log.i(TAG, text);
		textView.setText(textView.getText() + "\n" + sdf.format(new Date()) + " : " + text);
	}

	public void printlnLog(String tag, Intent intent) {
		String content = intent.getStringExtra("aTestContent");
		Log.i(tag, content);
	}
}
