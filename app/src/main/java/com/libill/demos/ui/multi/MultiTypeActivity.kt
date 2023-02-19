package com.libill.demos.ui.multi

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityMultiTypeBinding

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
class MultiTypeActivity : BaseActivity() {

    private val mAdapter by lazy {
        MultiTypeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMultiTypeBinding.inflate(layoutInflater).apply {
            setContentView(this.root)

            recyclerView.run {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            val list = mutableListOf<MultiTypeData>()
            for (i in 0..300) {
                val multiType = when (i % 3) {
                    0 -> MultiType.One
                    1 -> MultiType.Two
                    2 -> MultiType.Three
                    else -> MultiType.One
                }
                list += MultiTypeData(multiType, "name $i")
            }
            mAdapter.setData(list)
        }
    }
}
