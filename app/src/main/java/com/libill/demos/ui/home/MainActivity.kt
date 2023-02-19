package com.libill.demos.ui.home

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.libill.demos.R
import com.libill.demos.activity.*
import com.libill.demos.adapter.MainBaseAdapter
import com.libill.demos.ui.multi.MultiTypeActivity
import com.libill.demos.ui.stickyheader.StickyHeaderActivity

class MainActivity : ListActivity() {

    val list = mutableListOf<Class<*>>()

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_main)
        list.run {
            add(ArrayAdapterListActivity::class.java)
            add(ArrayAdapterActivity::class.java)
            add(SimpleAdapterActivity::class.java)
            add(SimpleAdapterListActivity::class.java)
            add(SimpleCursorAdapterActivity::class.java)
            add(BaseAdapterActivity::class.java)
            add(KeyWatchActivity::class.java)
            add(ServiceActivity::class.java)
            add(PhoneInformationActivity::class.java)
            add(LifecycleActivity::class.java)
            add(SharePreferencesActivity::class.java)
            add(ArrayAdapterActivity2::class.java)
            add(UDPActivity::class.java)
            add(UDPServerActivity::class.java)
            add(SeekBarActivity::class.java)
            add(SideBarActivity::class.java)
            add(StickyHeaderActivity::class.java)
            add(MultiTypeActivity::class.java)
        }

        val adapter = MainBaseAdapter(this, list)
        this.listAdapter = adapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        startActivityNow(list[position])
    }

    fun startActivityNow(aClass: Class<*>?) {
        val intent = Intent(this@MainActivity, aClass)
        intent.putExtra("aTestContent", "This is a test content.")
        startActivity(intent)
    }
}