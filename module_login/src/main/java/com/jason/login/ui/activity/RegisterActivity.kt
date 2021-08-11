package com.jason.login.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseComposeActivity
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.White
import com.jason.common.ui.theme.d3Normal14
import com.jason.common.ui.theme.whiteNormal14
import com.jason.common.widgets.LoadingView
import com.jason.common.widgets.TopBar
import com.jason.login.R
import com.jason.login.vm.RegisterViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
@Route(path = Routes.REGISTER)
class RegisterActivity : BaseComposeActivity() {

    var account by mutableStateOf("")
    var pwd by mutableStateOf("")
    var confirmPwd by mutableStateOf("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: RegisterViewModel = viewModel()
            vm.registerData.observe(this, {
                finish()
                ToastUtils.show("注册成功")
            })
            vm.errMsg.observe(this, {
                ToastUtils.show(it)
            })
            if (vm.showLoading){
                LoadingView()
            }
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TopBar(name = "注册", onBack = { finish() })
                Spacer(modifier = Modifier.height(20.dp))
                EditView(value = account,
                    hintStr = R.string.text_username,
                    onValueChange = { account = it },
                    type = KeyboardType.Text)
                Spacer(modifier = Modifier.height(15.dp))
                EditView(value = pwd,
                    hintStr = R.string.text_pwd,
                    onValueChange = { pwd = it },
                    type = KeyboardType.Password)
                Spacer(modifier = Modifier.height(15.dp))
                EditView(value = confirmPwd,
                    hintStr = R.string.text_repwd,
                    onValueChange = { confirmPwd = it },
                    type = KeyboardType.Password)
                Spacer(modifier = Modifier.height(25.dp))
                Button(onClick = {
                        vm.register(account,pwd,confirmPwd)
                },
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorPrimary
                    ),
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp)) {
                    Text(text = stringResource(id = R.string.register), style = whiteNormal14)
                }
            }
        }
    }


    @Composable
    private fun EditView(
        value: String,
        hintStr: Int,
        onValueChange: (String) -> Unit,
        type: KeyboardType,
    ) {
        Surface(color = White) {
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 12.dp)
                .border(1.dp, ColorPrimary),
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(text = stringResource(id = hintStr), style = d3Normal14)
                },
                keyboardOptions = KeyboardOptions(keyboardType = type),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorPrimary
                )
            )
        }
    }
}