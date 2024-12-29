package com.libill.demos.util


import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import java.util.Locale

/**
 * 主题需要加上这个<item name="android:typeface">serif</item>
 */

// 韩文/英文字体
private val ENGLISH_NORMAL_FONT: Typeface by lazy {
    Typeface.createFromAsset(SdkInit.appContext.assets, "fonts/poppins_regular.ttf")
}

// 中文字体
private val CHINESE_NORMAL_FONT: Typeface by lazy {
    Typeface.createFromAsset(SdkInit.appContext.assets, "fonts/RuiZiYunZiKuZhunYuanTiGBK-1.ttf")
}

private fun getNormalFont(): Typeface {
    return if (isChinaLanguage()) {
        CHINESE_NORMAL_FONT
    } else {
        ENGLISH_NORMAL_FONT
    }
}

private fun isChinaLanguage():Boolean {
    val defaultLocale = Locale.getDefault()
    return defaultLocale == Locale.CHINA || defaultLocale == Locale.CHINESE
}

fun TextView.useNormalFont(): TextView = apply { typeface = getNormalFont() }

fun Paint.useNormalFont(): Paint = apply { typeface = getNormalFont() }

fun changeDefaultFont() {
    try {
        val serifField = Typeface::class.java.getDeclaredField("SERIF")
        serifField.isAccessible = true
        serifField[null] = getNormalFont()
    } catch (e: Exception) {
        e.printStackTrace()
    }
//    try {
//        val monospaceField = Typeface::class.java.getDeclaredField("MONOSPACE")
//        monospaceField.isAccessible = true
//        monospaceField[null] = NORMAL_FONT
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    try {
//        val defaultBoldField = Typeface::class.java.getDeclaredField("DEFAULT_BOLD")
//        defaultBoldField.isAccessible = true
//        defaultBoldField[null] = BOLD_FONT
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
}
