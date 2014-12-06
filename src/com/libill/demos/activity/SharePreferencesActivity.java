package com.libill.demos.activity;

import com.libill.demos.sharemanager.ShareManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SharePreferencesActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Button button = new Button(this);
		setContentView(button);
		
		button.setText("点击一下屏幕哦，就可以设置SharedPreferences并获取值显示在屏幕上");
		
		// 生成对象shareManager
		final ShareManager shareManager = new ShareManager(this);
		// 设置名称
		shareManager.setMyName("li xiao long");
		// 设置isGood为true
		shareManager.setIsGood(true);
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				button.setText("myName:"+shareManager.getMyName()+"  ; "+"isGood:"+shareManager.isGood());
			};
		});
	}
}
