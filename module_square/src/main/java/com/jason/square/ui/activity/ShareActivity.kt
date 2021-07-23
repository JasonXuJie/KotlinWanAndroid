package com.jason.square.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjq.toast.ToastUtils
import com.jason.common.base.BaseActivity
import com.jason.router.Routes
import com.jason.square.databinding.ActivityShareBinding
import com.jason.square.vm.ShareViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = Routes.SHARE)
class ShareActivity : BaseActivity<ActivityShareBinding,ShareViewModel>() {

    private val shareViewModel : ShareViewModel by  viewModels()

    override fun getBind(): ActivityShareBinding = ActivityShareBinding.inflate(layoutInflater)


    override fun getVM(): ShareViewModel = shareViewModel

    override fun initViews() {
        setToolBarTitle("分享文章")
        binding.tvRefreshTitle.setOnClickListener { binding.etTitle.editableText.clear() }
        binding.tvOpenLink.setOnClickListener {  }
        binding.btnShare.setOnClickListener { viewModel?.share(binding.etTitle.editableText.toString(),binding.etLink.editableText.toString()) }
        viewModel?.errMsg?.observe(this,{
            ToastUtils.show(it)
        })
        viewModel?.success?.observe(this,{
            ToastUtils.show("分享成功")
            finish()

        })
    }

    override fun initData() {

    }

}