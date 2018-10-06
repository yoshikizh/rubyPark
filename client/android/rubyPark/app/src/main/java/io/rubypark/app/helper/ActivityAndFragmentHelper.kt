package io.rubypark.app.helper

import android.content.Intent
import android.support.v4.app.Fragment
import android.widget.Toast


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