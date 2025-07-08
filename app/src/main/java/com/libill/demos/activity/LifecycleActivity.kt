package com.libill.demos.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import com.libill.test.R
import java.text.SimpleDateFormat
import java.util.Date

class LifecycleActivity : Activity() {
    companion object {
        private const val TAG: String = "LifecycleActivity"
    }

    private var textView: TextView? = null
    private var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss SSS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        printlnLog("onCreate", intent)

        textView = TextView(this)
        textView!!.setTextColor(resources.getColor(R.color.black))
        // 使TextView可以滚动
        textView!!.movementMethod = ScrollingMovementMethod()
        setContentView(textView)
        textView!!.setOnClickListener {
            val intent = Intent(
                this@LifecycleActivity,
                LifecycleActivity::class.java
            )
            intent.putExtra("aTestContent", "This is a test content.")
            startActivity(intent)
        }
        logAndSetText("onCreate")
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        printlnLog("onNewIntent", intent)
    }

    override fun onStart() {
        super.onStart()
        logAndSetText("onStart")
    }

    override fun onResume() {
        super.onResume()
        logAndSetText("onResume")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            logAndSetText("onWindowFocusChanged")
        }
    }

    override fun onPause() {
        super.onPause()
        logAndSetText("onPause")
    }

    override fun onStop() {
        super.onStop()
        logAndSetText("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logAndSetText("onDestroy")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val intent = Intent(this, LifecycleActivity::class.java)
        intent.putExtra("aTestContent", "This is a test content.")
        startActivity(intent)
        return super.onTouchEvent(event)
    }

    fun logAndSetText(text: String) {
        Log.i(TAG, text)
        textView!!.text = """${textView!!.text}${sdf.format(Date())} : $text"""
    }

    private fun printlnLog(tag: String?, intent: Intent) {
        val content = intent.getStringExtra("aTestContent")
        Log.i(tag, content!!)
    }
}
