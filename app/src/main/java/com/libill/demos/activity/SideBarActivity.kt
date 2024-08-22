package com.libill.demos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libill.demos.R
import com.libill.demos.util.IDecorationCallback
import com.libill.demos.util.PinnedSectionDecoration
import com.libill.demos.view.WaveSideBar
import java.util.*
import kotlin.collections.ArrayList


class SideBarActivity : AppCompatActivity() {
    private var rvContacts: RecyclerView? = null
    private lateinit var sideBar: WaveSideBar
    private val contacts: ArrayList<Contact> = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_contacts)
        rvContacts = findViewById(R.id.rv_contacts) as RecyclerView?
        rvContacts?.setLayoutManager(LinearLayoutManager(this))
        rvContacts?.setAdapter(ContactsAdapter(contacts, R.layout.item_contacts))
        sideBar = findViewById(R.id.side_bar) as WaveSideBar
        sideBar.setOnSelectIndexItemListener(object : WaveSideBar.OnSelectIndexItemListener {
            override fun onSelectIndexItem(index: String?) {
                for (i in contacts.indices) {
                    if (contacts[i].index.equals(index)) {
                        (rvContacts?.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(i, 0)
                        return
                    }
                }
            }
        })
        rvContacts?.addItemDecoration(PinnedSectionDecoration(this@SideBarActivity, object : IDecorationCallback {
            override fun getGroupId(position: Int): Long {
                return contacts[position].index.toCharArray()[0].code.toLong()
            }

            override fun getGroupFirstLine(position: Int): String {
               return  contacts[position].name.substring(0, 1).toUpperCase(Locale.ROOT)
            }
        }))
    }

    private fun initData() {
        contacts.addAll(Contact.englishContacts)
    }
}
