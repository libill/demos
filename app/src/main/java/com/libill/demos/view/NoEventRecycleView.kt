package com.libill.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.libill.demos.R

class NoEventRecycleView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //    //不拦截，继续分发下去
    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return false
    }

    override fun dispatchTouchEvent(e: MotionEvent?): Boolean {
        return false
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = android.graphics.Color.BLUE
    }
    private val parentPadding = resources.getDimensionPixelSize(R.dimen.recycle_view_padding)

    override fun draw(canvas: Canvas) {
        drawBackground(canvas)
        super.draw(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        val rWidth = (width - parentPadding * 2) / 2
        val rHeight = rWidth / 4 * 3

        var left: Int
        var right: Int
        var top: Int
        var bottom: Int

        val count = (height / rHeight).toInt() * 2
        for (i in 0 until count) {
            left = if (i % 2 == 0) {
                parentPadding
            } else {
                parentPadding * 3 + rWidth
            }
            right = left + rWidth

            val topIndex = i / 2
            top = parentPadding * (1 + topIndex) + rHeight * topIndex
            bottom = top + rHeight

            val rect = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
            canvas.drawRect(rect, mPaint)
            if (i == 0) {
                firstViewRectF = rect
                firstViewRectF.right = (parentPadding * 3 + rWidth).toFloat()
            }
            Log.i("NoEventRecycleView", "height:$height, rHeight:$rHeight, count:$count")
        }
    }

    private var firstViewRectF = RectF()

    fun getFirstViewRectF(): RectF {
        return firstViewRectF
    }
}