package com.libill.demos.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private val mViewModel by viewModels<MainViewModel>()

    private val mAdapter by lazy {
        MainAdapter().apply {
            setItemClickListener { v, position ->
                startActivityNow(mViewModel.classList.value[position])
            }
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = mAdapter
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    mViewModel.classList.collect {
                        mAdapter.setData(it.toList())
                    }
                }
            }
        }
    }

    private fun startActivityNow(aClass: Class<*>?) {
        val intent = Intent(this@MainActivity, aClass)
        intent.putExtra("aTestContent", "This is a test content.")
        startActivity(intent)
    }
}