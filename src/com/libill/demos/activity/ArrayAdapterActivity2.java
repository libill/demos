package com.libill.demos.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.libill.demos.R;

public class ArrayAdapterActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrayadapter);
		
		//界面中的ListView是View，View在layout目录下通过xml文件格式生成，用getViewById()获取
		ListView listView = (ListView) findViewById(R.id.listview);
		
		// 控制数据怎样在ListView中显示是Controller
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData());
		
		//View和Model是通过桥梁Adapter来连接起来。
		listView.setAdapter(adapter);

		// 点击事件，Controller负责
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// position是从0开始的,获取点击item的内容
				Toast.makeText(ArrayAdapterActivity2.this, getData().get(position), Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 要显示的数据Model，Model在values目录下通过xml文件格式生成
	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		Resources res =getResources();
		// 取xml文件格式的字符数组
		String[] good=res.getStringArray(R.array.good);
		
//		Resources res =getResources();
		// 取xml文件格式的字符数组
		String[] array = res.getStringArray(R.array.two);
		String[][] result = getTwoDimensionalArray(array);
		
		for(int i=0;i<good.length;i++){
			data.add(good[i]);
		}
		return data;
	}
	
	/**
	 * 按设定规则解析一维数组为二维数组
	 * @param array
	 * @return
	 */
	private String[][] getTwoDimensionalArray(String[] array) {
		String[][] twoDimensionalArray = null;
		for (int i = 0; i < array.length; i++) {
			String[] tempArray = array[i].split(",");
			if (twoDimensionalArray == null) {
				twoDimensionalArray = new String[array.length][tempArray.length];
			}
			for (int j = 0; j < tempArray.length; j++) {
				twoDimensionalArray[i][j] = tempArray[j];
			}
		}
		return twoDimensionalArray;
	}
}
