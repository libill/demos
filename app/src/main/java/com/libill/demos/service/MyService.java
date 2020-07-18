package com.libill.demos.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.libill.demos.R;

public class MyService extends Service {

	public static final String TAG = "MyService";

	private MyBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		NotificationManager notificationManager = (NotificationManager) getSystemService
				(NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("这是通知的标题")
				//设置内容
				.setContentText("这是通知的内容")
				//设置大图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
				//设置小图标
				.setSmallIcon(R.drawable.ic_launcher)
				//设置通知时间
				.setWhen(System.currentTimeMillis())
				//首次进入时显示效果
				.setTicker("我是测试内容")
				//设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
				.setDefaults(Notification.DEFAULT_SOUND);
		//发送通知请求
		notificationManager.notify(1, mBuilder.build());

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