package com.jason.common.widgets

import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.LottieAnimationView
import com.jason.common.ui.theme.White


@Composable
fun LoadingView(){
    Dialog(onDismissRequest = {  },properties = DialogProperties(dismissOnClickOutside = false)) {
        Surface(shape = RoundedCornerShape(15.dp),color = White) {
            Box(modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                ,contentAlignment = Alignment.Center) {
                AndroidView(modifier = Modifier
                    .width(100.dp)
                    .height(80.dp),factory = {context ->  LottieAnimationView(context).apply {
                    loop(true)
                    setAnimation("loading.json")
                    playAnimation()
                } })
            }
        }

    }
}