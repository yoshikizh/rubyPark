package io.rubypark.app.helper

import android.support.v4.app.Fragment
import android.widget.Toast


/**
 *  主线程运行 用于线程内刷新 UI
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

