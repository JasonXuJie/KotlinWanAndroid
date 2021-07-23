package com.jason.router

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

object Navigator {

    fun push(path:String){
        ARouter.getInstance().build(path).navigation()
    }

    fun pushByParams(path: String,bundle: Bundle){
        ARouter.getInstance().build(path).with(bundle).navigation()

    }

    fun pushForResult(path: String,ctx:Activity,callback: NavigationCallback){
        ARouter.getInstance().build(path).navigation(ctx,callback)
    }


    fun pushParamsForResult(path: String,ctx: Activity,bundle: Bundle,callback: NavigationCallback){
        ARouter.getInstance().build(path).with(bundle).navigation(ctx,callback)
    }


}