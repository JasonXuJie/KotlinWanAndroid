package com.jason.login.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseActivity
import com.jason.login.databinding.ActivityRegisterBinding
import com.jason.login.vm.RegisterViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = Routes.REGISTER)
class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>(){

    private val registerViewModel:RegisterViewModel by viewModels()

    override fun getBind(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    override fun getVM(): RegisterViewModel = registerViewModel

    override fun initViews() {
        setToolBarTitle("注册")
        viewModel?.loadingEvent?.observe(this,{
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        })
        viewModel?.registerData?.observe(this,{
           finish()
           ToastUtils.show("注册成功")
        })
        viewModel?.errMsg?.observe(this,{
            ToastUtils.show(it)
        })
        binding.btnRegister.setOnClickListener {
            viewModel?.register(
                binding.etUsername.text.toString(),
                binding.etPwd.text.toString(),binding.etRepwd.text.toString()) }
    }

    override fun initData() {

    }
}