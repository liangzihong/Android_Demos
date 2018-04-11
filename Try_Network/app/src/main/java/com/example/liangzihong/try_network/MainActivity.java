package com.example.liangzihong.try_network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private HttpURLConnection conn1;
    private Button button1;
    private TextView textView;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();


    }


    private void init(){
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.textview);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useHttpUrlConnection();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useOkHttp();
            }
        });
    }


    /**
     * 用开源库的 OKhttp
     * 具体就是  包里面有  request response对象，通过这些类和对象可以得到很多东西
     */
    private void useOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("https://www.google.com.hk")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String content = response.body().string();
                    showTheContent(content);
                }
                catch (Exception e){e.printStackTrace();}
            }
        }).start();
    }



    /**
     * 用最普通的httpUrlConnection方法去获取
     * 通过 connection.getInputStream获得响应的 输出流
     * 然后 用 bufferedReader读出来
     * 再用主线程显示出来即可
     */
    private void useHttpUrlConnection() {
        Toast.makeText(this, "启动HttpUrlConnection", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.youtube.com/?hl=zh-cn");
                    conn1= (HttpURLConnection)url.openConnection();
                    conn1.setRequestMethod("GET");
                    conn1.setConnectTimeout(15000);
                    conn1.setReadTimeout(15000);

                    InputStream in=conn1.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    String content="";
                    String line="";
                    while((line=reader.readLine())!=null){
                        content=content+line;
                    }
                    reader.close();
                    showTheContent(content);
                }
                catch (Exception e){e.printStackTrace();}
                finally {
                    conn1.disconnect();
                }
            }
        }).start();



    }

    //runonUithread可以回到主线程，再在主线程中更新UI
    private void showTheContent(final String content){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(content);
            }
        });
    }



}
