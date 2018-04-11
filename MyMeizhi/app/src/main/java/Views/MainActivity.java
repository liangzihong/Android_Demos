package Views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.liangzihong.mymeizhi.R;

import java.util.ArrayList;
import java.util.List;


import Adapters.MeizhiAdapter;
import Models.MeizhiModel;
import Presenters.UpdateGanMeizhiPresenter;

public class MainActivity extends AppCompatActivity implements IUpdateGanMeizhiView ,
        SwipeRefreshLayout.OnRefreshListener
{

    private UpdateGanMeizhiPresenter ganmeizhipresenter;

    private RecyclerView content;
    private SwipeRefreshLayout refreshLayout;
    private Toolbar toolbar;

    private MeizhiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content=(RecyclerView)findViewById(R.id.activitymain_recyclerView);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.activitymain_swipeRefreshLayout);
        toolbar=(Toolbar)findViewById(R.id.activitymain_toolbar);
        toolbar.setTitle("干妹纸");
        setSupportActionBar(toolbar);



        //recyclerview
        content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState== RecyclerView.SCROLL_STATE_IDLE)
                    addMoreMeizhi();
            }
        });
        adapter=new MeizhiAdapter(new ArrayList<MeizhiModel>());
        content.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        content.setAdapter(adapter);


        //swiperefreshlayout
        refreshLayout.setOnRefreshListener(this);





        //presenter的定义
        ganmeizhipresenter=new UpdateGanMeizhiPresenter(this);
        startUpdateMeizhi();




    }




    //IUpdateGanMeizhiview的接口

    @Override
    public MeizhiAdapter getMeizhiAdapter() {
        return adapter;
    }

    @Override
    public void startUpdateMeizhi() {
        ganmeizhipresenter.addGanMeizhi();
    }

    @Override
    public void addMoreMeizhi() {
       ganmeizhipresenter.addMoreGanMeizhi();
    }

    //OnRefreshLayout的接口
    @Override
    public void onRefresh() {
        startUpdateMeizhi();
        refreshLayout.setRefreshing(false);
    }



}


















