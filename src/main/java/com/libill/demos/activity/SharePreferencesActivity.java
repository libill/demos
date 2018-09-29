package com.libill.demos.activity;

import com.libill.demos.R;
import com.libill.demos.sharemanager.ShareManager;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SharePreferencesActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sharepreferences);
		final Button button = (Button) findViewById(R.id.bt_save_share);
		button.setText("点击一下屏幕哦，就可以设置SharedPreferences并获取值显示在屏幕上");
		final Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim_window_close_in);
		// R.anim.show_anim
		button.postDelayed(new Runnable() {
			@Override
			public void run() {
				button.startAnimation(alphaAnimation);
			}
		},1000);

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
