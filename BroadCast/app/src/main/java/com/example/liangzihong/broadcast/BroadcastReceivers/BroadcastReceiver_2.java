package com.example.liangzihong.broadcast.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Liang Zihong on 2018/3/1.
 */

public class BroadcastReceiver_2 extends BroadcastReceiver {
    private static final String TAG = "BcR_2's TAG ";

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
        Log.i(TAG, "onReceive: I am BroadcastReceiver_2, your name is "+name);

        //如果是 有序广播，我会 通过 bundle 再传一个名字进去
        Bundle bundle=new Bundle();
        bundle.putString("name","Moon");
        setResultExtras(bundle);


    }
}
