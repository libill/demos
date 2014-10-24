package com.libill.demos.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ArrayAdapterActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = new ListView(this);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
		listView.setAdapter(adapter);
		setContentView(listView);
		
		// 点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// position是从0开始的,获取点击item的内容
				Toast.makeText(ArrayAdapterActivity.this, getData().get(position), Toast.LENGTH_SHORT).show();				
			}
		});
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		
		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");
		
		return data;
	}
}
