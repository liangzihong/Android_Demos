package com.example.liangzihong.simplelistview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ListView listview;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter sim_adapter;
    private List<Map<String,Object>> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview1);


        listview=(ListView)findViewById(R.id.listview);

        /**
         * 第一种方式，利用ArrayAdapter
         */
        /*String[]source={"这是第一个","这是第二个","这是第三个","这是第四个"};
        arr_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,source);
        listview.setAdapter(arr_adapter);*/


        /**
         * 第二种方式，利用SimpleAdapter和自己写的布局文件来搞
         */

        String[] from={"pic","tv1","tv2","tv3"};
        int[]to={R.id.imageView,R.id.tv1,R.id.tv2,R.id.tv3};
        for(int i=0;i<10;i++){
            Map<String, Object> map=new HashMap<>();
            map.put(from[0],R.mipmap.ic_launcher);
            map.put(from[1],"名字为"+i);
            map.put(from[2],"价格为"+i);
            map.put(from[3],"好评为"+i);
            data.add(map);
        }

        sim_adapter=new SimpleAdapter(this,data,R.layout.style,from,to);
        listview.setAdapter(sim_adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> obj= (Map<String, Object>) listview.getItemAtPosition(position);
                String tv1= (String) obj.get("tv1");
                Toast.makeText(MainActivity.this, tv1, Toast.LENGTH_SHORT).show();
            }
        });



        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState){

                    //用力滑了一下，然后手指离开，而屏幕仍然在走
                    //并且有一个说不过去的状态，就是当在最底部时继续滑动，会新增选项，这个也算是手指离开，而屏幕仍在走
                    case SCROLL_STATE_FLING:
                        /**
                         * 此时要试图增加条目
                         */
                        Map<String,Object> map=new HashMap<String,Object>();
                        map.put("pic",R.mipmap.ic_launcher);
                        map.put("tv1","新增项");
                        map.put("tv2","新增项");
                        map.put("tv3","新增项");
                        data.add(map);

                        //一定要有这一句话，不然不行
                        //记住这个就好，当data有更更新，要调用   sim_adapter的notifyDataSetChanged（）;
                        sim_adapter.notifyDataSetChanged();

                        break;
                    //滑动停止的一瞬间，就是这个状态
                    case SCROLL_STATE_IDLE:

                        break;
                    //你的手指在屏幕上，并且在滑动
                    case SCROLL_STATE_TOUCH_SCROLL:

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
}










