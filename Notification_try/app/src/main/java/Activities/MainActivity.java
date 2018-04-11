package Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liangzihong.notification_try.R;

public class MainActivity extends AppCompatActivity {
    private Button startNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
    }



    private void init(){
        startNotification=(Button)findViewById(R.id.startNotification);
    }



    public void onClick(View view){
        switch(view.getId())
        {
            case R.id.startNotification:
                Intent intent=new Intent(this,ClickNotificationActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

                NotificationManager manager=(NotificationManager)getSystemService
                        (NOTIFICATION_SERVICE);
                Notification notification=new Notification.Builder(this).setContentTitle("Hello")
                        .setContentText("这是一条通知，详情请点开")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setLights(Color.RED,1000,1000)
                        .build();
                manager.notify(1,notification);
                Toast.makeText(this, "通知显示成功", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

    }
}

















