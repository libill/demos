package com.libill.demos.ui.home

import androidx.lifecycle.ViewModel
import com.libill.demos.activity.array.ArrayAdapterActivity
import com.libill.demos.activity.ArrayAdapterActivity2
import com.libill.demos.activity.ArrayAdapterListActivity
import com.libill.demos.activity.BaseAdapterActivity
import com.libill.demos.activity.KeyWatchActivity
import com.libill.demos.activity.LifecycleActivity
import com.libill.demos.activity.PhoneInformationActivity
import com.libill.demos.activity.SeekBarActivity
import com.libill.demos.activity.ServiceActivity
import com.libill.demos.activity.SharePreferencesActivity
import com.libill.demos.activity.SideBarActivity
import com.libill.demos.activity.SimpleAdapterActivity
import com.libill.demos.activity.SimpleAdapterListActivity
import com.libill.demos.activity.SimpleCursorAdapterActivity
import com.libill.demos.activity.UDPActivity
import com.libill.demos.activity.UDPServerActivity
import com.libill.demos.ui.multi.MultiTypeActivity
import com.libill.demos.ui.stickyheader.StickyHeaderActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val mClassList = MutableStateFlow<List<Class<*>>>(emptyList())
    val classList = mClassList.asStateFlow()

    init {
        updateClassList()
    }

    private fun updateClassList() {
        val list = mutableListOf<Class<*>>()
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
        mClassList.value = list
    }
}