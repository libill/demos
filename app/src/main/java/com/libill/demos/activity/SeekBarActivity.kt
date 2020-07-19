package com.libill.demos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.libill.demos.databinding.ActivitySeekBarBinding
import com.libill.demos.dialog.AddPhotoBottomDialogFragment

/**
 * @Description:
 * @Author: libil
 * @Date: 2020-07-18 16:13
 */
class SeekBarActivity : AppCompatActivity() {
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

    private fun showAddPhotoDialog() {
        var addPhotoDialog = AddPhotoBottomDialogFragment()
        addPhotoDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
    }
}