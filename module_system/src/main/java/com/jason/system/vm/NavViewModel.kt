package com.jason.system.vm

import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.net.bean.ResState
import com.jason.system.model.NavData
import com.jason.system.model.NavRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NavViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:NavRepo

    val data = MutableLiveData<List<NavData>>()
    val errMsg = MutableLiveData<String>()


    fun loadNavList(){
        launch({
            val state = repo.loadNavTree()
            if (state is ResState.Success){
                data.postValue(state.data)
            }else if (state is ResState.Fail){
                errMsg.postValue(state.errorMsg)
            }
        })
    }


}