package com.libill.demos.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class LifecycleActivity extends Activity {
	public String TAG = "LifecycleActivity";
	private TextView textView;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss SSS");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		// 使TextView可以滚动
		textView.setMovementMethod(new ScrollingMovementMethod());
		setContentView(textView);
		logAndSetText("onCreate");
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

	public void logAndSetText(String text) {
		Log.i(TAG, text);
		textView.setText(textView.getText() + "\n" + sdf.format(new Date()) + " : " + text);
	}
}
