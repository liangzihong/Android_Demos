package Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.liangzihong.mymeizhi.R;

/**
 * Created by Liang Zihong on 2018/4/7.
 */

public class PictureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    public static void startPictureActivity(Context context,String UrlOfPic){
        Intent intent=new Intent(context,PictureActivity.class);
        intent.putExtra("Url",UrlOfPic);
        context.startActivity(intent);
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watchpicture_layout);
        image=(ImageView)findViewById(R.id.pictureWatcher);
        image.setOnClickListener(this);

        Intent intent=getIntent();
        String UrlOfPic=intent.getStringExtra("Url");

        Glide.with(this).load(UrlOfPic).into(image);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pictureWatcher:
                finish();
        }
    }
}
