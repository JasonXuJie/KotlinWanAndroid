package com.jason.common.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

object MyLog {

    init {
        Logger.addLogAdapter(object : AndroidLogAdapter(){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return super.isLoggable(priority, tag)
            }
        })
    }

    fun i(msg:String){
        Logger.i(msg)
    }


    fun e(msg:String){
        Logger.e(msg)
    }

    fun json(json:String){
        Logger.json(json)
    }

}