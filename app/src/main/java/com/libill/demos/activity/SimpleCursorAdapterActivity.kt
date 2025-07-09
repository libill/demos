package com.libill.demos.activity

import android.R
import android.os.Bundle
import android.provider.Contacts
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.lifecycle.lifecycleScope
import com.libill.demos.base.BaseActivity
import com.libill.demos.util.requestReadContactsPermission
import kotlinx.coroutines.launch

class SimpleCursorAdapterActivity : BaseActivity() {
    var listView: ListView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val granted = requestReadContactsPermission("需要读取通讯录权限")
            if (granted) {
                setData()
            } else {
                Toast.makeText(this@SimpleCursorAdapterActivity, "权限被拒绝", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData() {
        //获得一个指向系统通讯录数据库的Cursor对象获得数据来源
        val cur = contentResolver.query(Contacts.People.CONTENT_URI, null, null, null, null)
        startManagingCursor(cur)
        //实例化列表适配器
        val adapter: ListAdapter = SimpleCursorAdapter(
            this, R.layout.simple_list_item_1, cur, arrayOf(Contacts.People.NAME), intArrayOf(
                R.id.text1
            )
        )
        listView!!.adapter = adapter
    }
}
