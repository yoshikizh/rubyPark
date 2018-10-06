package module

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

inline fun <reified T> JSONArray.toList() : List<T>{
    val retv = mutableListOf<T>()
    for (i in 0 until length()){
        retv.add(get(i) as T)
    }
    return retv
}

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