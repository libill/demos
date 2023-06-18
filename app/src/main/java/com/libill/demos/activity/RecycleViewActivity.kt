package com.libill.demos.activity

import android.os.Bundle
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityRecycleviewBinding

/**
 * Date: 2023/6/18
 * Desc:
 * SinceVer: 1.0.0
 */
class RecycleViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityRecycleviewBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
    }
}