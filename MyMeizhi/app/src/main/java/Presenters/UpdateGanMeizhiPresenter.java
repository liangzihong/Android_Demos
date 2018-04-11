package Presenters;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.MeizhiAdapter;
import Models.MeizhiModel;
import Network.FuliModel;
import Utils.HttpUtil;
import Views.IUpdateGanMeizhiView;
import Views.MainActivity;
import Views.WrongActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import Network.FuliModel.Result;
/**
 * Created by Liang Zihong on 2018/4/4.
 */

public class UpdateGanMeizhiPresenter implements IUpdateGanMeizhiPresenter {


    private IUpdateGanMeizhiView view;
    private static int THISCOUNT=5;
    private static int THISPAGE=2;
    private static MeizhiAdapter adapter;


    public UpdateGanMeizhiPresenter(IUpdateGanMeizhiView iview){
        view=iview;
        adapter=view.getMeizhiAdapter();
    }




    @Override
    public void addGanMeizhi() {


        Call<FuliModel> call= HttpUtil.httpFuliService("15","1");

        call.enqueue(new Callback<FuliModel>() {
            @Override
            public void onResponse(Call<FuliModel> call, Response<FuliModel> response) {
                FuliModel model=response.body();

                if(model.isError()==false){

                    Log.i("info", "onResponse: thread in "+ Thread.currentThread().getName());
                    List<Result> results=model.getResults();
                    List<MeizhiModel> tmp=new ArrayList<>();
                    for(Result result : results)
                        tmp.add(new MeizhiModel(result.getUrl(),result.getPublishedAt()));
                    adapter.addFirst(tmp);
                }
                else
                    Toast.makeText((MainActivity)view, "加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FuliModel> call, Throwable t) {
                WrongActivity.startWrongActivity((MainActivity)view);
            }
        });



    }


    @Override
    public void addMoreGanMeizhi() {
        THISPAGE+=1;

        Call<FuliModel> call=HttpUtil.httpFuliService(String.valueOf(THISCOUNT),String.valueOf(THISPAGE));

        call.enqueue(new Callback<FuliModel>() {
            @Override
            public void onResponse(Call<FuliModel> call, Response<FuliModel> response) {
                FuliModel model=response.body();
                if(model.isError()==false){
                    List<Result> lists=model.getResults();
                    for(Result result: lists)
                        adapter.add(new MeizhiModel(result.getUrl(),result.getPublishedAt()));

                }
                else
                    Toast.makeText((MainActivity)view, "加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FuliModel> call, Throwable t) {

            }
        });
    }
}

















