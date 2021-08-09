package com.jason.my.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.ui.theme.*
import com.jason.common.widgets.TopBar
import com.jason.my.R
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
@Route(path = Routes.SETTING)
class SettingActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
                ,horizontalAlignment = Alignment.CenterHorizontally) {
                TopBar(name = "设置", onBack = { finish()})
                Column(Modifier.weight(1f)) {
                    ItemView(itemName = getString(R.string.theme), isVisible = false)
                    ItemView(itemName = getString(R.string.cur_version), isVisible = false)
                    ItemView(itemName = getString(R.string.about_me), isVisible = true)
                    ItemView(itemName = getString(R.string.cache_size), isVisible = false)
                }
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red
                ), modifier = Modifier
                    .width(180.dp)
                    .height(35.dp)) {
                    Text(text = stringResource(id = R.string.logout), style = whiteNormal14)
                }
            }
        }
    }

    @Composable
    private fun ItemView(itemName:String,isVisible:Boolean){
        val content = when(itemName){
            getString(R.string.theme)->{
                "默认模式"
            }
            getString(R.string.cur_version)->{
                "1.0.0"
            }
            getString(R.string.cache_size)->{
                "12.1MB"
            }
            else->{
                ""
            }
        }
        val contentColor = if (itemName == getString(R.string.cur_version)) mainNormal14 else d3Normal14
        Column(Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp)
            .clickable { }) {
            Row(modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = itemName,style = blackNormal14,textAlign = TextAlign.Left,modifier = Modifier.weight(1f))
                if (isVisible){
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null,tint = ColorD3)
                }else{
                    Text(text = content,style = contentColor,textAlign = TextAlign.Right,modifier = Modifier.weight(1f))
                }
            }
            Divider(color = ColorF5,modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
        }
    }


}