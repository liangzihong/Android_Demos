package com.example.liangzihong.rxjavaandretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        button=(Button)findViewById(R.id.begin);
        textView=(TextView)findViewById(R.id.textView);

        button.setOnClickListener(this);


    }



    private void startInternet(){

    }






    @Override
    public void onClick(View v) {
        startInternet();
    }
}








