package com.example.liangzihong.real_file_simple_operation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        init();

    }

    private void init() {
        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String content = editText.getText() + "";
                    String show=null;
                    writeFiles(content);
                    show=readFiles();
                    textView.setText(show);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 写文件，把字符串变成字节写入文件中
     * 利用   string.getBytes()
     * @param content
     * @throws Exception
     */
    private void writeFiles(String content) throws Exception{

        //openFileOutput(文件名，模式)直接返回输入流。  直接fop.write(string.getBytes())
        FileOutputStream outputStream=openFileOutput("store.txt",MODE_PRIVATE);
        outputStream.write(content.getBytes());
        outputStream.close();
    }


    /**
     * 读文件，把字节变为字符串
     * 利用   bytearrayoutputStream这个类
     * baos.write(byte [],start,cnt)
     * baos.toString()
     * @return
     * @throws Exception
     */
    private String readFiles() throws Exception {

        //利用到新知识  bytearrayoutputstream，可以直接 toString将字节变成String
        FileInputStream inputStream=openFileInput("store.txt");
        ByteArrayOutputStream baos=new ByteArrayOutputStream();

        byte []arr=new byte[1024];
        int len=0;
        while((len=inputStream.read(arr,0,1024))!=-1){
            baos.write(arr,0,len);
        }

        String ret= baos.toString();
        baos.close();
        inputStream.close();

        return ret;
    }




























}
