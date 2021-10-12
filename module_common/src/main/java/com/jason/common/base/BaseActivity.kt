package com.jason.common.base

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.gyf.immersionbar.ImmersionBar
import com.jason.common.R
import com.jason.common.utils.ActivityStack
import com.jason.common.widgets.LoadingDialog
import com.jason.router.Navigator

abstract class BaseActivity<T : ViewBinding,VM : BaseViewModel?>:AppCompatActivity() {

    lateinit var binding: T
    var viewModel:VM? = null
    private var loadingDialog : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        binding = getBind()
        viewModel = getVM()
        setContentView(binding.root)
        ActivityStack.addActivity(this)
        initViews()
        initData()

    }


    abstract fun getBind():T
    abstract fun getVM():VM?
    abstract fun initViews()
    abstract fun initData()

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


    fun setToolBarTitle(title:String?){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val iv_back = toolbar.findViewById<ImageView>(R.id.iv_back)
        val tv_title = toolbar.findViewById<TextView>(R.id.tv_title)
        iv_back.setOnClickListener { finish() }
        tv_title.text = title?:""
    }


    fun showLoading(){
        if (loadingDialog == null){
            loadingDialog = LoadingDialog()
        }
        loadingDialog?.show(supportFragmentManager)
    }

    fun hideLoading(){
        loadingDialog?.dismiss()
    }


    override fun onDestroy() {
        ActivityStack.removeActivity(this)
        super.onDestroy()
    }


}