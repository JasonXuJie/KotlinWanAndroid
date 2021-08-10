package com.jason.my.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.ui.theme.*
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException


//@AndroidEntryPoint
@Route(path = Routes.MY)
class MyFragment : Fragment(){

    private val menus = mutableListOf<String>("常用网站","收藏","反馈","设置")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ContentView()
            }
        }
    }

    @Composable
    private fun ContentView(){
        LazyColumn(Modifier.fillMaxSize()){
            item {
                Column(Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(ColorPrimary),
                verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
                   Image(painter = painterResource(id = com.jason.common.R.drawable.img_header), contentDescription = null,
                   Modifier.size(60.dp))
                   Spacer(modifier = Modifier.size(15.dp))
                   Text(text = "去登陆",style = whiteNormal12,modifier = Modifier.clickable { Navigator.push(Routes.LOGIN) })

                }
            }
            itemsIndexed(menus){index, item ->
                val icon = when(index){
                    0->{
                        com.jason.common.R.drawable.menu_list_one
                    }
                    1->{
                        com.jason.common.R.drawable.menu_list_six
                    }
                    2->{
                        com.jason.common.R.drawable.menu_list_two
                    }
                    3->{
                        com.jason.common.R.drawable.menu_list_seven
                    }
                    else->{
                        throw RuntimeException("Not resource")
                    }
                }
                Column(Modifier.fillMaxWidth().clickable {
                    when(index){
                        0->{
                            Navigator.push(Routes.USED_WEB)
                        }
                        1->{
                            Navigator.push(Routes.COLLECT)
                        }
                        2->{
                            Navigator.push(Routes.FEEDBACK)
                        }
                        3->{
                            Navigator.push(Routes.SETTING)
                        }
                        else->{
                            throw RuntimeException("Not resource")
                        }
                    }
                }) {
                    Row(Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = icon), contentDescription = null,Modifier.size(25.dp))
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = menus[index],style = blackNormal14,modifier = Modifier.weight(1f))
                        Image(painter = painterResource(id = com.jason.common.R.drawable.img_right_arrow), contentDescription = null,modifier = Modifier.size(10.dp))
                    }
                    Divider(color = ColorF5,modifier = Modifier.fillMaxWidth().height(1.dp))
                }

            }
        }
    }
}