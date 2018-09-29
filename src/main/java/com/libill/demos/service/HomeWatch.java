package com.libill.demos.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.libill.demos.util.Util;

public class HomeWatch {

	static final String TAG = "HomeWatcher";
	private Context mContext;
	private IntentFilter mFilter;
	private OnHomePressedListener mListener;
	private InnerRecevier mRecevier;

	// 回调接口
	public interface OnHomePressedListener {
		public void onHomePressed();

		public void onHomeLongPressed();
	}

	public HomeWatch(Context context) {
		mContext = context;
		mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
	}

	/**
	 * 设置监听
	 * 
	 * @param listener
	 */
	public void setOnHomePressedListener(OnHomePressedListener listener) {
		mListener = listener;
		mRecevier = new InnerRecevier();
	}

	/**
	 * 开始监听，注册广播
	 */
	public void startWatch() {
		if (mRecevier != null) {
			mContext.registerReceiver(mRecevier, mFilter);
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
	class InnerRecevier extends BroadcastReceiver {
		final String SYSTEM_DIALOG_REASON_KEY = "reason";
		final String SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions";
		final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
		final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			boolean isForeground = Util.isRunningForeground(context);
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
				if (isForeground && reason != null) {
					Log.e(TAG, "action:" + action + ",reason:" + reason);
					if (mListener != null) {
						if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
							// 短按home键
							mListener.onHomePressed();
							Log.e(TAG, "短按home键");
						} else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
							// 长按home键
							mListener.onHomeLongPressed();
							Log.e(TAG, "长按home键");
						}
					}
				}
			}
		}
	}
}
