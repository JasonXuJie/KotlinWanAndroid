package com.jason.system.vm

import androidx.lifecycle.MutableLiveData
import com.jason.common.base.BaseViewModel
import com.jason.net.bean.ResState
import com.jason.system.model.SysRepo
import com.jason.system.model.SysTree
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SystemViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var repo:SysRepo

    val sysTree = MutableLiveData<List<SysTree>>()
    val errMsg = MutableLiveData<String>()


    fun loadSysTree(){
        launch({
           val state =  repo.loadSysTree()
           if (state is ResState.Success){
               sysTree.postValue(state.data)
           }else if (state is ResState.Fail){
               errMsg.postValue(state.errorMsg)
           }
        })
    }
}