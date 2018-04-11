package Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liangzihong.mymusicplayer.R;

import java.util.ArrayList;
import java.util.List;

import Models.Music;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String > songList;
    private int PlayingIndex;
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        applyPermission();

        init();



    }



    private void init(){
        PlayingIndex=-1;
        player=new MediaPlayer();
        //listview的初始化
        listView=(ListView)findViewById(R.id.listView);
        songList=Music.getSongs();
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,songList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }




    public void onClick(View view){
        if(PlayingIndex==-1)
            return;
        switch(view.getId()){
            case R.id.display_button:
                if(player.isPlaying()==false)
                    player.start();
                break;
            case R.id.next_button:
                player.stop();
                player.reset();
                PlayingIndex=(PlayingIndex+1)%songList.size();
                startTheSong(PlayingIndex);
                break;
            case R.id.pause_button:
                if(player.isPlaying())
                    player.pause();
                break;
            case R.id.previous_button:
                player.stop();
                player.reset();
                PlayingIndex=(PlayingIndex-1)%songList.size();
                startTheSong(PlayingIndex);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(PlayingIndex==position)
                return;
            else{
                PlayingIndex=position;
                player.stop();
                player.reset();
                startTheSong(PlayingIndex);
            }
    }




    //传入第几首歌，来播放音乐
    public void startTheSong(int index){
        try{
           //设置player的文件（String），并且处于准备状态。
            player.setDataSource(Music.FOLDERNAME+songList.get(index));
            player.prepare();
            player.start();
        }
        catch(Exception e){e.printStackTrace();}
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player!=null){
            player.stop();
            player.release();
        }
    }



    /**
     * 申请权限
     */
    private void applyPermission(){
        //如果没有得到 读写sd卡的权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
    }


    /**
     * 接收反馈
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case 100:
                //如果得到权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    return;
                else
                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
        }
    }



}
