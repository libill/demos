package com.libill.demos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
class ViewBindingVH<VB : ViewBinding> constructor(val binding: VB) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        inline fun <T : ViewBinding> create(
            parent: ViewGroup,
            crossinline block: (inflater: LayoutInflater, container: ViewGroup, attach: Boolean) -> T
        ) = ViewBindingVH(block(LayoutInflater.from(parent.context), parent, false))
    }
}