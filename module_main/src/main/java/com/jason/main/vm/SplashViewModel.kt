package com.jason.main.vm

import com.jason.common.base.BaseViewModel
import com.jason.common.config.SpKey
import com.jason.common.utils.SpUtil
import com.jason.router.Navigator
import com.jason.router.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor():BaseViewModel() {


    fun openToGuide(){
        if (SpUtil.get(SpKey.IS_FIRST,true)as Boolean) Navigator.push(Routes.GUIDE) else  Navigator.push(Routes.MAIN)

    }
}