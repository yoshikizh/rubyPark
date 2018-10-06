package io.rubypark.app.helper

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import io.rubypark.app.R
import android.view.WindowManager
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics


/**
 *  主线程运行 用于线程内刷新 UI [Activity]
 */
fun android.app.Activity.runOnMainUI(body: () -> Unit){
    this.runOnUiThread(
        object :Runnable{
            override fun run() {
                body()
            }
        }
    )
}

/**
 *  Toast 封装 [Activity]
 */
fun android.app.Activity.showToast(str:String){
    android.widget.Toast.makeText(this.applicationContext, str, Toast.LENGTH_SHORT).show()
}

/**
 *  Toast 封装 [Fragment]
 */
fun Fragment.showToast(str:String, duration:Int = 2) {
    this.activity?.showToast(str)
}

/**
 *  redirect_to 跳转 Activity [Activity]
 */
fun android.app.Activity.redirecto(cls: Class<*>, transition_animation: Boolean = false, back:Boolean = false, request_code:Int = -1) {
    val intent = Intent()
    intent.setClass(this,cls)

    // REMARK 返回时跳过多个 Activity
    if (back){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }

    // Todo 设置跳转参数 (由于 intent 参数无法传递 Any)
    // ManagerTemp.instance.setParams()

    //  是否获取结果
    if (request_code > 0){
        startActivityForResult(intent, request_code)
    }else{
        startActivity(intent)
    }

    if (!transition_animation) {
        overridePendingTransition(0,0)
    }
}


/**
 * 设置自动调整高度的 contentView [xml模式]
 */
fun android.app.Activity.setAutoLayoutContentView(layoutResID:LinearLayout, navigationBarColor:Int? = null) {
    setContentView(layoutResID)
    adjustWindowSizeForNavigationBar(navigationBarColor)
}

/**
 * 适配虚拟机导航栏
 */
fun android.app.Activity.adjustWindowSizeForNavigationBar(navigationBarColor:Int? = null){
    val display = this.windowManager.defaultDisplay
    val size = Point()
    val realsize = Point()
    display.getSize(size)

    // Todo Api Level
    display.getRealSize(realsize)

    if (size.y != realsize.y){
        val contentView = findViewById<View>(android.R.id.content)
        //  更改布局高度（留出虚拟导航栏位置）
        contentView.layoutParams.height = size.y
        //  设置留出的导航栏区域背景
        if (navigationBarColor != null){
            contentView.rootView?.setBackgroundColor(resources.getColor(navigationBarColor))
        }else{
            contentView.rootView?.setBackgroundColor(resources.getColor(R.color.theme01_appBackColor))
        }
    }

    setWindowStatusBarColor()

    // Todo Api Level
    window.navigationBarColor = resources.getColor(R.color.theme01_appBackColor)

}

/**
 * 设置状态栏颜色(透明)
 */
fun android.app.Activity.setWindowStatusBarColor() {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = getWindow()
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 设置全屏
 */
fun android.app.Activity.setFullScreen() {
    val window = getWindow()
    val dector_view: View = window.decorView
    val option: Int = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    dector_view.systemUiVisibility = option

    // Todo Api Level
    window.navigationBarColor = Color.TRANSPARENT
}

/**
 * 获取屏幕 -> [宽,高]
 */
fun android.app.Activity.getScreen() : Array<Float> {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    val w = dm.widthPixels.toFloat()
    val h = dm.heightPixels.toFloat()
    return arrayOf(w,h)
}


