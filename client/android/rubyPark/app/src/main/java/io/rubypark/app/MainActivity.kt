package io.rubypark.app

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.rubypark.app.helper.adjustWindowSizeForNavigationBar
import io.rubypark.app.helper.setAutoLayoutContentView
import io.rubypark.app.helper.setFullScreen

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        

        val layout = LinearLayout(this)


        val text_view = TextView(this)
        text_view.text = "hello"

        layout.addView(text_view)

        setContentView(layout)


        adjustWindowSizeForNavigationBar()
    }

}
