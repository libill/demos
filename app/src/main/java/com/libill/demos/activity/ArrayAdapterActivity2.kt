package com.libill.demos.activity

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.libill.demos.R
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityArrayadapterBinding

class ArrayAdapterActivity2 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityArrayadapterBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            // 控制数据怎样在ListView中显示是Controller
            val adapter = ArrayAdapter(
                this@ArrayAdapterActivity2, android.R.layout.simple_expandable_list_item_1,
                data
            )

            //View和Model是通过桥梁Adapter来连接起来。
            listView.adapter = adapter

            // 点击事件，Controller负责
            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                // position是从0开始的,获取点击item的内容
                Toast.makeText(
                    this@ArrayAdapterActivity2,
                    data[position],
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val data: List<String>
        // 要显示的数据Model，Model在values目录下通过xml文件格式生成
        get() {
            val data: MutableList<String> =
                ArrayList()
            val res = resources
            // 取xml文件格式的字符数组
            val good = res.getStringArray(R.array.good)
            //		Resources res =getResources();
            // 取xml文件格式的字符数组
            val array =
                res.getStringArray(R.array.two)
            val result = getTwoDimensionalArray(array)

            for (i in good.indices) {
                data.add(good[i])
            }
            return data
        }

    /**
     * 按设定规则解析一维数组为二维数组
     * @param array
     * @return
     */
    private fun getTwoDimensionalArray(array: Array<String>): Array<Array<String?>>? {
        var twoDimensionalArray: Array<Array<String?>>? = null
        for (i in array.indices) {
            val tempArray =
                array[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (twoDimensionalArray == null) {
                twoDimensionalArray = Array(array.size) { arrayOfNulls(tempArray.size) }
            }
            for (j in tempArray.indices) {
                twoDimensionalArray[i][j] = tempArray[j]
            }
        }
        return twoDimensionalArray
    }
}
