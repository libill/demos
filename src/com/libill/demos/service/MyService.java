package com.libill.demos.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.libill.demos.R;
import com.libill.demos.activity.MainActivity;

public class MyService extends Service {

	public static final String TAG = "MyService";

	private MyBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		Notification notification = new Notification(R.drawable.ic_launcher, "有通知到来", System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容", pendingIntent);
		startForeground(1, notification);
		Log.d(TAG, "onCreate() executed");
        try {  
            Thread.sleep(10000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 开始执行后台任务
			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() executed");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}

	public class MyBinder extends Binder {

		public void startDownload() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 执行具体的下载任务
				}
			}).start();
		}

	}

}