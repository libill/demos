package com.libill.demos.activity.array

import android.view.ViewGroup
import com.libill.demos.adapter.BaseRecyclerAdapter
import com.libill.demos.adapter.ViewBindingVH
import com.libill.demos.databinding.ItemArrayListBinding

class ArrayAdapterAdapter(
    private val clickCallback: (position: Int) -> Unit
) : BaseRecyclerAdapter<String, ItemArrayListBinding>() {

    override fun createViewBindingViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingVH<ItemArrayListBinding> {
        return ViewBindingVH.create(parent, ItemArrayListBinding::inflate)
    }

    override fun bindViewBindingViewHolder(holder: ViewBindingVH<ItemArrayListBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.titleTv.text = item
        holder.binding.titleTv.setOnClickListener {
            clickCallback.invoke(position)
        }
    }
}