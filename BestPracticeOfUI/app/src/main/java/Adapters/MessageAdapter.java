package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangzihong.bestpracticeofui.R;

import java.util.List;

import MODEL.Message;

/**
 * Created by Liang Zihong on 2018/3/8.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> ListOfMessage;

    public MessageAdapter(List<Message> list){
        ListOfMessage=list;
    }


    /**
     * 要根据view 返回一个viewHolder，如果要设置事件响应，就在这里响应。
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.receiveandsentmessage_item,
                parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        final TextView text1=(TextView)view.findViewById(R.id.receive_TextView);
        final TextView text2=(TextView)view.findViewById(R.id.sent_TextView);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), text1.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), text2.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });


        return viewHolder;
    }


    /**
     * 根据 position 取得 Model的对象，根据对象的属性，对 holder的UI控件赋上响应的属性，如 setText，setImageResource等，
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=ListOfMessage.get(position);

        if(message.getState()== Message.Companion.getIs_Receive()){
            holder.Receive_layout.setVisibility(View.VISIBLE);
            holder.Sent_layout.setVisibility(View.GONE);
            holder.Receive_textview.setText(message.getContent());
        }
        else{
            holder.Sent_layout.setVisibility(View.VISIBLE);
            holder.Receive_layout.setVisibility(View.GONE);
            holder.Sent_textview.setText(message.getContent());
        }


    }

    /**
     * 返回数组的大小
     * @return
     */
    @Override
    public int getItemCount() {
        return ListOfMessage.size();
    }





    /**
     * recyclerView 内容是什么，它的变量就有什么，构造函数 就要把它的变量全部赋值掉。
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout Receive_layout;
        private TextView Receive_textview;
        private LinearLayout Sent_layout;
        private TextView Sent_textview;
        public ViewHolder(View itemView) {
            super(itemView);

            Receive_layout=(LinearLayout)itemView.findViewById(R.id.receive_layout);
            Receive_textview=(TextView)itemView.findViewById(R.id.receive_TextView);
            Sent_layout=(LinearLayout)itemView.findViewById(R.id.sent_layout);
            Sent_textview=(TextView)itemView.findViewById(R.id.sent_TextView);

        }
    }
}
