package com.libill.demos.activity;

import android.app.Activity;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UDPActivity extends Activity {
	private Button bt_send_data = null;
	DatagramSocket socket = null;
	InetAddress serverAddress = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bt_send_data = new Button(this);
		bt_send_data.setText("发送");
		setContentView(bt_send_data);
		try {
			socket = new DatagramSocket(8888);
			serverAddress = InetAddress.getByName("192.168.1.103");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bt_send_data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							String sendData = "hello world";
							byte data[] = sendData.getBytes();
							DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 8888);
							socket.send(packet);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}
}