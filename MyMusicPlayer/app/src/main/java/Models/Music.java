package Models;

import android.text.LoginFilter;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang Zihong on 2018/3/14.
 */

public class Music {
    public static final String FOLDERNAME="/sdcard/music/";


    public static List<String> getSongs(){
        return getAllSongsFromFolder();
    }


    private static List<String> getAllSongsFromFolder(){
        File folder=new File(FOLDERNAME);
        File [] Musicfiles;
        List<String> ret=new ArrayList<>();
        try{
            if(folder.isDirectory()==false)
                Log.i("fuck", "getAllSongsFromFolder: 这个不是一个文件夹");
            else{
                Musicfiles=folder.listFiles();
                if(Musicfiles!=null)
                    for(File tmpFile:Musicfiles){
                        ret.add(tmpFile.getName());
                        Log.i("fuck", "getAllSongsFromFolder: "+tmpFile.getName());
                    }
                else
                    Log.i("fuck", "getAllSongsFromFolder: wrong");
            }


        }
        catch(Exception e){e.printStackTrace();}
        return ret;
    }

}
