package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.liangzihong.mymeizhi.R;

import java.util.List;

import Models.MeizhiModel;
import Views.PictureActivity;

/**
 * Created by Liang Zihong on 2018/4/3.
 */

public class MeizhiAdapter extends RecyclerView.Adapter<MeizhiAdapter.MyViewHolder> {


    private List<MeizhiModel> modellist;
    private Context context;

    public MeizhiAdapter(List<MeizhiModel> arr) {
        modellist = arr;
    }


    public void add(MeizhiModel model) {
        modellist.add(model);
        notifyItemInserted(modellist.size() - 1);
    }

    public void addFirst(List<MeizhiModel> tmpList) {
        modellist.addAll(0, tmpList);
        notifyItemRangeInserted(0, tmpList.size());
    }


    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ganmeizhi_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        MeizhiModel model = modellist.get(position);
        holder.pic.setTag(R.id.GlideTag, model.getUrl());


        if ((holder.pic.getTag(R.id.GlideTag) + "").equals(model.getUrl())) {
            Glide.with(context).load(model.getUrl()).into(holder.pic);
        }

        holder.statement.setText(model.getText());

    }


    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView pic;
        private TextView statement;


        public MyViewHolder(View itemView) {
            super(itemView);

            pic = (ImageView) itemView.findViewById(R.id.ganmeizhi_imageView);
            statement = (TextView) itemView.findViewById(R.id.ganmeizhi_textView);



            //TODO:
            //顺便设置点击事件
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PictureActivity.startPictureActivity(context,modellist.get(getAdapterPosition()).getUrl());
                }
            });
        }
    }
}