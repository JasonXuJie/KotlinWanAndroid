package com.jason.login.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseComposeActivity
import com.jason.common.config.BundleKey
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.d3Normal14
import com.jason.common.ui.theme.whiteNormal12
import com.jason.common.ui.theme.whiteNormal14
import com.jason.common.widgets.LoadingView
import com.jason.common.widgets.TopBar
import com.jason.login.R
import com.jason.login.vm.LoginViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
@Route(path = Routes.LOGIN)
class LoginActivity : BaseComposeActivity(){

    companion object {
        const val RESULT_CODE = 4
    }

    var account by mutableStateOf("")
    var pwd by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm : LoginViewModel = viewModel()
            if(vm.showLoading){
                LoadingView()
            }
            vm.loginData.observe(this, {
                val intent = Intent()
                intent.putExtra(BundleKey.NAME, it.nickname)
                setResult(RESULT_CODE, intent)
                finish()
                ToastUtils.show("登陆成功")
            })
            vm.errMsg.observe(this, {
                ToastUtils.show(it)
            })
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box {
                    TopBar(name = "登陆", onBack = { finish() })
                    Text(text = stringResource(id = R.string.register),
                        style = whiteNormal14,
                        modifier = Modifier
                            .align(
                                Alignment.CenterEnd)
                            .padding(end = 15.dp)
                            .clickable { push(Routes.REGISTER) })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Image(painter = painterResource(id = com.jason.common.R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp))
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(value = account, onValueChange = {
                    account = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 12.dp)
                    .border(1.dp, ColorPrimary, RoundedCornerShape(10.dp)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(text = stringResource(id = R.string.text_username), style = d3Normal14)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(value = pwd, onValueChange = {
                    pwd = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 12.dp)
                    .border(1.dp, ColorPrimary, RoundedCornerShape(10.dp)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(text = stringResource(id = R.string.text_username), style = d3Normal14)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    vm.login(account,pwd)
                },colors =ButtonDefaults.buttonColors(
                    backgroundColor = ColorPrimary
                ),shape = RoundedCornerShape(100.dp),modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)) {
                    Text(text = stringResource(id = R.string.login), style = whiteNormal12)
                }
            }
        }
    }
}