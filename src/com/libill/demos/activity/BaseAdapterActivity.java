package com.libill.demos.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.libill.demos.R;

public class BaseAdapterActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		
		map = new HashMap<String, String>();
		map.put("TITLE", "AA");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("TITLE", "BB");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("TITLE", "CC");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("TITLE", "DD");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("TITLE", "EE");
		list.add(map);
		
		ABaseAdapter adapter = new ABaseAdapter(this, list);
		ListView listView = new ListView(this);
		listView.setAdapter(adapter);
		setContentView(listView);
	}
}


class ABaseAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Map<String, String>> list;
	ViewHolder viewHolder;

	public ABaseAdapter(Context context, List<Map<String, String>> list) {
		this.mContext = context;
		this.list = list;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = mInflater.inflate(R.layout.layout_baseadapter_list_item, null);
			final ViewHolder holder = new ViewHolder(view);
			view.setTag(holder);
		}
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.iv_img.setImageResource(R.drawable.ic_launcher);
		holder.tv_title.setText(list.get(position).get("TITLE"));
		return view;
	}

	private class ViewHolder {
		private ImageView iv_img;
		private TextView tv_title;

		public ViewHolder(View view) {
			iv_img = (ImageView) view.findViewById(R.id.iv_img);
			tv_title = (TextView) view.findViewById(R.id.tv_title);
		}
	}
}