package com.jason.common.base

import android.app.Application
import android.content.Context
import android.view.Gravity
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.jason.common.R
import com.jason.common.utils.MyLog
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator



abstract class BaseApplication : Application() {

    companion object{
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        if (isDebug()){
            ARouter.openLog();
            ARouter.openDebug()
        }
        ARouter.init(this)
        context = applicationContext
        setGlobalHeader()
        setToast()
    }


    //全局设置刷新Header
    fun setGlobalHeader(){
        ClassicsHeader.REFRESH_HEADER_PULLING = getString(R.string.header_pulldown)
        ClassicsHeader.REFRESH_HEADER_REFRESHING = getString(R.string.header_refreshing)
        ClassicsHeader.REFRESH_HEADER_LOADING = getString(R.string.header_loading)
        ClassicsHeader.REFRESH_HEADER_RELEASE = getString(R.string.header_release)
        ClassicsHeader.REFRESH_HEADER_FINISH = getString(R.string.header_finish)
        ClassicsHeader.REFRESH_HEADER_FAILED = getString(R.string.header_failed)
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator{
            override fun createRefreshHeader(
                context: Context,
                layout: RefreshLayout
            ): RefreshHeader {
                val header = ClassicsHeader(context)
                header.setEnableLastTime(false)
                return header.setSpinnerStyle(SpinnerStyle.Translate)
            }

        })
    }

    fun setToast(){
        ToastUtils.init(this)
        ToastUtils.setGravity(Gravity.BOTTOM,0,150)
    }




    abstract fun isDebug():Boolean



}