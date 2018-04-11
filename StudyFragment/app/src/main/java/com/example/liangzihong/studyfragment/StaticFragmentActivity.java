package com.example.liangzihong.studyfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liang Zihong on 2018/2/7.
 */

public class StaticFragmentActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private Button Back;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.staticfragment_layout);
        button=(Button)findViewById(R.id.sf2_button);
        textView=(TextView)findViewById(R.id.sf2_textview);
        Back=(Button)findViewById(R.id.sf_button);

        //设置button的监听，按下按钮就改变文字
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Bye Bye!");
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public String toString() {
        return "I am StaticFragmentActivity";
    }
}














