package Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.support.v7.widget.RecyclerView
import android.widget.*
import com.example.liangzihong.choosepicfromphone.R


class StartActivity : AppCompatActivity() {

    private var recyclerview : RecyclerView?=null
    private var inputEditText : EditText?=null
    private var sentButton : Button ?= null
    private var arr : List<Int>?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        init()
    }

    private fun init(){
        recyclerview= findViewById(R.id.xx) as RecyclerView
        inputEditText= findViewById(R.id.yy) as EditText
        arr= ArrayList<Int>()


        recyclerview!!.layoutManager= LinearLayoutManager(this)


        sentButton?.setOnClickListener {
            if (inputEditText!!.text==null)
                1+1
            2+2
        }




    }

}



