package com.example.liangzihong.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //在SDK23以上，一些危险权限再也不能在清单文件中声明即可，而需要  用函数申请权限
        // requestPermissions(new String[]{你需要申请的权限},一个code);
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},0);

        //addContacts();
        readContacts();


    }

    /**
     * 读取联系人，在log中显示
     */
    private void readContacts(){

        //ContentResolver是与各个contentProvider交流的类
        //面对android写好的contentProvider，
        //可以利用 ContentResolver.query,insert,update,delete等接口直接搞
        ContentResolver contentResolver=getContentResolver();


        //貌似contentProvider会提供对应的Uri出来
        Cursor cursor=contentResolver.query(Phone.CONTENT_URI,null,
                null,null,null);

        //ContactsContract.Contacts似乎是系统的contentProvider,它提供各个表的URi,以及一些表的列
        //Log.i(TAG, "onCreate: 现在有"+cursor.getCount()+"个人");
        Toast.makeText(this, "现在有"+cursor.getCount()+"个人", Toast.LENGTH_SHORT).show();
        if(cursor!=null){
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex(Contacts._ID));
                Toast.makeText(this, "display_name= "+cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "PhoneNumber is = "+cursor.getString(cursor.getColumnIndex(Phone.NUMBER)), Toast.LENGTH_SHORT).show();

            }
        }
        cursor.close();


    }


    /**
     * 添加联系人
     */
    private void addContacts() {
        ContentResolver cr=getContentResolver();
        ContentValues values=new ContentValues();

        //插入一个空的 contentvalues 可以在联系人中得到空的一行

        //RawContacts怀疑相当于联系人数据库，content_uri明显就是指向content表的uri
        //所以向content表中指出要一行空行，并得到指向这个空行的uri
        //然后要那一行空行的id，通过  辅助类 ContentUris得到uri后面的id
        //然后对那个空行做文章
        Uri uri=cr.insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long raw_contact_id= ContentUris.parseId(uri);   //相当于得到多出来那一行的id


        //添加人名
        values.clear();

        //以下两行是固定动作
        values.put(StructuredName.RAW_CONTACT_ID,raw_contact_id);
        values.put(StructuredName.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
        values.put(StructuredName.DISPLAY_NAME,"Bosco");
        cr.insert(ContactsContract.Data.CONTENT_URI,values);

        //添加电话号码
        values.clear();

        values.put(Phone.RAW_CONTACT_ID,raw_contact_id);
        values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER,"678910");
        cr.insert(ContactsContract.Data.CONTENT_URI,values);
    }

}
