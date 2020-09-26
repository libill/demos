package com.libill.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.libill.demos.R
import java.util.*


/**
 * Created by gjz on 8/23/16.
 */
class WaveSideBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mIndexItems: Array<String>

    /**
     * the index in [.mIndexItems] of the current selected index item,
     * it's reset to -1 when the finger up
     */
    private var mCurrentIndex = -1

    /**
     * Y coordinate of the point where finger is touching,
     * the baseline is top of [.mStartTouchingArea]
     * it's reset to -1 when the finger up
     */
    private var mCurrentY = -1f
    private lateinit var mPaint: Paint
    private var mTextColor: Int
    private var mTextSize: Float

    /**
     * the height of each index item
     */
    private var mIndexItemHeight = 0f

    /**
     * offset of the current selected index item
     */
    private var mMaxOffset: Float

    /**
     * [.mStartTouching] will be set to true when [MotionEvent.ACTION_DOWN]
     * happens in this area, and the side bar should start working.
     */
    private val mStartTouchingArea = RectF()

    /**
     * height and width of [.mStartTouchingArea]
     */
    private var mBarHeight = 0f
    private var mBarWidth = 0f

    /**
     * Flag that the finger is starting touching.
     * If true, it means the [MotionEvent.ACTION_DOWN] happened but
     * [MotionEvent.ACTION_UP] not yet.
     */
    private var mStartTouching = false

    /**
     * if true, the [OnSelectIndexItemListener.onSelectIndexItem]
     * will not be called until the finger up.
     * if false, it will be called when the finger down, up and move.
     */
    private var mLazyRespond = false

    /**
     * the position of the side bar, default is [.POSITION_RIGHT].
     * You can set it to [.POSITION_LEFT] for people who use phone with left hand.
     */
    private var mSideBarPosition: Int

    /**
     * the alignment of items, default is [.TEXT_ALIGN_CENTER].
     */
    private var mTextAlignment: Int

    /**
     * observe the current selected index item
     */
    private var onSelectIndexItemListener: OnSelectIndexItemListener? = null

    /**
     * the baseline of the first index item text to draw
     */
    private var mFirstItemBaseLineY = 0f

    /**
     * for [.dp2px] and [.sp2px]
     */
    private val mDisplayMetrics: DisplayMetrics

    private fun initPaint() {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = mTextColor
        mPaint.textSize = mTextSize
        when (mTextAlignment) {
            TEXT_ALIGN_CENTER -> mPaint.textAlign = Paint.Align.CENTER
            TEXT_ALIGN_LEFT -> mPaint.textAlign = Paint.Align.LEFT
            TEXT_ALIGN_RIGHT -> mPaint.textAlign = Paint.Align.RIGHT
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val fontMetrics = mPaint.fontMetrics
        mIndexItemHeight = fontMetrics.bottom - fontMetrics.top
        mBarHeight = mIndexItems.size * mIndexItemHeight

        // calculate the width of the longest text as the width of side bar
        for (indexItem in mIndexItems) {
            mBarWidth = Math.max(mBarWidth, mPaint.measureText(indexItem))
        }
        val areaLeft: Float = (if (mSideBarPosition == POSITION_LEFT) 0 else width - mBarWidth - paddingRight) as Float
        val areaRight = if (mSideBarPosition == POSITION_LEFT) paddingLeft + areaLeft + mBarWidth else width.toFloat()
        val areaTop = height / 2 - mBarHeight / 2
        val areaBottom = areaTop + mBarHeight
        mStartTouchingArea[areaLeft, areaTop, areaRight] = areaBottom

        // the baseline Y of the first item' text to draw
        mFirstItemBaseLineY = (height / 2 - mIndexItems.size * mIndexItemHeight / 2
                + (mIndexItemHeight / 2 - (fontMetrics.descent - fontMetrics.ascent) / 2)
                - fontMetrics.ascent)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw each item
        var i = 0
        val mIndexItemsLength = mIndexItems.size
        while (i < mIndexItemsLength) {
            val baseLineY = mFirstItemBaseLineY + mIndexItemHeight * i

            // calculate the scale factor of the item to draw
            // val scale = 0
            // val alphaScale = 255
            // val textSize = mTextSize
            val scale = getItemScale(i)
            val alphaScale = if (i == mCurrentIndex) 255 else (255 * (1 - scale)).toInt()
            mPaint.alpha = alphaScale
            mPaint.textSize = mTextSize + mTextSize * scale
            var baseLineX = 0f
            if (mSideBarPosition == POSITION_LEFT) {
                when (mTextAlignment) {
                    TEXT_ALIGN_CENTER -> baseLineX = paddingLeft + mBarWidth / 2 + mMaxOffset * scale
                    TEXT_ALIGN_LEFT -> baseLineX = paddingLeft + mMaxOffset * scale
                    TEXT_ALIGN_RIGHT -> baseLineX = paddingLeft + mBarWidth + mMaxOffset * scale
                }
            } else {
                when (mTextAlignment) {
                    TEXT_ALIGN_CENTER -> baseLineX = width - paddingRight - mBarWidth / 2 - mMaxOffset * scale
                    TEXT_ALIGN_RIGHT -> baseLineX = width - paddingRight - mMaxOffset * scale
                    TEXT_ALIGN_LEFT -> baseLineX = width - paddingRight - mBarWidth - mMaxOffset * scale
                }
            }

            // draw
            canvas.drawText(
                    mIndexItems[i],  //item text to draw
                    baseLineX,  //baseLine X
                    baseLineY,  // baseLine Y
                    mPaint)
            i++
        }

        // reset paint
        mPaint.alpha = 255
        mPaint.textSize = mTextSize
    }

    /**
     * calculate the scale factor of the item to draw
     *
     * @param index the index of the item in array [.mIndexItems]
     * @return the scale factor of the item to draw
     */
    private fun getItemScale(index: Int): Float {
        var scale = 0f
        if (mCurrentIndex != -1) {
            val distance = Math.abs(mCurrentY - (mIndexItemHeight * index + mIndexItemHeight / 2)) / mIndexItemHeight
            scale = 1 - distance * distance / 16
            scale = Math.max(scale, 0f)
        }
        return scale
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mIndexItems.isEmpty()) {
            return super.onTouchEvent(event)
        }
        val eventY = event.y
        val eventX = event.x
        mCurrentIndex = getSelectedIndex(eventY)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> return if (mStartTouchingArea.contains(eventX, eventY)) {
                mStartTouching = true
                if (!mLazyRespond && onSelectIndexItemListener != null) {
                    onSelectIndexItemListener!!.onSelectIndexItem(mIndexItems[mCurrentIndex])
                }
                invalidate()
                true
            } else {
                mCurrentIndex = -1
                false
            }
            MotionEvent.ACTION_MOVE -> {
                if (mStartTouching && !mLazyRespond && onSelectIndexItemListener != null) {
                    onSelectIndexItemListener!!.onSelectIndexItem(mIndexItems[mCurrentIndex])
                }
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (mLazyRespond && onSelectIndexItemListener != null) {
                    onSelectIndexItemListener!!.onSelectIndexItem(mIndexItems[mCurrentIndex])
                }
                mCurrentIndex = -1
                mStartTouching = false
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun getSelectedIndex(eventY: Float): Int {
        mCurrentY = eventY - (height / 2 - mBarHeight / 2)
        if (mCurrentY <= 0) {
            return 0
        }
        var index = (mCurrentY / mIndexItemHeight).toInt()
        if (index >= mIndexItems.size) {
            index = mIndexItems.size - 1
        }
        return index
    }

    private fun dp2px(dp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), mDisplayMetrics)
    }

    private fun sp2px(sp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), mDisplayMetrics)
    }

    fun setIndexItems(vararg indexItems: String) {
        mIndexItems = Arrays.copyOf(indexItems, indexItems.size)
        requestLayout()
    }

    fun setTextColor(color: Int) {
        mTextColor = color
        mPaint.color = color
        invalidate()
    }

    fun setPosition(position: Int) {
        require(!(position != POSITION_RIGHT && position != POSITION_LEFT)) { "the position must be POSITION_RIGHT or POSITION_LEFT" }
        mSideBarPosition = position
        requestLayout()
    }

    fun setMaxOffset(offset: Int) {
        mMaxOffset = offset.toFloat()
        invalidate()
    }

    fun setLazyRespond(lazyRespond: Boolean) {
        mLazyRespond = lazyRespond
    }

    fun setTextAlign(align: Int) {
        if (mTextAlignment == align) {
            return
        }
        when (align) {
            TEXT_ALIGN_CENTER -> mPaint.textAlign = Paint.Align.CENTER
            TEXT_ALIGN_LEFT -> mPaint.textAlign = Paint.Align.LEFT
            TEXT_ALIGN_RIGHT -> mPaint.textAlign = Paint.Align.RIGHT
            else -> throw IllegalArgumentException(
                    "the alignment must be TEXT_ALIGN_CENTER, TEXT_ALIGN_LEFT or TEXT_ALIGN_RIGHT")
        }
        mTextAlignment = align
        invalidate()
    }

    fun setTextSize(size: Float) {
        if (mTextSize == size) {
            return
        }
        mTextSize = size
        mPaint.textSize = size
        invalidate()
    }

    fun setOnSelectIndexItemListener(onSelectIndexItemListener: OnSelectIndexItemListener?) {
        this.onSelectIndexItemListener = onSelectIndexItemListener
    }

    interface OnSelectIndexItemListener {
        fun onSelectIndexItem(index: String?)
    }

    companion object {
        private const val DEFAULT_TEXT_SIZE = 14 // sp
        private const val DEFAULT_MAX_OFFSET = 80 //dp
        private val DEFAULT_INDEX_ITEMS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        const val POSITION_RIGHT = 0
        const val POSITION_LEFT = 1
        const val TEXT_ALIGN_CENTER = 0
        const val TEXT_ALIGN_LEFT = 1
        const val TEXT_ALIGN_RIGHT = 2
    }

    init {
        mDisplayMetrics = context.resources.displayMetrics
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveSideBar)
        mLazyRespond = typedArray.getBoolean(R.styleable.WaveSideBar_sidebar_lazy_respond, false)
        mTextColor = typedArray.getColor(R.styleable.WaveSideBar_sidebar_text_color, Color.GRAY)
        mTextSize = typedArray.getDimension(R.styleable.WaveSideBar_sidebar_text_size, sp2px(DEFAULT_TEXT_SIZE))
        mMaxOffset = typedArray.getDimension(R.styleable.WaveSideBar_sidebar_max_offset, dp2px(DEFAULT_MAX_OFFSET))
        mSideBarPosition = typedArray.getInt(R.styleable.WaveSideBar_sidebar_position, POSITION_RIGHT)
        mTextAlignment = typedArray.getInt(R.styleable.WaveSideBar_sidebar_text_alignment, TEXT_ALIGN_CENTER)
        typedArray.recycle()
        mIndexItems = DEFAULT_INDEX_ITEMS
        initPaint()
    }
}
