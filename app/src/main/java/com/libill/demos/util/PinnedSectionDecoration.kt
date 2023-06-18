package com.libill.demos.ui.multi

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.text.TextPaint
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.libill.demos.R
import java.util.*

/**
 * Date: 2023/2/25
 * Desc:
 * SinceVer: 1.0.0
 */
class PinnedSectionDecoration(
    context: Context, decorationCallback: IDecorationCallback
) : ItemDecoration() {
    private val callback: IDecorationCallback
    private val textPaint: TextPaint
    private val paint: Paint
    private val topGap: Int
    private val fontMetrics = Paint.FontMetrics()

    init {
        val res: Resources = context.resources
        callback = decorationCallback
        paint = Paint()
        paint.color = res.getColor(R.color.design_default_color_error, null)
        textPaint = TextPaint()
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.isAntiAlias = true
        textPaint.textSize = 80f
        textPaint.color = Color.BLACK
        textPaint.getFontMetrics(fontMetrics)
        textPaint.textAlign = Paint.Align.LEFT

        topGap = res.getDimensionPixelSize(R.dimen.item_sticky_header_size)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        val groupId: Long = callback.getGroupId(pos)
        if (groupId < 0) return
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = topGap
        } else {
            outRect.top = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val lineHeight: Float = textPaint.textSize + fontMetrics.descent
        var preGroupId: Long
        var groupId: Long = -1
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupId = groupId
            groupId = callback.getGroupId(position)
            if (groupId < 0 || groupId == preGroupId) continue
            val textLine: String = callback.getGroupFirstLine(position).uppercase(Locale.ROOT)
            if (TextUtils.isEmpty(textLine)) continue
            val viewBottom = view.bottom
            var textY = Math.max(topGap, view.top).toFloat()
            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                val nextGroupId: Long = callback.getGroupId(position + 1)
                if (nextGroupId != groupId && viewBottom < textY) { //组内最后一个view进入了header
                    textY = viewBottom.toFloat()
                }
            }

            val rectF = RectF(left.toFloat(), textY - topGap, right.toFloat(), textY)
            c.drawRect(rectF, paint)


            val fontMetrics = textPaint.fontMetrics
            val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
            val baseline: Float = rectF.centerY() + distance
            c.drawText(textLine, rectF.left, baseline, textPaint)
        }
    }

    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val prevGroupId: Long = callback.getGroupId(pos - 1)
            val groupId: Long = callback.getGroupId(pos)
            prevGroupId != groupId
        }
    }

    companion object {
        private const val TAG = "PinnedSectionDecoration"
    }
}

interface IDecorationCallback {
    fun getGroupId(position: Int): Long
    fun getGroupFirstLine(position: Int): String
}
