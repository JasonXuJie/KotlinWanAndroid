package com.jason.my.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.my.databinding.ActivitySettingBinding
import com.jason.my.vm.SettingViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.SETTING)
class SettingActivity:BaseActivity<ActivitySettingBinding,SettingViewModel>() {

    private val settingViewModel : SettingViewModel by viewModels()

    override fun getBind(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    override fun getVM(): SettingViewModel = settingViewModel

    override fun initViews() {
        setToolBarTitle("设置")
    }

    override fun initData() {

    }
}