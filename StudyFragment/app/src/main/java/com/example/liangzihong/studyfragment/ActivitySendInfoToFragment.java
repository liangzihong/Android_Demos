package com.example.liangzihong.studyfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/2/8.
 */

/**
 *动态传递数据
 */

public class ActivitySendInfoToFragment extends AppCompatActivity implements SimpleFragment2.ConverInfo {
    private Button button;
    private TextView textView;

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysendinfotofragment_layout);

        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=(String)textView.getText();
                Bundle bundle=new Bundle();
                bundle.putString("name",name);

                SimpleFragment2 sf2=new SimpleFragment2();
                sf2.setArguments(bundle);
                //这样fragment就带有了bundle的数据
                //所以要在simpleFragment2那里处理消息。
                FragmentManager manager=getFragmentManager();
                FragmentTransaction beginTransaction=manager.beginTransaction();
                beginTransaction.add(R.id.astif_layout,sf2,"SimpleFragment2");  //这样为fragment添加tag，那又有什么用？
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        });

    }










    private void init(){
        button=(Button)findViewById(R.id.asitf_button);
        textView=(TextView)findViewById(R.id.asitf_textview);

    }

    @Override
    public String toString() {
        return "I am ActivitySendInfoToFragment";
    }

    @Override
    public void getInfo(String info) {
        Toast.makeText(this, "I have received the info: "+info, Toast.LENGTH_SHORT).show();
    }
}














