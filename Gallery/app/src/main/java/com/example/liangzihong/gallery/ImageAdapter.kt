package com.example.liangzihong.gallery

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView

/**
 * Created by Liang Zihong on 2018/2/11.
 */

class ImageAdapter(private val resource: IntArray, private val context: Context) : BaseAdapter() {

    /**
     * 返回已定义的数据源的总数量，也可以设置无限大，可以无限循环
     * 等下也会这样做
     * @return
     */
    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }


    /**
     * 告诉适配器取得目前容器中的对象
     * 固定的写法，返回数据源的目标对象
     * @param position
     * @return
     */
    override fun getItem(position: Int): Any {
        return resource[position]
    }


    /**
     * 告诉适配器取得目前容器中的ID
     * 固定的写法，返回position
     * @param position
     * @return
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    /**
     * 取得目前欲显示的图像view，传入数组ID值使之读取与成像
     * 人话，你想要呈现在屏幕上的是什么，你就返回那个的类给他
     * 如图片就是ImageView之类的
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var position = position
        position = position % resource.size
        val imageView = ImageView(context)
        imageView.setBackgroundResource(resource[position])

        /**
         * 设置以什么样的规格出现
         * setScaleType表示它的比例是怎样的
         */
        imageView.layoutParams = Gallery.LayoutParams(400, 300)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return imageView
    }
}
