package com.libill.demos.view

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.libill.demos.R
import kotlinx.android.synthetic.main.homepage_edit_text_layout.view.*

/**
 * Author: liqiongwei
 * Date: 2020/9/23
 * Desc: 输入框
 * SinceVer: 1.0.0
 */
class HomepageEditTextLayout : LinearLayout {
    private lateinit var mTextHint: String
    private lateinit var mText: String
    private lateinit var mInputType: String

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.homepage_edit_text_layout, this, true)
    }

    private fun initView(attrs: AttributeSet?) {
        attrs?.let {
            val attributeValues = context.obtainStyledAttributes(it, R.styleable.HomepageEditTextLayout)
            with(attributeValues) {
                try {
                    mText = this.getString(R.styleable.HomepageEditTextLayout_homepageText) ?: ""
                    mTextHint = this.getString(R.styleable.HomepageEditTextLayout_homepageTextHint) ?: ""
                    mInputType = this.getString(R.styleable.HomepageEditTextLayout_homepageInputType) ?: "text"
                } finally {
                    recycle()
                }
            }
        }
        var type: Int = InputType.TYPE_CLASS_TEXT
        when (mInputType) {
            "text" -> {
                type = InputType.TYPE_CLASS_TEXT
            }
            "phone" -> {
                type = InputType.TYPE_CLASS_PHONE
            }
            "password" -> {
                type = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        et_input.inputType = type
        et_input.setText(mText)

        iv_clear_text.setOnClickListener {
            et_input.setText("")
        }

        et_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    iv_clear_text.visibility = View.VISIBLE
                } else {
                    iv_clear_text.visibility = View.GONE
                }
            }
        })
    }

    fun setText(text: String) {
        et_input.setText(text)
    }

    fun getText(): String {
        return et_input.text.toString()
    }
}