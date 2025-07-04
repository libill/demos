package com.libill.base.handler;

public class ALooper {
    private static final ThreadLocal<ALooper> threadLocal = new ThreadLocal<ALooper>();
    private static AMessageQueue mQueue;
    private Thread mThread;

    private ALooper(boolean quitAllowed) {
        mQueue = new AMessageQueue(quitAllowed);
        mThread = Thread.currentThread();
    }

    public static void prepare() {
        if (threadLocal.get() != null) {
            throw new RuntimeException("only one looper can be created in one thread!");
        }
        threadLocal.set(new ALooper(true));
    }

    public static void loop() {
        for (; ; ) {
            loopOnce();
        }
    }

    public static void loopOnce() {

    }

    public static ALooper myLooper() {
        return threadLocal.get();
    }

    public static AMessageQueue getQueue() {
        return mQueue;
    }
}
