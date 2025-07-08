package com.libill.demos.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.libill.demos.R
import com.libill.demos.databinding.LayoutPhotoBottomSheetBinding

/**
 * @Description: 禁止下拖关闭的底部对话框
 * @Author: libill
 * @Date: 2020-07-19 21:32
 */
class AddPhotoBottomDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutPhotoBottomSheetBinding.inflate(layoutInflater).root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity(), R.style.BottomDialog)
        dialog.setCanceledOnTouchOutside(true);
        return dialog
    }

    private fun fullScreenImmersive(view: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
            view?.systemUiVisibility = uiOptions
        }
    }

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    override fun onResume() {
        super.onResume()
        view?.postDelayed({
            setWindowParams()
        }, 200)
    }

    private fun setWindowParams() {
        val window = dialog?.window
        // 全屏隐藏导航栏
        window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        fullScreenImmersive(window?.decorView)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

        // 背景透明
        val windowParams = window?.attributes
        windowParams!!.dimAmount = 0.0f
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        windowParams.gravity = Gravity.BOTTOM
        windowParams.windowAnimations = R.style.BottomDialogAnimation;
        window.attributes = windowParams
    }
}

fun AppCompatActivity.showAddPhotoDialog(): AddPhotoBottomDialogFragment {
    var addPhotoDialog = AddPhotoBottomDialogFragment()
    addPhotoDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
    return addPhotoDialog
}