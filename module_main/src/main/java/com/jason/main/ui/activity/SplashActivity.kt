package com.jason.main.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.ui.theme.*
import com.jason.main.R
import com.jason.main.vm.SplashViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
@Route(path = Routes.SPLASH)
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinWanAndroidTheme {
                ContentView()
            }
        }
    }

    @Composable
    private fun ContentView() {
        val vm : SplashViewModel = viewModel()
        vm.openToGuide()
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = com.jason.common.R.drawable.app_icon),
                contentDescription = null,modifier = Modifier.size(70.dp,70.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = stringResource(id = R.string.main_app_name), style = whiteBold14)
        }
    }

}


