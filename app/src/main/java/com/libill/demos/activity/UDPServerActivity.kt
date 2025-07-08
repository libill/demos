package com.libill.demos.activity

import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import com.libill.demos.R
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityServerUdpBinding
import java.net.DatagramPacket
import java.net.DatagramSocket

class UDPServerActivity : BaseActivity() {
    private lateinit var binding: ActivityServerUdpBinding
    private var mHandler: Handler? = null
    private var socket: DatagramSocket? = null

    private var isRunning: Boolean = true

    private val mRunnable = Runnable {
        try {
            socket = DatagramSocket(8888)

            while (isRunning) {
                val data = ByteArray(1024)
                val packet = DatagramPacket(data, data.size)
                socket!!.receive(packet)
                val result = String(packet.data, packet.offset, packet.length)
                Log.i("get result:", result)

                runOnUiThread {
                    Toast.makeText(
                        this@UDPServerActivity,
                        result + "",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvReceiveMsg.text = """
                        ${binding.tvReceiveMsg.text}
                        $result
                        """.trimIndent()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_udp)
        ActivityServerUdpBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            binding = this
            // 添加滚动属性
            tvReceiveMsg.movementMethod = ScrollingMovementMethod.getInstance()
            val thread = HandlerThread("MyHandlerThread")
            thread.start()
            mHandler = Handler(thread.looper)
            btStart.setOnClickListener {
                btStart.text = "已开启"
                mHandler?.post(mRunnable)
            }
        }
    }

    private val currentIP: Unit
        get() {
            //获取wifi服务
            val wifiManager =
                getSystemService(WIFI_SERVICE) as WifiManager
            //判断wifi是否开启
            if (!wifiManager.isWifiEnabled) {
                wifiManager.setWifiEnabled(true)
            }
            val wifiInfo = wifiManager.connectionInfo
            val ipAddress = wifiInfo.ipAddress
            val ip = intToIp(ipAddress)
            binding.tvIp.text = "本机IP地址：$ip"
        }

    private fun intToIp(i: Int): String {
        return (i and 0xFF).toString() + "." +
                ((i shr 8) and 0xFF) + "." +
                ((i shr 16) and 0xFF) + "." +
                (i shr 24 and 0xFF)
    }

    override fun onDestroy() {
        isRunning = false
        if (socket != null && socket!!.isConnected) {
            socket?.disconnect()
            socket?.close()
            socket = null
        }

        mHandler?.removeCallbacks(mRunnable)
        super.onDestroy()
    }
}