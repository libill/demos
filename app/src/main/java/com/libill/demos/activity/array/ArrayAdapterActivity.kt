package com.libill.demos.activity;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.libill.base.TestCode;

import aa.ThreadTestTTTT;

public class ArrayAdapterActivity extends Activity {

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		// test
		DisplayMetrics displayMetrics = newBase.getApplicationContext().getResources().getDisplayMetrics();
		Configuration configuration = newBase.getApplicationContext().getResources().getConfiguration();
		configuration.orientation = ORIENTATION_PORTRAIT;
		newBase.getApplicationContext().getResources().updateConfiguration(configuration, displayMetrics);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Integer> intergerList = new ArrayList<>();
		Set<Integer> a = new HashSet<Integer>();
		// registerActivityLifecycleCallbacks();
		// getFragmentManager().registerFragmentLifecycleCallbacks();
		TestCode t = new TestCode();
		new ThreadTestTTTT();
		//界面中的ListView是View，这里通过硬编码的方式直接Java代码生成
		ListView listView = new ListView(this);

		// 控制数据怎样在ListView中显示是Controller
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData());
		
		//View和Model是通过桥梁Adapter来连接起来。
		listView.setAdapter(adapter);
		setContentView(listView);

		// 点击事件，Controller负责
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// position是从0开始的,获取点击item的内容
				Toast.makeText(ArrayAdapterActivity.this, getData().get(position), Toast.LENGTH_SHORT).show();
			}
		});
		printClassLoader();
	}

	// 要显示的数据Model，通过硬编码的方式直接Java代码生成
	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");
		return data;
	}

	private void printClassLoader() {
		ClassLoader classLoader = getClassLoader();
		while (classLoader != null) {
			Log.i("ClassLoaderTag", "ClassLoader:"+classLoader);
			classLoader = classLoader.getParent();
		}

		Log.i("ClassLoaderTag", "Activity ClassLoader:"+Activity.class.getClassLoader());
		Log.i("ClassLoaderTag", "AppCompatActivity ClassLoader:"+ AppCompatActivity.class.getClassLoader());
	}
}
