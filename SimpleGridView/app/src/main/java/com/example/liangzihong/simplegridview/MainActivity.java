package com.example.liangzihong.simplegridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SimpleAdapter adapter;

    //这里放置icon的id，因为我没有那么多图片，所以也就不搞了
    private int[]icon={R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
    //这里放置跟icon所对应的字符串的名字，用于显示桌面效果
    private String[]iconName={"没围墙","有围墙"};
    private GridView gridview;
    private List<Map<String,Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gridview初始化
        gridview=(GridView) findViewById(R.id.gv);

        data=new ArrayList<>();
        //data的初始化
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("picture",icon[i]);
            map.put("text",iconName[i]);
            data.add(map);
        }

        //adapter的初始化
        adapter=new SimpleAdapter(this,data,R.layout.style,
                new String[]{"picture","text"},new int[]{R.id.image,R.id.text});

        //setAdapter
        gridview.setAdapter(adapter);

        //监听事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,"我是"+iconName[position],Toast.LENGTH_SHORT).show();
            }
        });




    }
}
















