package com.example.liangzihong.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liang Zihong on 2018/2/5.
 */

public class FActivity extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private TextView tv1;
    private final int FTSCode=1;
    private static final String TAG = "FActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factivity);


        bt1= (Button) findViewById(R.id.button1);
        bt2= (Button) findViewById(R.id.button2);
        tv1= (TextView) findViewById(R.id.tv);

        /**
         * 第一种方法，创立Intent，然后直接用startActivity(Intent)进行跳转
         */
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FActivity.this,SActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 第二种方法，利用startActivityForResult(Intent,int)
         * 利用onActivityResult接收从另一个Activity传过来的信息
         */
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FActivity.this,SActivity.class);
                startActivityForResult(intent,FTSCode);
            }
        });


    }

    /*
     * FActivity用onActivityResult来接受从里一个页面返回的东西
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==FTSCode&&resultCode==2){
            String text=data.getStringExtra("data");
            tv1.setText(text);
        }
    }
}




















