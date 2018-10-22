package module

import android.content.res.Resources
import android.util.TypedValue
import org.json.JSONArray
import org.json.JSONObject


/**
 * 扩展JSONArray，提供 for in 支持。
 */

inline operator fun JSONArray.iterator(): Iterator<JSONObject?>{
    return (0 until length()).asSequence().map{ get(it) as? JSONObject }.iterator()
}
inline fun <reified T> JSONArray.forin(): Iterator<T?>{
    return (0 until length()).asSequence().map{ get(it) as? T }.iterator()
}

/**
 * 扩展JSONArray，提供 forEach 遍历方法。
 */
inline fun <reified T> JSONArray.forEach(action: (T?) -> Unit) {
    (0 until length()).forEach { action(get(it) as? T) }
}


/**
 * 快速构造JSONArray
 */
fun jsonArrayfrom(vararg args: Any):JSONArray{
    var ary = JSONArray()
    for (obj in args){
        ary.put(obj)
    }
    return ary
}

/**
 * 快速构造JSONObject
 */
fun jsonObjectfromKVS(vararg args: Any):JSONObject{
    var retv = JSONObject()
    assert(args.size % 2 == 0)
    for (i in 0 until args.size step 2){
        val key = args[i] as String
        val value = args[i + 1]
        retv.put(key, value)
    }
    return retv
}

/**
 * 返回所有值
 */
fun JSONObject.values() : JSONArray{
    val ary = JSONArray()
    for (k in keys()){
        ary.put(this.get(k))
    }
    return ary
}


/**
 * 直接put一个数组
 */
fun JSONArray.putAll(ary:JSONArray){
    for (i in 0 until ary.length()){
        this.put(ary.get(i))
    }
}

/**
 * 获取首元素
 */
inline fun <reified T> JSONArray.first() :T?{
    val size = length()
    if (size <= 0){
        return null
    }else{
        return get(0) as T
    }
}

/**
 * 获取尾元素
 */
inline fun <reified T> JSONArray.last() :T?{
    val size = length()
    if (size <= 0){
        return null
    }else{
        return get(size - 1) as T
    }
}

/**
 * 转 JSONArray to List
 */
inline fun <reified T> JSONArray.toList() : List<T>{
    val retv = mutableListOf<T>()
    for (i in 0 until length()){
        retv.add(get(i) as T)
    }
    return retv
}

/**
 * 转 List to JSONArray
 */
inline fun <reified T>  List<T>.toJsonArray():JSONArray{
    val retv = JSONArray()
    for (v in this){
        retv.put(v)
    }
    return retv
}

/**
 * 空扩展
 */
fun Any?.toString(): String {
    if (this == null) return ""
    return toString()
}

/**
 * DP 和 PX 转换
 * 来源 https://www.jianshu.com/p/3520c63e1e0c
 */
private val metrics = Resources.getSystem().displayMetrics

/**
 * 正常编码中一般只会用到 [dp]/[sp] 和 [px] ;
 * 其中[dp]/[sp] 会根据系统分辨率将输入的dp/sp值转换为对应的px
 * 而[px]只是返回自身，目的是表明自己是px值
 */


val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)

val Int.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()

val Float.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, metrics)

val Int.sp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), metrics).toInt()

val Number.px: Number
    get() = this

/**
 * 在(可能存在的?)某些特殊情况会需要将px值转换为对应的dp/sp
 * 对应方法[Number.px2dp]/[Number.px2sp]
 */
val Number.px2dp: Int       // [xxhdpi](360 -> 120)
    get() = (this.toFloat() / metrics.density).toInt()
