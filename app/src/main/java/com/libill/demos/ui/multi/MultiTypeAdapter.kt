package com.libill.demos.ui.multi

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.libill.demos.adapter.BaseRecyclerAdapter
import com.libill.demos.adapter.ViewBindingVH
import com.libill.demos.databinding.LayoutMultiOneItemBinding
import com.libill.demos.databinding.LayoutMultiThreeItemBinding
import com.libill.demos.databinding.LayoutMultiTwoItemBinding

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
class MultiTypeAdapter : BaseRecyclerAdapter<MultiTypeData, ViewBinding>() {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.value
    }

    override fun createViewBindingViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH<ViewBinding> {
        return when (viewType) {
            MultiType.One.value -> {
                ViewBindingVH.create(parent, LayoutMultiOneItemBinding::inflate)
            }
            MultiType.Two.value -> {
                ViewBindingVH.create(parent, LayoutMultiTwoItemBinding::inflate)
            }
            MultiType.Three.value -> {
                ViewBindingVH.create(parent, LayoutMultiThreeItemBinding::inflate)
            }
            else -> {
                ViewBindingVH.create(parent, LayoutMultiOneItemBinding::inflate)
            }
        }
    }

    override fun bindViewBindingViewHolder(holder: ViewBindingVH<ViewBinding>, position: Int) {
        val item = getItem(position)
        when (item.viewType) {
            MultiType.One -> {
                (holder.binding as LayoutMultiOneItemBinding).bindData(data = item, position = position)
            }
            MultiType.Two -> {
                (holder.binding as LayoutMultiTwoItemBinding).bindData(data = item, position = position)
            }
            MultiType.Three -> {
                (holder.binding as LayoutMultiThreeItemBinding).bindData(data = item, position = position)
            }
        }
    }

    private fun LayoutMultiOneItemBinding.bindData(data: MultiTypeData, position: Int) {
        tvName.text = data.name
    }

    private fun LayoutMultiTwoItemBinding.bindData(data: MultiTypeData, position: Int) {
        tvName.text = data.name
    }

    private fun LayoutMultiThreeItemBinding.bindData(data: MultiTypeData, position: Int) {
        tvName.text = data.name
    }
}