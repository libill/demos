package com.libill.demos.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ArrayAdapterListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] str = { "1", "2", "3" };
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, str);
		setListAdapter(adapter);
		
		// 点击事件
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// position是从0开始的，要记住哦
				Toast.makeText(ArrayAdapterListActivity.this, position+"", Toast.LENGTH_SHORT).show();
			}
		});
	}
}