package com.example.liangzihong.use_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Models.APIInterface;
import Models.User;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(TAG, "onCreate: 主线程为"+Thread.currentThread().getName());

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIInterface apiinterface=retrofit.create(APIInterface.class);
                Call<User> model= apiinterface.getUser("Guolei1130");
                model.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        Log.i(TAG, "onResponse: 在得到响应时线程为"+Thread.currentThread().getName());

                        Log.i(TAG, "onResponse: "+response.body().getLogin());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });




    }


}
