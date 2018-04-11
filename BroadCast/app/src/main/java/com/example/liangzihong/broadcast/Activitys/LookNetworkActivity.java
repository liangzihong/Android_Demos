package com.example.liangzihong.broadcast.Activitys;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.liangzihong.broadcast.BroadcastReceivers.NetworkBroadcastReceiver;
import com.example.liangzihong.broadcast.R;

/**
 * Created by Liang Zihong on 2018/3/7.
 */

public class LookNetworkActivity extends AppCompatActivity {
    NetworkBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter=new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver=new NetworkBroadcastReceiver();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
