package com.libill.demos;

//public class Singleton {
//	private static Singleton instance = null;
//	private Singleton() {
//	}
//	// 饿汉式：在懒汉式下添加synchronized，已达到线程安全
//	public static synchronized  Singleton getInstance() {
//		if (instance == null) {
//			instance = new Singleton();
//		}
//		return instance;
//	}
//}
//public class Singleton{
//	private static Singleton singleton = new Singleton();
//	private Singleton(){};
//	public Singleton getInstance(){
//		return singleton;
//	}
//}

public class Singleton {
	private static Singleton instance = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		synchronized (Singleton.class) {
			if (instance == null) {
				instance = new Singleton();
			}
		}
		return instance;
	}
}

