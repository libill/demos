package com.libill.demos.test

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

class AtomicBooleanTest {
    private val atomicBoolean = AtomicBoolean(false)
    private val list = CopyOnWriteArrayList<Int>()

    fun test() {
        atomicBoolean.compareAndSet(false, true)
    }
}