package io.rubypark.app.view

import android.content.Context
import android.graphics.ColorFilter
import android.support.graphics.drawable.R
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import module.LINEAR_LAYOUT_PARAMS_MATCH
import module.LINEAR_LAYOUT_PARAMS_WRAP
import module.dp
import module.px2dp

class BottomNavigationView : LinearLayout {

    lateinit var mContext: Context
    val HEIGHT = 48.dp
    lateinit var allTextViews: ArrayList<TextView>
    lateinit var allIconViews: ArrayList<ImageView>

    constructor(context: Context) : super(context) {
        mContext = context
    }


    public fun init() : LinearLayout{

        allTextViews = arrayListOf()
        allIconViews = arrayListOf()

        this.orientation = LinearLayout.HORIZONTAL
        this.layoutParams = LinearLayout.LayoutParams(LINEAR_LAYOUT_PARAMS_MATCH, HEIGHT)

        this.addView(createSubView(io.rubypark.app.R.drawable.ic_message, "聊天"))
        this.addView(createSubView(io.rubypark.app.R.drawable.ic_contact, "好友"))
        this.addView(createSubView(io.rubypark.app.R.drawable.ic_explore, "发现"))
        this.addView(createSubView(io.rubypark.app.R.drawable.ic_user, "我"))

        return this
    }

    private fun createSubView(icon: Int, text: String ) : LinearLayout {
        val layout = LinearLayout(mContext)
        val layout_params = LinearLayout.LayoutParams(LINEAR_LAYOUT_PARAMS_WRAP, HEIGHT)
        layout.orientation = LinearLayout.VERTICAL
        layout_params.weight = 1.0f
        layout.layoutParams = layout_params

        val view_icon = ImageView(mContext)
        val view_icon_layout_params = LinearLayout.LayoutParams(LINEAR_LAYOUT_PARAMS_WRAP, LINEAR_LAYOUT_PARAMS_WRAP)
        view_icon.setImageDrawable(mContext.resources.getDrawable(icon))
        view_icon_layout_params.gravity = Gravity.CENTER_HORIZONTAL
        view_icon.layoutParams = view_icon_layout_params
        view_icon.setColorFilter(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorNormal))
        view_icon.scaleX = 0.7f
        view_icon.scaleY = 0.7f

        val view_text = TextView(mContext)
        val view_text_params = LinearLayout.LayoutParams(LINEAR_LAYOUT_PARAMS_WRAP, LINEAR_LAYOUT_PARAMS_WRAP)
        view_text_params.gravity = Gravity.CENTER_HORIZONTAL
        view_text.layoutParams = view_text_params
        view_text.text = text
        view_text.gravity = Gravity.CENTER_HORIZONTAL
        view_text.setTextColor(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorNormal))
        view_text.textSize = 5.5f.dp

        allIconViews.add(view_icon)
        allTextViews.add(view_text)

        layout.addView(view_icon)
        layout.addView(view_text)

        layout.setOnClickListener{
            initColors()
            view_icon.setColorFilter(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorHighlight))
            view_text.setTextColor(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorHighlight))
        }

        return layout
    }

    private fun initColors(){
        allIconViews.forEach {
            it.setColorFilter(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorNormal))
        }
        allTextViews.forEach {
            it.setTextColor(mContext.resources.getColor(io.rubypark.app.R.color.theme01_textColorNormal))
        }

    }
}