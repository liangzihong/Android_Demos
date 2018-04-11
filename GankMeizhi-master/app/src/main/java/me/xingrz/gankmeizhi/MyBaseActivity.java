package me.xingrz.gankmeizhi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/4/2.
 */

public class MyBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Toast.makeText(this, "this is "+this.getClass().getName(), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
    }
}
