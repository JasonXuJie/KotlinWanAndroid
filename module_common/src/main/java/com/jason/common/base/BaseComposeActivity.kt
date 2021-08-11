package com.jason.common.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.gyf.immersionbar.ImmersionBar
import com.jason.common.R
import com.jason.common.utils.ActivityStack
import com.jason.common.widgets.LoadingDialog
import com.jason.router.Navigator

open class BaseComposeActivity : ComponentActivity() {

    //private var loadingDialog : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        ActivityStack.addActivity(this)
    }




    private fun setStatusBar(){
        ImmersionBar.with(this).
        statusBarColor(R.color.colorPrimary).barAlpha(0.3f).fitsSystemWindows(true).init()
    }

    open fun pushByParams(path:String,bundle: Bundle){
        Navigator.pushByParams(path, bundle)
    }

    open fun push(path: String){
        Navigator.push(path)
    }

    open fun pushByParamsForResult(path: String,bundle: Bundle,callback: NavigationCallback){
        Navigator.pushParamsForResult(path,this,bundle, callback)
    }

    open fun pushForResult(path: String,callback: NavigationCallback){
        Navigator.pushForResult(path,this,callback)
    }


//    fun showLoading(){
//        if (loadingDialog == null){
//            loadingDialog = LoadingDialog()
//        }
//        loadingDialog?.show()
//    }
//
//    fun hideLoading(){
//        loadingDialog?.dismiss()
//    }

}