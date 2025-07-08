package com.libill.demos.activity

import android.os.Bundle
import com.libill.demos.base.BaseActivity
import com.libill.demos.databinding.ActivityUdpBinding
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UDPActivity : BaseActivity() {
    private var socket: DatagramSocket? = null
    private var serverAddress: InetAddress? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUdpBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            btSend.setOnClickListener {
                if (socket == null) {
                    try {
                        socket = DatagramSocket(8888)
                        serverAddress = InetAddress.getByName(etIp.text.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                Thread {
                    try {
                        val sendData = etContent.text.toString()
                        val data = sendData.toByteArray()
                        val packet = DatagramPacket(data, data.size, serverAddress, 8888)
                        socket!!.send(packet)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }.start()
            }
        }
    }
}