package Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liangzihong.use_camera.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button takePhoto;
    private Button chooseFromAlbum;
    private ImageView picture;
    private EditText editText;
    private Uri imageUri;
    private File file;

    public static final int CHOOSE_FROM_ALBUM=1;
    public static final int TAKE_PHOTO=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();



    }

    private void init(){
        takePhoto=(Button)findViewById(R.id.button);
        chooseFromAlbum=(Button)findViewById(R.id.FromAlbum);
        picture=(ImageView)findViewById(R.id.imageView);
        editText=(EditText)findViewById(R.id.editText);
        editText.setText(null);
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                goTakingPhoto();
                break;


            case R.id.FromAlbum:
                applyPermission();
                break;




            case R.id.clear:

                picture.setImageBitmap(null);
                editText.setText(null);
                break;
        }
    }



    /**
     * 拍照 启动函数
     */
    private void goTakingPhoto(){
        String name=editText.getText()+".jpg";
        if(".jpg".equals(name)){
            Toast.makeText(this, "名字不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //创建  图片将要存放的位置
        file=new File("/sdcard/Pictures/",name);
        try{

            //存在则不行，不存在则可以，并新建这个文件
            if(file.exists()){
                Toast.makeText(this, "该名字已经存在", Toast.LENGTH_SHORT).show();
                return;
            }
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }


        if(Build.VERSION.SDK_INT>=24)
            imageUri= FileProvider.getUriForFile(this,"com.example.Use_Camera.fileprovider",file);
        else
            imageUri=Uri.fromFile(file);

        //启动相机
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
        return;
    }





    /**
     * 申请权限
     */
    private void applyPermission(){
        //如果没有得到 读写sd卡的权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        else
            openAlbum();
    }


    /**
     * 这里反馈是否得到权限，如果得到权限就继续openAlbum，得不到就会断掉
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 100:
                //如果得到权限
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    openAlbum();
                else
                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 打开相册
     */
    private void openAlbum(){
        Intent AlbumIntent=new Intent("android.intent.action.GET_CONTENT");
        AlbumIntent.setType("image/*");
        startActivityForResult(AlbumIntent,CHOOSE_FROM_ALBUM);
    }


    /**
     * 接收 照完相 或者选择完照片 的 回归信号
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    //将拍摄的照片显示出来
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }
                    catch (Exception e){e.printStackTrace();}
                }
                break;

            case CHOOSE_FROM_ALBUM:
                if(resultCode==RESULT_OK)
                    //判断手机的android版本，高低版本用不同的函数方法去解决
                    if(Build.VERSION.SDK_INT>=19)
                        handleImageOnKitKat(data);
                    else
                        handleImageBeforeKitKat(data);
                break;


            default:
                break;
        }



    }



    @TargetApi(19)
    public void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];   //解析出数字格式的ID
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath
                        (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

                Toast.makeText(this, "第一个", Toast.LENGTH_SHORT).show();
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("" +
                        "content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
                Toast.makeText(this, "第二个", Toast.LENGTH_SHORT).show();
            }
        }
            else if("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
                Toast.makeText(this, "第三个", Toast.LENGTH_SHORT).show();
            }
            else if("file".equalsIgnoreCase(uri.getScheme())) {
                Toast.makeText(this, "第四个", Toast.LENGTH_SHORT).show();
                imagePath = uri.getPath();
            }


        displayImage(imagePath);
    }





    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);

        Toast.makeText(this, "第0个", Toast.LENGTH_SHORT).show();
    }






    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);

        if(cursor!=null){
            if(cursor.moveToFirst())
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();

        }
        return path;
    }



    /**
     * 通过 图片的位置，可以通过  BitmapFactory 解析得到 bitmap，然后imageView就可以直接setImageBitmap
     * @param imagePath
     */
    private void displayImage(String imagePath){
        if(imagePath!=null) {
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }
        else
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
    }

}


















