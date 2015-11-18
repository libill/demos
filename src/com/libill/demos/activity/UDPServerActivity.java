package com.libill.demos.activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.libill.demos.R;

public class UDPServerActivity extends Activity {

    private TextView tv_ip;
    private TextView tv_receive_msg;
    private Button bt_start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_udp);
        tv_ip = (TextView) findViewById(R.id.tv_ip);
        tv_receive_msg = (TextView) findViewById(R.id.tv_receive_msg);
        bt_start = (Button) findViewById(R.id.bt_start);// 作为客户端

        // 添加滚动属性
        tv_receive_msg.setMovementMethod(ScrollingMovementMethod.getInstance());

        getCurrentIP();

        bt_start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                bt_start.setText("已开启");

                runUDPServer();
            }

        });
    }

    private void runUDPServer(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(8888);

                    while (true) {
                        byte data[] = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        socket.receive(packet);
                        final String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                        Log.i("get result:", result);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(UDPServerActivity.this, result + "", Toast.LENGTH_SHORT).show();
                                tv_receive_msg.setText(tv_receive_msg.getText().toString() + "\n" + result);
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getCurrentIP() {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        tv_ip.setText("本机IP地址："+ip);
    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }


}