package com.libill.demos.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.libill.demos.R
import com.libill.demos.databinding.LayoutPhotoBottomSheetBinding

/**
 * @Description: 禁止下拖关闭的底部对话框
 * @Author: libill
 * @Date: 2020-07-19 21:32
 */
class AddPhotoBottomDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutPhotoBottomSheetBinding.inflate(layoutInflater).root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val d = super.onCreateDialog(savedInstanceState)
        //view hierarchy is inflated after dialog is shown
        d.setOnShowListener {
            //this disables outside touch
            //d.window?.findViewById<View>(R.id.touch_outside)?.setOnClickListener(null)
            //this prevents dragging behavior
            (d.window?.findViewById<View>(R.id.design_bottom_sheet)?.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
        }
        return d
    }
}