package com.jason.my.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.blackNormal14
import com.jason.common.ui.theme.d3Normal14
import com.jason.common.ui.theme.whiteNormal14
import com.jason.common.widgets.TopBar
import com.jason.my.R
import com.jason.my.vm.FeedbackViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
@Route(path = Routes.FEEDBACK)
class FeedbackActivity : ComponentActivity() {

    var content = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm:FeedbackViewModel = viewModel()
            Column(Modifier.fillMaxSize()) {
                TopBar(name = "用户反馈") { finish() }
                Spacer(modifier = Modifier.height(20.dp))
                EditView()
                Spacer(modifier = Modifier.height(40.dp))
                Button(onClick = {vm.submit(content)}, colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorPrimary
                ), modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(horizontal = 40.dp),
                shape = RoundedCornerShape(percent = 80)) {
                    Text(text = stringResource(id = R.string.my_submit), style = whiteNormal14)
                }
            }
        }
    }

    @Composable
    private fun EditView() {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(value = text,
            onValueChange = {
                text = it
                content = it
            },
            maxLines = 10,
            textStyle = blackNormal14,
            placeholder = {
                Text(text = stringResource(id = R.string.hint_feedback), style = d3Normal14)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 30.dp))
    }

}