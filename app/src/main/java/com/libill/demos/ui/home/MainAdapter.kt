package com.libill.demos.ui.home

import android.view.ViewGroup
import com.libill.demos.adapter.BaseRecyclerAdapter
import com.libill.demos.adapter.ViewBindingVH
import com.libill.demos.databinding.ListItemBinding

class MainAdapter() : BaseRecyclerAdapter<Class<*>, ListItemBinding>() {

    override fun createViewBindingViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingVH<ListItemBinding> {
        return ViewBindingVH.create(parent, ListItemBinding::inflate)
    }

    override fun bindViewBindingViewHolder(holder: ViewBindingVH<ListItemBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.tvTitle.text = item.toString().split(".").lastOrNull() ?: ""
    }
}