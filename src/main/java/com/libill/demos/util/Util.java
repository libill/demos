package com.libill.demos.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

public class Util {

	/**
	 * Android判断App是否在前台运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = am.getRunningTasks(2);

		ComponentName cn = tasksInfo.get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		// Log.i("p:", currentPackageName+" "+tasksInfo.size());
		if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
			return true;
		}
		
		// 小米1，有时候Activity在最顶，但是com.miui.home会在点击Home键时，变成最顶（可恶）
		if (tasksInfo.size() > 1) {
			ComponentName cn2 = tasksInfo.get(1).topActivity;
			String currentPackageName2 = cn2.getPackageName();
			// Log.i("p2:", currentPackageName2);
			if (!TextUtils.isEmpty(currentPackageName2) && currentPackageName2.equals(context.getPackageName())) {
				return true;
			}
		}

		return false;
	}
}
