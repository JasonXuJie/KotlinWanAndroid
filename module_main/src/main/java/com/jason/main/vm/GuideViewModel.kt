package com.jason.main.vm

import com.jason.common.base.BaseViewModel
import com.jason.common.config.SpKey
import com.jason.common.utils.SpUtil
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor():BaseViewModel() {


    fun jumpToMain(){
        SpUtil.put(SpKey.IS_FIRST,false)
        Navigator.push(Routes.MAIN)
    }
}