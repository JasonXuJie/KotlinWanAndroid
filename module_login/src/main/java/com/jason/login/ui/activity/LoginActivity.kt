package com.jason.login.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseActivity
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.login.databinding.ActivityLoginBinding
import com.jason.login.vm.LoginViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    companion object{
        const val RESULT_CODE = 4
    }

    private val loginViewModel:LoginViewModel by viewModels()

    override fun getBind(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override fun getVM(): LoginViewModel = loginViewModel

    override fun initViews() {
      setToolBarTitle("登陆")
      binding.tvRegister.setOnClickListener { push(Routes.REGISTER) }
      viewModel?.loadingEvent?.observe(this,{
          if (it){
              showLoading()
          }else{
              hideLoading()
          }
      })
      viewModel?.loginData?.observe(this,{
          val intent = Intent()
          intent.putExtra(BundleKey.NAME,it.nickname)
          setResult(RESULT_CODE,intent)
          finish()
          ToastUtils.show("登陆成功")
      })
      viewModel?.errMsg?.observe(this,{
          ToastUtils.show(it)
      })
       binding.btnLogin.setOnClickListener {
           viewModel?.login(binding.etUsername.text.toString(),binding.etPwd.text.toString()) }
    }

    override fun initData() {

    }

}