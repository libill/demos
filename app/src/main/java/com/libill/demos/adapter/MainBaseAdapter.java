package com.libill.demos.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.libill.demos.R;

public class MainBaseAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Map<String, String>> list;
	ViewHolder viewHolder;

	public MainBaseAdapter(Context context, List<Map<String, String>> list) {
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
			view = mInflater.inflate(R.layout.list_item, null);
			final ViewHolder holder = new ViewHolder(view);
			view.setTag(holder);
		}
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.tv_title.setText(list.get(position).get("title"));
		return view;
	}

	private class ViewHolder {
		private TextView tv_title;

		public ViewHolder(View view) {
			tv_title = (TextView) view.findViewById(R.id.tv_title);
		}
	}
}
