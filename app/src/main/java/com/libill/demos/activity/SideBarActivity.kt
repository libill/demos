package com.libill.demos.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libill.demos.R
import com.libill.demos.view.WaveSideBar
import java.util.*


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
    }

    private fun initData() {
        contacts.addAll(Contact.englishContacts)
    }
}
