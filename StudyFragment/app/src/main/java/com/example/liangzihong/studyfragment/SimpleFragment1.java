package com.example.liangzihong.studyfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Liang Zihong on 2018/2/7.
 */

public class SimpleFragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //将布局文件转化为view并返回。
        View view=inflater.inflate(R.layout.simplefragment1_layout,container,false);
        return view;
    }
}






















