package com.libill.demos.activity;

import android.app.Activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.libill.demos.R;

public class UDPActivity extends Activity {
    private EditText et_ip;
    private EditText et_content;
    private Button bt_send_data;
    DatagramSocket socket = null;
    InetAddress serverAddress = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_udp);

        et_ip = (EditText) findViewById(R.id.et_ip);
        et_content = (EditText) findViewById(R.id.et_content);
        bt_send_data = (Button) findViewById(R.id.bt_send);


        bt_send_data.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(socket == null) {
                    try {
                        socket = new DatagramSocket(8888);
                        serverAddress = InetAddress.getByName(et_ip.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String sendData = et_content.getText().toString();
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