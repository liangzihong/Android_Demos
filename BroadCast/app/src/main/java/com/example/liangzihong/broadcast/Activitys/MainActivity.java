package com.example.liangzihong.broadcast.Activitys;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.liangzihong.broadcast.BroadcastReceivers.BroadcastReceiver_1;
import com.example.liangzihong.broadcast.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private static final String MyAction="MyAction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();

    }

    private void init() {
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            //发送标准广播
            //广播的内容是intent
            case R.id.button1:
                sendStandardBroadcast();
                break;
            case R.id.button2:
                mySendOrderedBroadcast();
                break;

            case R.id.button3:
                DynamicReceive();
                break;
        }
    }


    //发送标准广播
    //广播的内容是intent
    private void sendStandardBroadcast() {
        Intent intent1=new Intent();
        intent1.putExtra("name","ali");  //为intent添加信息
        intent1.setAction(MyAction);    //为广播添加密钥，只有符合这个密钥的才能接收
        sendBroadcast(intent1);
    }


    //发送有序广播
    //有序广播可以 被 前面的广播接收者截断或加以处理
    private void mySendOrderedBroadcast() {
        Intent intent2=new Intent();
        intent2.putExtra("name","Wongtsuiyu");  //为intent添加信息
        intent2.setAction(MyAction);    //为广播添加密钥，只有符合这个密钥的才能接收
        sendOrderedBroadcast(intent2,null);

    }

    /**
     * 动态注册
     */
    private void DynamicReceive() {
        IntentFilter intentFilter=new IntentFilter(MyAction);
        BroadcastReceiver_1 bc1=new BroadcastReceiver_1();
        registerReceiver(bc1,intentFilter);
    }

}
















