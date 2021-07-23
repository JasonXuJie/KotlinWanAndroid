package com.jason.common.utils

import android.os.Parcelable
import com.jason.common.base.BaseApplication
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

object SpUtil{

    init {
        MMKV.initialize(BaseApplication.context, MMKVLogLevel.LevelInfo)
    }

    /**
     * 多进程模式
     * **/
    fun getKv(id: String, key: String, value: Any) {
        val mmkv = MMKV.mmkvWithID(id, MMKV.MULTI_PROCESS_MODE)
        //put(key, value)
    }

    /**
     * 保存
     * **/
    fun put(key: String, value: Any) {
        val kv = MMKV.defaultMMKV()
        when (value) {
            is String -> {
                kv.encode(key, value)
            }
            is Boolean -> {
                kv.encode(key, value)
            }
            is Int -> {
                kv.encode(key, value)
            }
            is Double -> {
                kv.encode(key, value)
            }
            is Float -> {
                kv.encode(key, value)
            }
            is Long -> {
                kv.encode(key, value)
            }
            is ByteArray -> {
                kv.encode(key, value)
            }
            is Parcelable -> {
                kv.encode(key, value)
            }
            else -> {
                kv.encode(key, value as Set<String>)
            }
        }
    }

    /**
     * 获取
     * **/
    fun get(key: String, defValue: Any): Any {
        val kv = MMKV.defaultMMKV()
        return when (defValue) {
            is String -> {
                kv.decodeString(key, defValue)
            }
            is Boolean -> {
                kv.decodeBool(key, defValue)
            }
            is Int -> {
                kv.decodeInt(key, defValue)
            }
            is Long -> {
                kv.decodeLong(key, defValue)
            }
            is Float -> {
                kv.decodeFloat(key, defValue)
            }
            is Double -> {
                kv.decodeDouble(key, defValue)
            }
            else -> {
                kv.decodeStringSet(key)
            }
        }
    }


    /**
     * 获取所有key
     * ***/
    fun getAllKeys():Array<String>{
        val kv = MMKV.defaultMMKV()
        return kv.allKeys()
    }

    /**
     * 清除所有
     * **/
    fun clear() {
        val kv = MMKV.defaultMMKV()
        kv.clearAll()
    }
}