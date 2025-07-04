package com.libill.base.handler;

public class AMessageQueue {
    private boolean mQuitAllowed = false;

    public AMessageQueue(boolean quitAllowed) {
        mQuitAllowed = quitAllowed;
    }


    public boolean enqueueMessage(AMessageQueue queue, AMessage message, long delayMillis) {
        return true;
    }

}
