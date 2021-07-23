package com.jason.common.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.jason.common.utils.MyLog
import com.jason.common.widgets.LoadingDialog
import com.jason.router.Navigator

abstract class BaseFragment<T : ViewBinding,VM : BaseViewModel?>:Fragment() {

    lateinit var binding:T
    var viewModel:VM? = null
    private var loadingDialog:LoadingDialog? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBind(inflater, container)
        viewModel = getVM()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun getBind(inflater:LayoutInflater, container:ViewGroup?):T
    abstract fun getVM():VM?
    abstract fun initViews()
    abstract fun initData()

    open fun pushByParams(path:String,bundle: Bundle){
        Navigator.pushByParams(path, bundle)
    }

    open fun push(path: String){
        Navigator.push(path)
    }

    open fun pushByParamsForResult(path: String,bundle: Bundle,callback: NavigationCallback){
        Navigator.pushParamsForResult(path,context as Activity,bundle, callback)
    }

    open fun pushNoParamsForResult(path: String,callback: NavigationCallback){
        Navigator.pushForResult(path,context as Activity,callback)
    }


    open fun showLoading(){
        if (loadingDialog == null){
            loadingDialog = LoadingDialog()
        }
        activity?.let { loadingDialog?.show(it.supportFragmentManager) }
    }


    open fun hideLoading(){
        loadingDialog?.dismiss()
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}