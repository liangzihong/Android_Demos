package com.example.liangzihong.studyfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/2/7.
 */

public class SimpleFragment2 extends Fragment {
    private TextView textView;


    public interface ConverInfo{
        public void getInfo(String info);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //将布局文件转化为view
        View view=inflater.inflate(R.layout.simplefragment2_layout,container,false);

        Bundle bundle=getArguments();
        if(bundle!=null) {
            ConverInfo Listener=(ConverInfo)getActivity();
            String InfoFromActivity = (String) bundle.get("name");
            textView = (TextView) view.findViewById(R.id.sf2_textview);
            textView.setText("你的名字是：" + InfoFromActivity);
            Listener.getInfo("你好，"+InfoFromActivity);
        }
        Toast.makeText(getActivity(), getActivity().toString(), Toast.LENGTH_SHORT).show();

        return view;
    }
}
















