package com.libill.base.handler;

import android.os.SystemClock;

public class AHandler {

    private ALooper mLooper;
    private AMessageQueue mQueue;

    public AHandler() {
        mLooper = ALooper.myLooper();
        mQueue = ALooper.getQueue();
    }

    public void handleMessage(AMessage message) {

    }

    public boolean post(AMessage message) {
        return postDelayed(message, 0);
    }

    public boolean postDelayed(AMessage message, long delayMillis) {
        return sendMessageAtTime(message, SystemClock.uptimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(AMessage message, long delayMillis) {
        AMessageQueue queue = mQueue;
        return enqueueMessage(queue, message, delayMillis);
    }

    private boolean enqueueMessage(AMessageQueue queue, AMessage message, long delayMillis) {
        message.target = this;
        return queue.enqueueMessage(queue, message, delayMillis);
    }

}
