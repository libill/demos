package com.libill.demos.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.libill.demos.R
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityContactsBinding
import com.libill.demos.util.IDecorationCallback
import com.libill.demos.util.PinnedSectionDecoration
import com.libill.demos.view.WaveSideBar
import java.util.Locale


class SideBarActivity : BaseActivity() {
    private val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_contacts)
        ActivityContactsBinding.inflate(layoutInflater).apply {
            setContentView(this.root)

            rvContacts.setLayoutManager(LinearLayoutManager(this@SideBarActivity))
            rvContacts.setAdapter(ContactsAdapter(contacts, R.layout.item_contacts))
            sideBar.setOnSelectIndexItemListener(object : WaveSideBar.OnSelectIndexItemListener {
                override fun onSelectIndexItem(index: String?) {
                    for (i in contacts.indices) {
                        if (contacts[i].index == index) {
                            (rvContacts.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(i, 0)
                            return
                        }
                    }
                }
            })
            rvContacts.addItemDecoration(PinnedSectionDecoration(this@SideBarActivity, object : IDecorationCallback {
                override fun getGroupId(position: Int): Long {
                    return contacts[position].index.toCharArray()[0].code.toLong()
                }

                override fun getGroupFirstLine(position: Int): String {
                    return contacts[position].name.substring(0, 1).uppercase(Locale.ROOT)
                }
            }))
        }
    }

    private fun initData() {
        contacts.addAll(Contact.englishContacts)
    }
}
