package com.libill.demos.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import com.libill.demos.R;
import com.libill.demos.service.MyService;
import com.libill.demos.service.RemoteAIDLService;
import com.libill.demos.service.RemoteService;

public class ServiceActivity extends Activity {

	private Button startService;

	private Button stopService;

	private Button bindService;

	private Button unbindService;
	
	private Button startRemoteService;  
	  
    private Button stopRemoteService;  
  
    private Button bindRemoteService;  
  
    private Button unbindRemoteService;  
  
    private MyService.MyBinder myBinder;  
    
    private RemoteAIDLService remoteAIDLService;
  
    private ServiceConnection connection = new ServiceConnection() {  
  
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
        }  
  
        @Override  
        public void onServiceConnected(ComponentName name, IBinder service) {  
            myBinder = (MyService.MyBinder) service;  
            myBinder.startDownload();  
        }  
    };  
    
    private ServiceConnection remoteConnection = new ServiceConnection() {  
    	  
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
        }  
  
        @Override  
        public void onServiceConnected(ComponentName name, IBinder service) {  
            remoteAIDLService = RemoteAIDLService.Stub.asInterface(service);  
            try {  
                int result = remoteAIDLService.plus(50, 50);  
                String upperStr = remoteAIDLService.toUpperCase("comes from ClientTest");  
                Log.d("TAG", "result is " + result);  
                Log.d("TAG", "upperStr is " + upperStr);  
            } catch (RemoteException e) {  
                e.printStackTrace();  
            }  
        }  
    }; 
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_service);  
        startService = (Button) findViewById(R.id.start_service);  
        stopService = (Button) findViewById(R.id.stop_service);  
        bindService = (Button) findViewById(R.id.bind_service);  
        unbindService = (Button) findViewById(R.id.unbind_service);  
        startService.setOnClickListener(view -> {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        });
        stopService.setOnClickListener(view -> {
            Log.d("MyService", "click Stop Service button");
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        });
        bindService.setOnClickListener(view -> {
            Intent bindIntent = new Intent(this, MyService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        });
        unbindService.setOnClickListener(view -> {
            Log.d("MyService", "click Unbind Service button");
            unbindService(connection);
        });
        
        startRemoteService = (Button) findViewById(R.id.start_remote_service);  
        stopRemoteService = (Button) findViewById(R.id.stop_remote_service);  
        bindRemoteService = (Button) findViewById(R.id.bind_remote_service);  
        unbindRemoteService = (Button) findViewById(R.id.unbind_remote_service);  
        startRemoteService.setOnClickListener(view -> {
            Intent startRIntent = new Intent(this, RemoteService.class);
            startService(startRIntent);
        });
        stopRemoteService.setOnClickListener(view -> {
            Log.d("RemoteService", "click Stop Service button");
            Intent stopRIntent = new Intent(this, RemoteService.class);
            stopService(stopRIntent);
        });
        bindRemoteService.setOnClickListener(view -> {
            Intent bindRIntent = new Intent("com.libill.demos.service.RemoteAIDLService");
            bindService(bindRIntent, remoteConnection, BIND_AUTO_CREATE);
        });
        unbindRemoteService.setOnClickListener(view -> {
            Log.d("RemoteService", "click Unbind Service button");
            unbindService(remoteConnection);
        });
    }
  
}  