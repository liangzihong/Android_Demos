package com.example.liangzihong.helloworld;

//import android.content.Intent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView AutoView;
    private String[] rec={"Beijing1,Beijing2,Beijing3,Shanghai1,Shanghai2,Shanghai3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 这是一个自动匹配编辑框，类似于百度那种打两个字就有匹配的选项那种
         * 第一步:初始化
         * 第二步：需要一个适配器
         * 第三步：初始化匹配器
         * 第四步：setAdapter
         */
        /*AutoView=(AutoCompleteTextView)findViewById(R.id.auto);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,rec);
        AutoView.setAdapter(adapter);*/
        


    }
}






















