package com.libill.demos.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityServiceBinding
import com.libill.demos.service.MyService
import com.libill.demos.service.MyService.MyBinder
import com.libill.demos.service.RemoteAIDLService
import com.libill.demos.service.RemoteService

class ServiceActivity : BaseActivity() {
    private var myBinder: MyBinder? = null

    private var remoteAIDLService: RemoteAIDLService? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            myBinder = service as MyBinder
            myBinder!!.startDownload()
        }
    }

    private val remoteConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            remoteAIDLService = RemoteAIDLService.Stub.asInterface(service)
            try {
                val result = remoteAIDLService?.plus(50, 50)
                val upperStr = remoteAIDLService?.toUpperCase("comes from ClientTest")
                Log.d("TAG", "result is $result")
                Log.d("TAG", "upperStr is $upperStr")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityServiceBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            startService.setOnClickListener { view: View? ->
                val startIntent = Intent(
                    this@ServiceActivity,
                    MyService::class.java
                )
                startService(startIntent)
            }
            stopService.setOnClickListener { view: View? ->
                Log.d("MyService", "click Stop Service button")
                val stopIntent = Intent(this@ServiceActivity, MyService::class.java)
                stopService(stopIntent)
            }
            bindService.setOnClickListener { view: View? ->
                val bindIntent = Intent(
                    this@ServiceActivity,
                    MyService::class.java
                )
                bindService(bindIntent, connection, BIND_AUTO_CREATE)
            }
            unbindService.setOnClickListener { view: View? ->
                Log.d("MyService", "click Unbind Service button")
                unbindService(connection)
            }

            startRemoteService.setOnClickListener { view: View? ->
                val startRIntent = Intent(
                    this@ServiceActivity,
                    RemoteService::class.java
                )
                startService(startRIntent)
            }
            stopRemoteService.setOnClickListener { view: View? ->
                Log.d("RemoteService", "click Stop Service button")
                val stopRIntent =
                    Intent(this@ServiceActivity, RemoteService::class.java)
                stopService(stopRIntent)
            }
            bindRemoteService.setOnClickListener { view: View? ->
                val bindRIntent =
                    Intent("com.libill.demos.service.RemoteAIDLService")
                bindService(bindRIntent, remoteConnection, BIND_AUTO_CREATE)
            }
            unbindRemoteService.setOnClickListener { view: View? ->
                Log.d("RemoteService", "click Unbind Service button")
                unbindService(remoteConnection)
            }
        }

    }
}