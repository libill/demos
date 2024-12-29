package com.libill.base;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread sdudentThread = new PeopleThread("student");
        Thread teacherThread = new PeopleThread("teacher");
        sdudentThread.start();
        sdudentThread.join();
        teacherThread.start();
    }

    private static class PeopleThread extends Thread {

        public PeopleThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 250; i++) {
                if (i % 2 == 0) {
                    System.out.println(i+" name:" + getName());
                    Thread.yield();
//                    interrupt();
                    try {
                        sleep(500L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


}

