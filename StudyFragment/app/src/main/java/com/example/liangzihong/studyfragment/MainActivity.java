package com.example.liangzihong.studyfragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //初始化radioGroup
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        //radioGroup建立监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    //静态加载fragment
                    case R.id.first:
                        Intent intent=new Intent(MainActivity.this,StaticFragmentActivity.class);
                        startActivity(intent);
                        break;

                    //动态加载fragment
                    case R.id.second:
                        Toast.makeText(MainActivity.this, "Touch Second", Toast.LENGTH_SHORT).show();
                        SimpleFragment1 sf1=new SimpleFragment1();
                        FragmentManager manager=getFragmentManager();
                        FragmentTransaction beginTransaction=manager.beginTransaction();
                        beginTransaction.add(R.id.frame,sf1);
                        beginTransaction.addToBackStack(null);
                        beginTransaction.commit();
                        break;

                    //fragment的生命周期
                    case R.id.third:

                        break;

                    //传递信息
                    case R.id.forth:
                        Intent intent4=new Intent(MainActivity.this,ActivitySendInfoToFragment.class);
                        startActivity(intent4);
                        break;
                }
            }
        });

    }
}


















