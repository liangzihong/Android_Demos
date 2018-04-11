package com.example.liangzihong.learning_service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import services.MyBindService;
import services.MyStartService;

public class MainActivity extends AppCompatActivity {
    private Intent intent1;
    private Intent intent2;
    private MyBindService service;

    private ServiceConnection conn=new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            service=((MyBindService.Mybinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

    }


    public void onClick(View view){
        switch(view.getId()){
            case R.id.start:
                intent1=new Intent(MainActivity.this, MyStartService.class);
                startService(intent1);
                break;
            case R.id.stop:
                stopService(intent1);
                break;
            case R.id.bind:
                intent2=new Intent(MainActivity.this, MyBindService.class);
                bindService(intent2,conn,Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(conn);
                break;
            case R.id.play:
                service.play();
                break;
            case R.id.pause:
                service.pause();
                break;
            case R.id.previous:
                service.previous();
                break;
            case R.id.next:
                service.next();
                break;
        }
    }

}
