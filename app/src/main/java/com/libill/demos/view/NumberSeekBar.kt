package com.libill.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSeekBar
import com.libill.demos.R

/**
 * @Description:
 * @Author: libill
 * @Date: 2020-07-18 20:39
 */

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class NumberSeekBar : AppCompatSeekBar {
    private var mPaint: Paint? = null
    private var textSize: Float = 15F

    // 进度文字位置信息
    private val mProgressTextRect: Rect = Rect()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        attrs?.let {
            val attributeValues = context.obtainStyledAttributes(it, R.styleable.NumberSeekBar)
            with(attributeValues) {
                try {
                    textSize = getDimension(R.styleable.NumberSeekBar_progressTextSize, 20F)
                } finally {
                    recycle()
                }
            }
        }

        initPaint()
    }

    private fun initPaint() {
        mPaint = TextPaint()
        mPaint!!.isAntiAlias = true
        mPaint!!.color = Color.parseColor("#00574B")
        mPaint!!.textSize = textSize
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawProgressText(canvas)
    }

    private fun drawProgressText(canvas: Canvas) {
        val progressText: String = progress.toString()
        mPaint!!.getTextBounds(progressText, 0, progressText.length, mProgressTextRect)

        val progressRatio: Float = progress.toFloat() / max.toFloat()
        var thumbX = (width - paddingEnd - paddingStart) * progressRatio + paddingStart - this.thumb.intrinsicWidth / 2.0f
        if (progressRatio > 0.99f) {
            thumbX -= this.thumb.intrinsicWidth / 2.0f
        } else if (progressRatio < 0.1f) {
            thumbX += this.thumb.intrinsicWidth / 5.0f
        }

        val thumbY: Float = 0f + paddingTop
        canvas.drawText(progressText, thumbX, thumbY, mPaint!!)
    }
}