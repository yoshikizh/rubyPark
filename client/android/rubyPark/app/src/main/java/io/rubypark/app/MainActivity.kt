package io.rubypark.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.rubypark.app.helper.adjustWindowSizeForNavigationBar
import io.rubypark.app.helper.getScreen
import io.rubypark.app.helper.setAutoLayoutContentView
import io.rubypark.app.helper.setFullScreen
import module.UtilsBitmap
import java.io.InputStream

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val image_logo = ImageView(this)
        val layout_params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layout_params.gravity = Gravity.CENTER_HORIZONTAL

        image_logo.setImageBitmap(UtilsBitmap.new(this, "logo.png"))
        image_logo.layoutParams = layout_params

        val text_view = TextView(this)
        text_view.text = resources.getText(R.string.title_name)
        text_view.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        text_view.layoutParams = layout_params
        text_view.gravity = Gravity.CENTER_HORIZONTAL

        val version_text = TextView(this)
        version_text.text = String.format("%s %s","v:",resources.getText(R.string.version_name))
        version_text.setTextColor(resources.getColor(R.color.theme01_textColorGray))
        version_text.layoutParams = layout_params
        version_text.gravity = Gravity.CENTER_HORIZONTAL

        // 设置 paddingTop
        val screen_height = getScreen()[1]
        layout.setPadding(0, (screen_height / 3).toInt(),0,0)

        layout.addView(image_logo)
        layout.addView(text_view)
        layout.addView(version_text)

        setContentView(layout)

        adjustWindowSizeForNavigationBar()
    }

}
