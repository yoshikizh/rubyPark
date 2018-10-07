package io.rubypark.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.rubypark.app.helper.adjustWindowSizeForNavigationBar
import io.rubypark.app.helper.getScreen
import module.UtilsBitmap
import module.dp
import module.px2dp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = FrameLayout(this)

        val image_background = ImageView(this)
        image_background.setImageBitmap(UtilsBitmap.new(this, "title-back.png"))
        image_background.scaleType = ImageView.ScaleType.FIT_XY

        val layout_body = LinearLayout(this)
        layout_body.orientation = LinearLayout.VERTICAL

        val image_logo = ImageView(this)
        val view_layout_params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        view_layout_params.gravity = Gravity.CENTER_HORIZONTAL

        image_logo.setImageBitmap(UtilsBitmap.new(this, "logo.png"))
        image_logo.layoutParams = view_layout_params

        val text_view = TextView(this)
        text_view.text = resources.getText(R.string.title_name)
        text_view.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        text_view.layoutParams = view_layout_params
        text_view.gravity = Gravity.CENTER_HORIZONTAL

        val version_text = TextView(this)
        version_text.text = String.format("%s %s","v:",resources.getText(R.string.version_name))
        version_text.setTextColor(resources.getColor(R.color.theme01_textColorGray))
        version_text.layoutParams = view_layout_params
        version_text.gravity = Gravity.CENTER_HORIZONTAL

        // 设置 paddingTop -> 1 / 3 的位置
        val padding_top_pix = (getScreen()[1] / 3).toInt()
        layout_body.setPadding(0, padding_top_pix,0,0)

        layout_body.addView(image_logo)
        layout_body.addView(text_view)
        layout_body.addView(version_text)

        layout.addView(image_background)
        layout.addView(layout_body)

        setContentView(layout)

        adjustWindowSizeForNavigationBar()
    }

}
