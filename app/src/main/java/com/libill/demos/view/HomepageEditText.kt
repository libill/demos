package com.libill.demos.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.widget.AppCompatEditText
import com.libill.demos.R

/**
 * Created by Administrator on 2017/12/7 0007.
 */
class HomepageEditText : AppCompatEditText, OnFocusChangeListener, TextWatcher {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private var mClearDrawable: Drawable? = null

    private fun initView() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = compoundDrawables[2]
        if (mClearDrawable == null) {
            mClearDrawable = resources.getDrawable(R.drawable.ic_launcher)
        }
        mClearDrawable?.let {
            it?.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
        }


        //设置清除按钮是否显示
        setDelBtnVisible(false)
        onFocusChangeListener = this
        addTextChangedListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mClearDrawable != null && event.action == MotionEvent.ACTION_UP) {
            val x = event.x.toInt()
            //判断触摸点是否在水平范围内
            //getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
            val isInnerWidth =
                x > width - totalPaddingRight && x < width - paddingRight
            //获取删除图标的边界，返回一个Rect对象
            val rect = mClearDrawable!!.bounds
            //获取删除图标的高度
            val height = rect.height()
            val y = event.y.toInt()
            //计算图标底部到控件底部的距离
            val distance = (getHeight() - height) / 2
            //判断触摸点是否在竖直范围内(可能会有点误差)
            //触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            val isInnerHeigh = y > distance && y < height + distance
            if (isInnerHeigh && isInnerWidth) {
                setText("")
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     */
    private fun setDelBtnVisible(b: Boolean) {
        val right = if (b) mClearDrawable else null
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], right,
            compoundDrawables[3])
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) {
            setDelBtnVisible(text!!.isNotEmpty())
        } else {
            setDelBtnVisible(hasFocus)
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (hasFocus()) {
            setDelBtnVisible(s.length > 0)
        }
    }

    override fun afterTextChanged(s: Editable) {}
}