package com.jason.my.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.my.databinding.ActivityUsedWebBinding
import com.jason.my.vm.UsedWebViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.USED_WEB)
class UsedWebActivity:BaseActivity<ActivityUsedWebBinding,UsedWebViewModel>() {

    private val usedWebViewModel : UsedWebViewModel by viewModels()

    override fun getBind(): ActivityUsedWebBinding = ActivityUsedWebBinding.inflate(layoutInflater)

    override fun getVM(): UsedWebViewModel = usedWebViewModel

    override fun initViews() {
        setToolBarTitle("常用网站")
    }

    override fun initData() {

    }
}