package com.jason.my.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jason.common.base.BaseViewModel
import com.jason.common.utils.MyLog
import com.jason.common.utils.MyToast
import com.jason.common.widgets.LoadState
import com.jason.my.model.Classify
import com.jason.my.model.WebData
import com.jason.my.model.WebRepo
import com.jason.net.bean.ResState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.stream.Collector
import javax.inject.Inject

@HiltViewModel
class UsedWebViewModel @Inject constructor():BaseViewModel() {

    //@Inject
    //lateinit var repo:WebRepo
    val repo by lazy { WebRepo() }

    var loadingState by mutableStateOf(LoadState.LOADING)
    var dataStateList by mutableStateOf(listOf<Classify>())

    init {
        loadData()
    }


    fun loadData(){
        launch({
            val state = repo.loadWebData()
            if (state is ResState.Success){
                val dataList = state.data
                val map = mutableMapOf<String,List<WebData>>()
                dataList.forEach {
                    val element = it
                    if (!map.containsKey(element.category)){
                        map[element.category] = dataList.filter { element.category == it.category }
                    }
                }
                val list = mutableListOf<Classify>()
                map.forEach {
                    val data = Classify(it.key,it.value)
                    list.add(data)
                }
                dataStateList = list
                loadingState = LoadState.SUCCESS
            }else{
                MyToast.show((state as ResState.Fail).errorMsg)
                loadingState = LoadState.FAIL
            }
        })
    }
}