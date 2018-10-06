package io.rubypark.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Example of a call to a native method
        // sample_text.text = stringFromJNI()

        val layout = LinearLayout(this)

        val text_view = TextView(this)
        text_view.text = "hello"

        layout.addView(text_view)






        setContentView(layout)
    }

}
