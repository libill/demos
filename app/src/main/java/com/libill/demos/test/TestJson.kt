package com.libill.demos.test

import android.os.Debug
import com.didichuxing.doraemonkit.util.GsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Arrays
import java.util.LinkedList


class TestJson {
    fun toJson() {
        val m = Debug.MemoryInfo()
        Debug.getMemoryInfo(m)

        val listType: Type = object : TypeToken<LinkedList<User?>?>() {}.getType()
        Gson().toJson("", listType)
        val a: Class<Type> = listType.javaClass
//        val t: TestGeneric<String> = TestGeneric<String>()
//        t.setValue("Alo")
//        val type = object : TypeToken<TestGeneric<String?>?>() {}.type
//
//        val gStr: String = GsonUtils.gson.toJson(t, type)
//        println(gStr)
//        val t1: TestGeneric = GsonUtils.gson.fromJson(gStr, type)
//        System.out.println(t1.getValue())

    }
}

data class User(val userName: String)

