package com.libill.demos.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.libill.demos.util.Util;

public class ScreenLockWatch {

	static final String TAG = "HomeWatcher";
	private Context mContext;
	private IntentFilter mFilter;
	private OnScreenLockListener mListener;
	private ScreenLockReceive mRecevier;

	// 回调接口
	public interface OnScreenLockListener {
		public void onScreenOn();
		public void onScreenOff();
		public void onScreenPresent();
	}

	public ScreenLockWatch(Context context) {
		mContext = context;
		mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
	}

	/**
	 * 设置监听
	 * 
	 * @param listener
	 */
	public void setOnScreenLockListener(OnScreenLockListener listener) {
		mListener = listener;
		mRecevier = new ScreenLockReceive();
	}

	/**
	 * 开始监听，注册广播
	 */
	public void startWatch() {
		if (mRecevier != null) {
			// 注册事件
			mContext.registerReceiver(mRecevier, new IntentFilter(Intent.ACTION_SCREEN_ON));
			mContext.registerReceiver(mRecevier, new IntentFilter(Intent.ACTION_SCREEN_OFF));
			mContext.registerReceiver(mRecevier, new IntentFilter(Intent.ACTION_USER_PRESENT));
		}
	}

	/**
	 * 停止监听，注销广播
	 */
	public void stopWatch() {
		if (mRecevier != null) {
			mContext.unregisterReceiver(mRecevier);
		}
	}

	/**
	 * 广播接收者
	 */
	class ScreenLockReceive extends BroadcastReceiver {
		boolean islockScreen;

		@Override
		public void onReceive(Context context, Intent intent) {
			boolean isForeground = Util.isRunningForeground(context);
			if (isForeground){
				if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {// 当按下电源键，屏幕亮起的时候
					mListener.onScreenOn();
				}
				if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {// 当按下电源键，屏幕变黑的时候
					islockScreen = true;
					mListener.onScreenOff();
				}
				if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {// 当解除锁屏的时候
					islockScreen = false;
					mListener.onScreenPresent();
				}
			}
		}
	};
}
