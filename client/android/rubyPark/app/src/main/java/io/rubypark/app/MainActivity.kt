package io.rubypark.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import io.rubypark.app.helper.adjustWindowSizeForNavigationBar
import io.rubypark.app.helper.getScreen
import module.UtilsBitmap
import module.dp
import module.px2dp
import module.sp
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 主容器
        val layout = FrameLayout(this)

        // 背景图
        val image_background = ImageView(this)
        image_background.setImageBitmap(UtilsBitmap.new(this, "title-back.png"))
        image_background.scaleType = ImageView.ScaleType.FIT_XY

        // body 容器
        val layout_body = LinearLayout(this)
        layout_body.orientation = LinearLayout.VERTICAL

        // logo 图片
        val image_logo = ImageView(this)
        val view_layout_params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        view_layout_params.gravity = Gravity.CENTER_HORIZONTAL
        image_logo.setImageBitmap(UtilsBitmap.new(this, "logo.png"))
        image_logo.layoutParams = view_layout_params

        // app 名称
        val text_view = TextView(this)
        text_view.text = resources.getText(R.string.title_name)
        text_view.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        text_view.layoutParams = view_layout_params
        text_view.gravity = Gravity.CENTER_HORIZONTAL

        // 版本号
        val version_text = TextView(this)
        version_text.text = String.format("%s %s","v:",resources.getText(R.string.version_name))
        version_text.setTextColor(resources.getColor(R.color.theme01_textColorGray))
        version_text.layoutParams = view_layout_params
        version_text.gravity = Gravity.CENTER_HORIZONTAL

        // 按钮
        val button_layout_params = LinearLayout.LayoutParams(128.dp, 34.dp)
        button_layout_params.gravity = Gravity.CENTER_HORIZONTAL
        button_layout_params.setMargins(0,40.dp,0,0)
        val button_start = Button(this)
        button_start.text = "立即使用"
        button_start.setTextSize(TypedValue.COMPLEX_UNIT_DIP,11f)
        button_start.gravity = Gravity.CENTER
        button_start.layoutParams = button_layout_params
        button_start.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        button_start.setBackgroundColor(resources.getColor(R.color.theme01_appBackColor))

        // 底部文字
        val text_slogan_params_layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        text_slogan_params_layout.setMargins(0,getScreen()[1].toInt() - 132,0,0)
        val text_slogan = TextView(this)
        text_slogan.text = resources.getString(R.string.app_slogan)
        text_slogan.setTextSize(TypedValue.COMPLEX_UNIT_DIP,11f)
        text_slogan.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        text_slogan.layoutParams = text_slogan_params_layout
        text_slogan.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM

        // 设置 paddingTop -> 1 / 3 的位置
        val padding_top_pix = (getScreen()[1] / 3).toInt()
        layout_body.setPadding(0, padding_top_pix,0,0)

        layout_body.addView(image_logo)
        layout_body.addView(text_view)
        layout_body.addView(version_text)
        layout_body.addView(button_start)

        layout.addView(image_background)
        layout.addView(layout_body)
        layout.addView(text_slogan)

        setContentView(layout)

        adjustWindowSizeForNavigationBar()
    }

}
