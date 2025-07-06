package com.libill.demos.activity.array

import aa.ThreadTestTTTT
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libill.base.TestCode
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityArrayListBinding

class ArrayAdapterActivity : BaseActivity() {

    private val dataList = getData()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        // test
        val displayMetrics = newBase.applicationContext.resources.displayMetrics
        val configuration = newBase.applicationContext.resources.configuration
        configuration.orientation = Configuration.ORIENTATION_PORTRAIT
        newBase.applicationContext.resources.updateConfiguration(configuration, displayMetrics)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intergerList: List<Int> = ArrayList()
        val a: Set<Int> = HashSet()
        // registerActivityLifecycleCallbacks();
        // getFragmentManager().registerFragmentLifecycleCallbacks();
        val t = TestCode()
        ThreadTestTTTT()
        //界面中的ListView是View，这里通过硬编码的方式直接Java代码生成

        // 控制数据怎样在ListView中显示是Controller
        val mAdapter = ArrayAdapterAdapter() { position ->
            Toast.makeText(baseContext, dataList[position], Toast.LENGTH_SHORT).show()
        }


        ActivityArrayListBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = mAdapter
            }
            mAdapter.setData(dataList)
        }
        printClassLoader()
    }

    /**
     * 要显示的数据Model，通过硬编码的方式直接Java代码生成
     */
    private fun getData(): List<String> {
        val data = mutableListOf<String>()
        data.add("a")
        data.add("b")
        data.add("c")
        data.add("d")
        return data
    }

    private fun printClassLoader() {
        var classLoader = classLoader
        while (classLoader != null) {
            Log.i("ClassLoaderTag", "ClassLoader:$classLoader")
            classLoader = classLoader.parent
        }

        Log.i("ClassLoaderTag", "Activity ClassLoader:" + Activity::class.java.classLoader)
        Log.i(
            "ClassLoaderTag",
            "AppCompatActivity ClassLoader:" + AppCompatActivity::class.java.classLoader
        )
    }
}
