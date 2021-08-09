package com.jason.my.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.common.ui.theme.*
import com.jason.common.widgets.PageLoading
import com.jason.common.widgets.TopBar
import com.jason.my.model.Classify
import com.jason.my.vm.UsedWebViewModel
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
@Route(path = Routes.USED_WEB)
class UsedWebActivity:ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar(name ="常用网站",onBack ={finish()} )
                ListView()
            }
        }
    }


    @Composable
    private fun ListView(){
        val vm:UsedWebViewModel = viewModel()
        PageLoading(
            loadState = vm.loadingState,
            onReload = {vm.loadData()}
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(vm.dataStateList){itemData->
                    ItemView(itemData)
                }
            }
        }
    }


    @Composable
    private fun ItemView(itemData:Classify){
       Column(modifier = Modifier
           .fillMaxWidth()
           .padding(top = 15.dp, start = 12.dp, end = 12.dp)) {
           Text(text = itemData.category,style = blackBold16)
           Spacer(modifier = Modifier.height(10.dp))
           Row {
               itemData.list.forEach{
                   TextButton(onClick = {
                       val bundle = bundleOf()
                       bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(null, it.link))
                       Navigator.pushByParams(Routes.WEB, bundle)
                   },colors = ButtonDefaults.buttonColors(
                       backgroundColor = Color.DarkGray
                   ),shape = RoundedCornerShape(5.dp)) {
                       Text(text =  it.name,style = whiteNormal12)
                   }
                   Spacer(modifier = Modifier.width(2.dp))
               }
           }
           Spacer(modifier = Modifier.height(10.dp))
           Divider(modifier = Modifier
               .fillMaxWidth()
               .height(1.dp),color = ColorD3)
       }     
    }

}