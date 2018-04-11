package com.example.liangzihong.gallery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Gallery
import android.widget.ImageView

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val resource = intArrayOf(R.drawable.wong1, R.drawable.wong2, R.drawable.wong3, R.drawable.wong4, R.drawable.wong5, R.drawable.wong6, R.drawable.wong7, R.drawable.wong8, R.drawable.wong9, R.drawable.wong10, R.drawable.wong11, R.drawable.wong12)
    private var adapter: ImageAdapter? = null
    private var image: ImageView? = null
    private var gallery: Gallery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        init()


    }


    private fun init() {
        gallery = findViewById(R.id.gallery) as Gallery
        image = findViewById(R.id.image) as ImageView
        adapter = ImageAdapter(resource, this)
        gallery!!.adapter = adapter
        gallery!!.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        image!!.setBackgroundResource(adapter!!.getItem(position % resource.size) as Int)
        image!!.scaleType = ImageView.ScaleType.FIT_CENTER
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}
