package com.example.liangzihong.lbstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import models.Place;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private LocationClient client;
    private MapView map;
    private BaiduMap mapController;
    private boolean FirstTime=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //对地图进行初始化操作
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.main_layout);
        applyPermissions();
        init();


        //client开始定位
        requestLocation();
    }


    /**
     * 初始化  textview和client和map和controller
     */
    private void init(){
        map=(MapView)findViewById(R.id.map);
        textView=(TextView)findViewById(R.id.textView);
        client=new LocationClient(this);
        //给client设置一个  回调函数的监听器
        client.registerLocationListener(new LocationListener());
        mapController=map.getMap();
    }



    //通过调用这个方法 令client开始定位
    private void requestLocation(){
        initLocation();
        client.start();
    }

    /**
     * 设置  client的属性，设置成 10s更新一次，或5s更新一次
     * client.setLocOption(option)可以设置很多相应的属性
     */
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        //设置多少时间间隔，定位一次
        option.setScanSpan(5000);

        //设置 定位的方式 是网络方式还是GPS方式
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        //设置 是否需要更详细的地址
        option.setIsNeedAddress(true);
        client.setLocOption(option);
    }



    /**
     * 用于第一次初始化 地图
     * 使地图移动到我所在的位置
     * 并且把缩放设置成12.5
     *这里初始化   controller
     *
     * 利用   MapStatusUpdate update=MapStatusUpdate.Factory.xxxx
     * 然后   mapController.animateMapStatus(update)即可
     */
    private void NavigateToMyPosition(double latitude,double longitude){
        if(FirstTime){

            MapStatusUpdate update= MapStatusUpdateFactory.zoomTo(13.5f);
            mapController.animateMapStatus(update);

            LatLng position=new LatLng(latitude,longitude);
            update=MapStatusUpdateFactory.newLatLng(position);



            mapController.animateMapStatus(update);

        }
        FirstTime=false;
    }



    /**
     * 展现自己在地图上的位置
     * 利用 MyLocationData.Builder来构造  MyLocationData
     * 和 controller.setMyLocationData(data)
     */
    private void ShowMe(double latitude,double longitude){
        //设置 我的位置在地图上显示
        mapController.setMyLocationEnabled(true);

        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(latitude);
        builder.longitude(longitude);


        MyLocationData data=builder.build();
        mapController.setMyLocationData(data);
    }



    //定义  这个 监听抽象类，使其监听  client的举动
    public class LocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String latitude = bdLocation.getLatitude() + "";
                    String longitude = bdLocation.getLongitude() + "";

                    ShowMe(bdLocation.getLatitude(), bdLocation.getLongitude());

                    String text = "";
                    text = text + "纬度：" + latitude + "\n";
                    text = text + "经度：" + longitude + "\n";

                    if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                        text = text + "定位方式：" + "GPS";
                        NavigateToMyPosition(bdLocation.getLatitude(), bdLocation.getLongitude());
                    } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                        text = text + "定位方式：" + "Network";
                        NavigateToMyPosition(bdLocation.getLatitude(), bdLocation.getLongitude());
                    }


                    String country=bdLocation.getCountry();
                    String province=bdLocation.getProvince();
                    String city=bdLocation.getCity();
                    String district=bdLocation.getDistrict();
                    String street=bdLocation.getStreet();
                    Place place=new Place(country,province,city,district,street);


                    textView.setText(place.toString()+'\n'+text);
                }
            });
        }
    }















    //申请权限
    private void applyPermissions(){
        List<String> permissonList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            permissonList.add((Manifest.permission.READ_PHONE_STATE));
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            permissonList.add((Manifest.permission.READ_PHONE_STATE));
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            permissonList.add((Manifest.permission.WRITE_EXTERNAL_STORAGE));
        if(permissonList.size()!=0){
            String[] arr=(String[])permissonList.toArray();
            requestPermissions(arr,100);
        }

    }

    /**
     * 回应权限
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 100:
                if(grantResults.length>0){
                    for(int a:grantResults){
                        if(a!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                }
                else{
                    Toast.makeText(this, "发生错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }





    /**
     * activity回复时调用
     * 让 地图 mapview 也恢复 定位
     */
    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();

    }

    /**
     * activity暂停时调用
     * 让 地图 mapview 也停止 定位
     */
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    /**
     * activity销毁时调用
     * 让 地图 mapview 也停止 定位
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapController.setMyLocationEnabled(false);

        map.onDestroy();
    }

}




















