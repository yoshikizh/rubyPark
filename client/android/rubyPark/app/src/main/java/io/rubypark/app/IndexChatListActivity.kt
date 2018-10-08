package io.rubypark.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import io.rubypark.app.helper.adjustWindowSizeForNavigationBar
import io.rubypark.app.helper.render
import module.dp

class IndexChatListActivity : AppCompatActivity() {

    lateinit var mLayout: LinearLayout          // => 主容器
    lateinit var mHeaderLayout: LinearLayout          // => Header 主容器
    lateinit var mBodyLayout:   LinearLayout          // => Body   主容器
    lateinit var mFooterLayout: LinearLayout          // => Footer 主容器

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeViews()
        render(mLayout)

    }

    // 初始化界面所需 UI 组件
    private fun initializeViews(){
        // 主容器
        mLayout = LinearLayout(this)

        initHeaderView()
        initBodyViews()
        initFooterViews()
        wrapViews()

    }

    // 包装 Views
    private fun wrapViews(){
        mLayout.addView(mHeaderLayout)
        mLayout.addView(mBodyLayout)
        mLayout.addView(mFooterLayout)
    }

    // 初始化 Header view
    private fun initHeaderView(){
        mHeaderLayout = LinearLayout(this)
        mHeaderLayout.layoutParams =  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 40.dp )
        mHeaderLayout.gravity = Gravity.CENTER

        val text_title = TextView(this)
        text_title.gravity = Gravity.CENTER
        text_title.text = resources.getString(R.string.chatlist_title)
        text_title.setTextColor(resources.getColor(R.color.theme01_textColorMain))
        text_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12f)

        mHeaderLayout.addView(text_title)

    }

    // 初始化 Body views
    private fun initBodyViews(){
        mBodyLayout = LinearLayout(this)
    }

    // 初始化 Foot View
    private fun initFooterViews(){
        mFooterLayout = LinearLayout(this)
    }

}
