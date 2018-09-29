package com.libill.demos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.libill.demos.service.RemoteAIDLService.Stub;

public class RemoteService extends Service {

	public static final String TAG = "RemoteService";

	@Override
	public void onCreate() {
		super.onCreate();

		Log.d(TAG, "onCreate() executed");
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		Log.d("TAG", "process id is " + Thread.currentThread().getId());
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

	RemoteAIDLService.Stub mBinder = new Stub() {

		@Override
		public String toUpperCase(String str) throws RemoteException {
			if (str != null) {
				return str.toUpperCase();
			}
			return null;
		}

		@Override
		public int plus(int a, int b) throws RemoteException {
			return a + b;
		}
	};

}