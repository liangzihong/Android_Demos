package com.example.liangzihong.broadcast.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/3/7.
 */

public class NetworkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();

            if(info!=null&&info.isAvailable()){
                Toast.makeText(context, "Network is available", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Network is unavailable", Toast.LENGTH_SHORT).show();

        }
    }
}
