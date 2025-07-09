package com.libill.demos.util

import android.app.Activity

fun Activity.isActivityRunning(): Boolean {
    return !this.isFinishing && !this.isDestroyed
}
