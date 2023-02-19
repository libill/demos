package com.libill.demos.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
abstract class BaseRecyclerAdapter<M, VB: ViewBinding>(
    list: List<M>? = null,
    bind: (BaseRecyclerAdapter<M, VB>.() -> Unit)? = null
) : RecyclerView.Adapter<ViewBindingVH<VB>>() {

    private var dataList = mutableListOf<M>()

    init {
        if (bind != null) {
            apply(bind)
        }
        list?.apply {
            dataList = list.toMutableList()
        }
    }

    private var mOnItemClickListener: ((v: View, position: Int) -> Unit)? = null
    private var mOnItemLongClickListener: ((v: View, position: Int) -> Boolean) = { _, _ -> false }

    fun setData(list: Collection<M>?): Boolean {
        var result = false
        dataList.clear()
        if (list != null) {
            result = dataList.addAll(list)
        }
        notifyDataSetChanged()
        return result
    }

    fun getItem(position: Int) = dataList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH<VB> {
        val viewHolder = createViewBindingViewHolder(parent, viewType)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener { mOnItemClickListener?.invoke(it, viewHolder.adapterPosition) }
        itemView.setOnLongClickListener {
            return@setOnLongClickListener mOnItemLongClickListener.invoke(
                it,
                viewHolder.adapterPosition
            )
        }
        return viewHolder
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewBindingVH<VB>, position: Int) {
        bindViewBindingViewHolder(holder, position)
    }

    fun setItemClickListener(onClickListener: (v: View, position: Int) -> Unit) {
        mOnItemClickListener = onClickListener
    }

    fun getData(): List<M> {
        return dataList
    }

    abstract fun createViewBindingViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH<VB>

    abstract fun bindViewBindingViewHolder(holder: ViewBindingVH<VB>, position: Int)
}
