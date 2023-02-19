package com.libill.demos.ui.stickyheader

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.libill.demos.databinding.ActivityStickyHeaderBinding
import com.libill.demos.base.BaseActivity

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
class StickyHeaderActivity : BaseActivity() {

    private val mAdapter by lazy {
        StickyHeaderAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStickyHeaderBinding.inflate(layoutInflater).apply {
            setContentView(this.root)

            recyclerView.run {
                adapter = mAdapter
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            val list = mutableListOf<StickyData>()
            for (i in 0..100) {
                list += StickyData("name $i")
            }
            mAdapter.setData(list)
        }
    }
}
