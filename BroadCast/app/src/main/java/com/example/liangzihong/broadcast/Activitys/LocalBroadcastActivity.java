package com.example.liangzihong.broadcast.Activitys;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.example.liangzihong.broadcast.BroadcastReceivers.BroadcastReceiver_1;
import com.example.liangzihong.broadcast.BroadcastReceivers.BroadcastReceiver_2;
import com.example.liangzihong.broadcast.BroadcastReceivers.NetworkBroadcastReceiver;
import com.example.liangzihong.broadcast.R;

/**
 * Created by Liang Zihong on 2018/3/7.
 */

public class LocalBroadcastActivity extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private BroadcastReceiver_2 receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=LocalBroadcastManager.getInstance(this);
        IntentFilter filter=new IntentFilter();
        filter.addAction("MyAction");
        receiver=new BroadcastReceiver_2();
        manager.registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);

    }
}
