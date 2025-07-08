package com.libill.demos.activity

import android.os.Bundle
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivitySeekBarBinding
import com.libill.demos.dialog.showAddPhotoDialog

/**
 * @Description:
 * @Author: libil
 * @Date: 2020-07-18 16:13
 */
class SeekBarActivity : BaseActivity() {
    private lateinit var binding: ActivitySeekBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeekBarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDialog.setOnClickListener {
            showAddPhotoDialog()
        }
    }
}