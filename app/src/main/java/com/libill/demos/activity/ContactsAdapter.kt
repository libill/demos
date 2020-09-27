package com.libill.demos.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.libill.demos.R
import com.libill.demos.activity.ContactsAdapter.ContactsViewHolder


class ContactsAdapter(private val contacts: List<Contact>, private val layoutId: Int) : RecyclerView.Adapter<ContactsViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layoutId, null)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        if (position == 0 || contacts[position - 1].index != contact.index) {
            holder.tvIndex.visibility = View.VISIBLE
            holder.tvIndex.text = contact.index
        } else {
            holder.tvIndex.visibility = View.GONE
        }
        holder.tvName.text = contact.name
    }

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvIndex: TextView
        var ivAvatar: ImageView
        var tvName: TextView

        init {
            tvIndex = itemView.findViewById<View>(R.id.tv_index) as TextView
            ivAvatar = itemView.findViewById<View>(R.id.iv_avatar) as ImageView
            tvName = itemView.findViewById<View>(R.id.tv_name) as TextView
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}