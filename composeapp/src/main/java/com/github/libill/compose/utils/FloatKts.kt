package com.github.libill.compose.utils

import android.content.Context
import androidx.compose.ui.unit.Dp

fun Float.pxToDp(context: Context): Dp {
    val screenPixelDensity = context.resources.displayMetrics.density
    val dpVal = this / screenPixelDensity
    return Dp(dpVal)
}