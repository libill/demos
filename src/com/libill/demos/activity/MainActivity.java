package com.libill.demos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.libill.demos.R;
import com.libill.demos.adapter.MainBaseAdapter;

public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		
		map = new HashMap<String, String>();
		map.put("title", "ArrayAdapterListActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "ArrayAdapterActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "SimpleAdapterActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "SimpleAdapterListActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "SimpleCursorAdapterActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "BaseAdapterActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "KeyWatchActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "ServiceActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "PhoneInfomationActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "LifecycleActivity");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("title", "SharePreferencesActivity");
		list.add(map);
		
		MainBaseAdapter adapter = new MainBaseAdapter(this, list);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivityNow(ArrayAdapterListActivity.class);
			break;
		case 1:
			startActivityNow(ArrayAdapterActivity.class);
			break;
		case 2:
			startActivityNow(SimpleAdapterActivity.class);
			break;
		case 3:
			startActivityNow(SimpleAdapterListActivity.class);
			break;
		case 4:
			startActivityNow(SimpleCursorAdapterActivity.class);
			break;
		case 5:
			startActivityNow(BaseAdapterActivity.class);
			break;	
		case 6:
			startActivityNow(KeyWatchActivity.class);
			break;		
		case 7:
			startActivityNow(ServiceActivity.class);
			break;		
		case 8:
			startActivityNow(PhoneInfomationActivity.class);
			break;			
		case 9:
			startActivityNow(LifecycleActivity.class);
			break;
		case 10:
			startActivityNow(SharePreferencesActivity.class);
			break;
			
			
		default:
			break;
		}
	}
	
	public void startActivityNow(Class aClass){
		Intent intent = new Intent(MainActivity.this, aClass);
		startActivity(intent);
	}
}