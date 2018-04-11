package com.example.liangzihong.toolbar;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout_layout);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawer=(DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        /*ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }*/


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;

            case R.id.firstItem:
                Toast.makeText(this, "FirstItem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.secondItem:
                Toast.makeText(this, "secondItem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.thirdItem:
                Toast.makeText(this, "thirdItem", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
