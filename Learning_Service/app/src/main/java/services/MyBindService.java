package services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/3/4.
 */

public class MyBindService extends Service {
    private static final String TAG = "Info";
    @Override
    public void onCreate() {
        Toast.makeText(this, "MyStartService---onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyStartService---onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MyStartService---onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyStartService---onBind", Toast.LENGTH_SHORT).show();
        return new Mybinder();
    }


    public void play(){
        Toast.makeText(this, "MyStartService---play", Toast.LENGTH_SHORT).show();
    }
    public void pause(){
        Toast.makeText(this, "MyStartService---pause", Toast.LENGTH_SHORT).show();
    }
    public void next(){
        Toast.makeText(this, "MyStartService---next", Toast.LENGTH_SHORT).show();
    }
    public void previous(){
        Toast.makeText(this, "MyStartService---previous", Toast.LENGTH_SHORT).show();
    }


    public class Mybinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }
}
