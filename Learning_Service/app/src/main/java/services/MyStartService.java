package services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/3/4.
 */

public class MyStartService extends Service {
    private static final String TAG = "Info";
    @Override
    public void onCreate() {
        Toast.makeText(this, "MyStartService---onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MyStartService---onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyStartService---onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyStartService---onBind", Toast.LENGTH_SHORT).show();
        return null;
    }
}
