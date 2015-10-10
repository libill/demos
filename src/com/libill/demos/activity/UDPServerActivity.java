package com.libill.demos.activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UDPServerActivity  extends Activity {

	private Button requestButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestButton = new Button(this);// 作为客户端
		requestButton.setText("开启");
		
		setContentView(requestButton);
		requestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				requestButton.setText("已开启");
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						DatagramSocket socket = null;
						try {
							socket = new DatagramSocket(8888);
						
						while (true) {
							byte data[] = new byte[1024];
							DatagramPacket packet = new DatagramPacket(data, data.length);
							System.out.println("result--->2");
							socket.receive(packet);
							System.out.println("result--->3");
							final String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
							System.out.println("result--->4" + result);
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(UDPServerActivity.this, result+"", Toast.LENGTH_SHORT).show();
									
								}
							});
							
						}
						
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
				}).start();

			}

		});
	}

}