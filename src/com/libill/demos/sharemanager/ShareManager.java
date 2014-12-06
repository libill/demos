package com.libill.demos.sharemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareManager {

	private SharedPreferences share;
	private Editor editor;
	private String TAG = "ShareManager";

	private ShareManager() {
		super();
	}

	public void clear() {
		editor.clear().commit();
	};

	public ShareManager(Context context) {
		super();
		share = context.getSharedPreferences(ShareContents.ShareName, Context.MODE_PRIVATE);
		editor = share.edit();
	}

	/**
	 * 获取myName
	 * 
	 * @return
	 */
	public String getMyName() {
		String result = share.getString(ShareContents.myName, "");
		return result;
	}

	/**
	 * 设置myName
	 * 
	 * @param myName
	 */
	public void setMyName(String myName) {
		editor.putString(ShareContents.myName, myName).commit();
	}
	
	/**
	 * 获取isGood
	 * @return
	 */
	public boolean isGood(){
		boolean result = share.getBoolean(ShareContents.isGood, false);;
		return result;
	}
	
	/**
	 * 设置isGood
	 * @param isGood
	 */
	public void setIsGood(boolean isGood){
		editor.putBoolean(ShareContents.isGood, isGood).commit();
	}
}
