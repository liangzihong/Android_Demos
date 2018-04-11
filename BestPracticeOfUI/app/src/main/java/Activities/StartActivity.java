package Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liangzihong.bestpracticeofui.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.MessageAdapter;
import MODEL.Message;

/**
 * Created by Liang Zihong on 2018/3/8.
 */

public class StartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText inputEditText;
    private Button sentButton;
    private List<Message> arr;
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatui_layout);
        init();
    }



    private void init(){
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        inputEditText=(EditText)findViewById(R.id.inputEditText);
        sentButton=(Button)findViewById(R.id.sentButton);

        /**
         * RecyclerView的前提，需要一个LinerLayoutManager
         * 然后recyclerView要 setLayoutManager，然后setAdapter
         */
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arr=new ArrayList<>();
        adapter=new MessageAdapter(arr);
        recyclerView.setAdapter(adapter);


        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEditText.getText()==null)
                    Toast.makeText(StartActivity.this, "消息为空", Toast.LENGTH_SHORT).show();
                sentMessage(inputEditText.getText()+"");
            }
        });

    }





    private void sentMessage(String content){
        Message msg=new Message(content, Message.Companion.getIs_Sent());
        arr.add(msg);
        Message ret=new Message(content+"是什么\n"+"你在说什么?", Message.Companion.getIs_Receive());
        arr.add(ret);
        //当有新消息时，刷新 recyclerView的显示
        adapter.notifyItemInserted(arr.size()-1);

        //将recyclerView定位到最后一行。
        recyclerView.scrollToPosition(arr.size()-1);
        inputEditText.setText(null);

    }

}


















