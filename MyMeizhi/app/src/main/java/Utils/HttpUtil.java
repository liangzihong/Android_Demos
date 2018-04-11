package Utils;

import java.util.concurrent.TimeUnit;

import Network.FuliModel;
import Network.GankInfo;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liang Zihong on 2018/4/4.
 */

public class HttpUtil {

    public static final String GANMEIZHIAPI="https://gank.io/api/";



    public static Call<FuliModel> httpFuliService(String count,String page){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(6, TimeUnit.SECONDS);


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GANMEIZHIAPI)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankInfo gankService=retrofit.create(GankInfo.class);

        Call<FuliModel> callOfModel=gankService.getFuliModel(count,page);
        return callOfModel;

    }

}
