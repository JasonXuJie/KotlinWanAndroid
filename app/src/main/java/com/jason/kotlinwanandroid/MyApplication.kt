package com.jason.kotlinwanandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.jason.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : BaseApplication() {


    override fun isDebug(): Boolean = true


    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }


}