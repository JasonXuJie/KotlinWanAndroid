package com.jason.login.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseActivity
import com.jason.common.config.BundleKey
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.whiteNormal12
import com.jason.common.ui.theme.whiteNormal14
import com.jason.common.utils.MyLog
import com.jason.common.widgets.TopBar
import com.jason.login.R
import com.jason.login.databinding.ActivityLoginBinding
import com.jason.login.vm.LoginViewModel
import com.jason.router.Navigator.push
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    companion object {
        const val RESULT_CODE = 4
    }

//    var account by mutableStateOf("")
//    var pwd by mutableStateOf("")

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val vm : LoginViewModel = viewModel()
//            Column(modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally) {
//                Box {
//                    TopBar(name = "登陆", onBack = { finish() })
//                    Text(text = stringResource(id = R.string.register),
//                        style = whiteNormal14,
//                        modifier = Modifier
//                            .align(
//                                Alignment.CenterEnd)
//                            .padding(end = 15.dp)
//                            .clickable { push(Routes.REGISTER) })
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Image(painter = painterResource(id = com.jason.common.R.drawable.app_icon),
//                    contentDescription = null,
//                    modifier = Modifier.size(80.dp))
//                Spacer(modifier = Modifier.height(20.dp))
//                OutlinedTextField(value = account, onValueChange = {
//                    account = it
//                }, modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .padding(horizontal = 12.dp)
//                    .border(1.dp, ColorPrimary, RoundedCornerShape(10.dp)),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                shape = RoundedCornerShape(10.dp))
//                Spacer(modifier = Modifier.height(20.dp))
//                OutlinedTextField(value = pwd, onValueChange = {
//                    pwd = it
//                }, modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .padding(horizontal = 12.dp)
//                    .border(1.dp, ColorPrimary, RoundedCornerShape(10.dp)),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                    shape = RoundedCornerShape(10.dp)
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(onClick = {
//                    vm.login(account,pwd)
//                },colors =ButtonDefaults.buttonColors(
//                    backgroundColor = ColorPrimary
//                ),shape = RoundedCornerShape(100.dp),modifier = Modifier
//                    .width(250.dp)
//                    .height(50.dp)) {
//                    Text(text = stringResource(id = R.string.login), style = whiteNormal12)
//                }
//            }
//        }
//    }

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