package com.example.liangzihong.broadcast.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Liang Zihong on 2018/3/1.
 */

public class BroadcastReceiver_1 extends BroadcastReceiver {
    private static final String TAG = "BcR_1's TAG ";

    /**
     * 广播接收者 要重写 OnReceive函数
     * 这个函数 context就是发送广播的上下文，intent就是广播中的内容
     * 而intent又可以传递信息。
     *
     * 当这个Receiver接收到信息，就会回掉这个 onReceive
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String name=intent.getStringExtra("name");
        Log.i(TAG, "onReceive: I am BroadcastReceiver_1, your name is "+name);

        Bundle bundle=getResultExtras(true);
        String name2=bundle.getString("name");
        if(name2!=null){
            Log.i(TAG, "onReceive: I am 1, and I receive the extra info from 2");
        }
    }
}
