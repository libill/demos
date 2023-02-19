package com.libill.demos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.libill.demos.R

class MainBaseAdapter(private val mContext: Context, private val list: List<Class<*>>) : BaseAdapter() {
    private val mInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: mInflater.inflate(R.layout.list_item, null).apply {
            val holder = ViewHolder(this)
            tag = holder
        }
        val holder = view.tag as ViewHolder
        holder.tvTitle.text = list[position].toString()
            .split(".").lastOrNull() ?: ""
        return view
    }

    inner class ViewHolder(view: View) {
        val tvTitle: TextView

        init {
            tvTitle = view.findViewById<View>(R.id.tv_title) as TextView
        }
    }
}