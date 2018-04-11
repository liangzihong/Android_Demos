package view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.liangzihong.caculator_mvc.R

import model.Calculator_model

class MainActivity : AppCompatActivity() {

    private var textView:TextView?=null
    private var equal_button:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        init()
    }

    private fun init() {
        textView=findViewById(R.id.textView) as TextView
        equal_button=findViewById(R.id.button_equal) as Button
    }

    fun onclick(view: View) {

        var input = ""
        if (textView!!.text != null)
            input = textView!!.text.toString() + ""
        when (view.id) {
            R.id.button0 -> {
                input = input + "0"
                textView!!.text = input
            }
            R.id.button1 -> input = input + "1"
            R.id.button2 -> input = input + "2"
            R.id.button3 -> input = input + "3"
            R.id.button4 -> input = input + "4"
            R.id.button5 -> input = input + "5"
            R.id.button6 -> input = input + "6"
            R.id.button7 -> input = input + "7"
            R.id.button8 -> input = input + "8"
            R.id.button9 -> input = input + "9"
            R.id.button_plus -> input = input + " + "
            R.id.button_minus -> input = input + " - "
            R.id.button_multiply -> input = input + " * "
            R.id.button_divide -> input = input + " / "
            R.id.button_c -> {
                textView!!.text = null
                return
            }
            R.id.button_equal -> {
                val answer = Calculator_model.caculate(input)
                textView!!.text = answer.toString() + ""
                return
            }
        }
        textView!!.text = input
    }

}
