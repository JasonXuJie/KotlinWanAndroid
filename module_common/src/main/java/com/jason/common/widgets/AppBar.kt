package com.jason.common.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.White
import com.jason.common.ui.theme.whiteNormal14


@Composable
fun TopBar(name:String,onBack:()->Unit){
   Box(Modifier
       .fillMaxWidth()
       .height(56.dp)
       .background(ColorPrimary)) {
       IconButton(onClick = onBack,modifier = Modifier.fillMaxHeight()){
           Icon(Icons.Filled.ArrowBack,contentDescription = null,tint = White)
       }
       Row(Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center) {
           Text(text = name,style = whiteNormal14)
       }
   }

}