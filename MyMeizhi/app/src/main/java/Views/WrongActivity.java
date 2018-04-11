package Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.liangzihong.mymeizhi.R;

/**
 * Created by Liang Zihong on 2018/4/6.
 */

public class WrongActivity extends AppCompatActivity {

    private Button button;
    public static void startWrongActivity(Activity startActivity){
        Intent intent=new Intent(startActivity,WrongActivity.class);
        startActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrong_layout);

        button=(Button)findViewById(R.id.retry_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
