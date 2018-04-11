package com.example.liangzihong.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liang Zihong on 2018/2/5.
 */

public class SActivity extends AppCompatActivity {
    private Button bt1;
    private final int STFCode=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity);

        bt1= (Button) findViewById(R.id.Sbt1);

        /**
         * 当是第二种方法时
         * 生成一个Intent，在上面利用putExtra放数据，然后通过setResult方法丢一个代号和intent进去
         */


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("data","I come from SActivity");
                setResult(STFCode,intent);
                finish();
            }
        });

    }


}
