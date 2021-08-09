package com.jason.my.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.widgets.TopBar
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.COLLECT)
class CollectActivity :ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopBar(name = "收藏",onBack = {finish()})
        }
    }

}