package com.example.liangzihong.dialog

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var Dialog_Button: Button? = null
    private var builder: android.app.AlertDialog.Builder? = null
    private val SingleChoice = arrayOf("Teacher", "CTO", "Programmer")
    private val MultiplyChoice = arrayOf("Football", "Basketball", "Tennis")
    private val ItemList = arrayOf("CEO", "CTO", "COO", "Project Manager")
    private var dialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        init()
    }

    /**
     * 按钮的初始化以及事件响应
     */
    private fun init() {
        button1 = findViewById(R.id.button1) as Button
        button2 = findViewById(R.id.button2) as Button
        button3 = findViewById(R.id.button3) as Button
        button4 = findViewById(R.id.button4) as Button
        button5 = findViewById(R.id.button5) as Button

        button1!!.setOnClickListener(this)
        button2!!.setOnClickListener(this)
        button3!!.setOnClickListener(this)
        button4!!.setOnClickListener(this)
        button5!!.setOnClickListener(this)
    }

    /**
     * 触发按钮点击事件
     */
    override fun onClick(v: View) {
        when (v.id) {
        //确认对话框
            R.id.button1 -> {
                builder = AlertDialog.Builder(this)
                //对话框的标题
                builder!!.setTitle("确认对话框")
                builder!!.setIcon(R.mipmap.ic_launcher)

                //对话框的提示内容
                builder!!.setMessage("这是确认对话框的内容")
                //对话框的基本事件响应
                setBuilder(builder)
                dialog = builder!!.create()
                dialog!!.show()
            }


        //单选对话框
            R.id.button2 -> {
                builder = AlertDialog.Builder(this)
                builder!!.setTitle("单选对话框")
                builder!!.setIcon(R.mipmap.ic_launcher)
                //设置单选对话框的数据源以及  对应的事件响应
                builder!!.setSingleChoiceItems(SingleChoice, 0) { dialog, which ->
                    val choose = SingleChoice[which]
                    Toast.makeText(this@MainActivity, "你选择了" + choose, Toast.LENGTH_SHORT).show()
                }

                //对话框的基本事件响应
                setBuilder(builder)
                dialog = builder!!.create()
                dialog!!.show()
            }


        //多选对话框
            R.id.button3 -> {
                builder = AlertDialog.Builder(this)
                builder!!.setTitle("爱好")
                builder!!.setIcon(R.mipmap.ic_launcher_round)
                builder!!.setMultiChoiceItems(MultiplyChoice, null) { dialog, which, isChecked ->
                    if (isChecked) {
                        Toast.makeText(this@MainActivity, "你选择了" + MultiplyChoice[which], Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "你取消了" + MultiplyChoice[which], Toast.LENGTH_SHORT).show()
                    }
                }
                setBuilder(builder!!)
                dialog = builder!!.create()
                dialog!!.show()
            }

        //列表对话框
            R.id.button4 -> {
                builder = AlertDialog.Builder(this)
                builder!!.setTitle("职位")
                builder!!.setIcon(R.mipmap.ic_launcher_round)

                //设置数据源
                builder!!.setItems(ItemList) { dialog, which -> Toast.makeText(this@MainActivity, "我做了" + ItemList[which], Toast.LENGTH_SHORT).show() }
                setBuilder(builder)
                dialog = builder!!.create()
                dialog!!.show()
            }


        //自定义对话框
            R.id.button5 -> {
                val view = View.inflate(this, R.layout.mydialog_layout, null)

                builder = AlertDialog.Builder(this)
                builder!!.setTitle("自定义对话框")
                builder!!.setIcon(R.mipmap.ic_launcher_round)
                builder!!.setView(view)

                Dialog_Button = view.findViewById(R.id.mydialog_button) as Button
                Dialog_Button!!.setOnClickListener { Toast.makeText(this@MainActivity, "你的名字是", Toast.LENGTH_SHORT).show() }

                setBuilder(builder)
                dialog = builder!!.create()
                dialog!!.show()
            }
        }
    }

    /**
     * 设置对话框的确定与取消
     * @param builder
     */
    private fun setBuilder(builder: AlertDialog.Builder) {
        //对话框的事件响应,按确认和取消都是直接消失
        builder.setPositiveButton("确认") { dialog, which -> dialog.dismiss() }
        builder.setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
    }
}
