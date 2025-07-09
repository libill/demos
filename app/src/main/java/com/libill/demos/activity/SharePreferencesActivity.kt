package com.libill.demos.activity

import android.os.Bundle
import android.view.animation.AnimationUtils
import com.libill.demos.R
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivitySharepreferencesBinding
import com.libill.demos.sharemanager.ShareManager

class SharePreferencesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySharepreferencesBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            btSaveShare.text = "点击一下屏幕哦，就可以设置SharedPreferences并获取值显示在屏幕上"
            val alphaAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.my_anim_window_close_in)
            // R.anim.show_anim
            btSaveShare.postDelayed({ btSaveShare.startAnimation(alphaAnimation) }, 1000)

            // 生成对象shareManager
            val shareManager = ShareManager(baseContext)
            // 设置名称
            shareManager.myName = "li xiao long"
            // 设置isGood为true
            shareManager.setIsGood(true)

            btSaveShare.setOnClickListener {
                btSaveShare.text = "myName:" + shareManager.myName + "  ; " + "isGood:" + shareManager.isGood
            }
        }
    }
}
