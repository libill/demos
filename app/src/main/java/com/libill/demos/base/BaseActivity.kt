package com.libill.demos.base

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Date: 2023/2/19
 * Desc:
 * SinceVer: 1.0.0
 */
abstract class BaseActivity: AppCompatActivity() {

    private var contentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        contentView = view
        view?.let {
            observeWindowInsets(it)
        }
    }

    protected open fun observeWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}