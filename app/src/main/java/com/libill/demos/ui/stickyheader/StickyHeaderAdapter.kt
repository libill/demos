package com.libill.demos.ui.stickyheader

import android.view.ViewGroup
import com.libill.demos.adapter.BaseRecyclerAdapter
import com.libill.demos.adapter.ViewBindingVH
import com.libill.demos.databinding.LayoutStickyHeaderItemBinding

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
internal class StickyHeaderAdapter : BaseRecyclerAdapter<StickyData, LayoutStickyHeaderItemBinding>() {
    override fun createViewBindingViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingVH<LayoutStickyHeaderItemBinding> {
        return ViewBindingVH.create(parent, LayoutStickyHeaderItemBinding::inflate)
    }

    override fun bindViewBindingViewHolder(
        holder: ViewBindingVH<LayoutStickyHeaderItemBinding>,
        position: Int
    ) {
        val item = getItem(position)
        holder.binding.tvName.text = item.name
    }
}
