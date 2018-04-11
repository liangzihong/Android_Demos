package com.example.liangzihong.use_rxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody


class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView) as TextView

        Test2()

    }


    //try Rxjava 2

    /**
     * 调用顺序为 create--->map--->doOnNext--->accept
     */
    private fun Test2() {
        Observable.create(ObservableOnSubscribe<Response> { emitter ->
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
            val response = client.newCall(request).execute()
            Log.i(TAG, "subscribe: 发射器 create")
            emitter.onNext(response)
        }).subscribeOn(Schedulers.io())
                .map { response ->
                    val body = response.body()
                    Log.i(TAG, "apply: 发射器 map")
                    body?.string()
                }
                //.observeOn(AndroidSchedulers.mainThread())
                .doOnNext(Consumer<String> { Log.i(TAG, "accept: 接收器 doonnext") })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<String> { s ->
                    textView!!.text = s
                    Log.i(TAG, "accept: 接收器： accept")
                })
    }

    companion object {

        private val TAG = "info"
    }

}




























