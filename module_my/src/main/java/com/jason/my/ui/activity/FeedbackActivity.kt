package com.jason.my.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.my.databinding.ActivityFeedbackBinding
import com.jason.my.vm.FeedbackViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = Routes.FEEDBACK)
class FeedbackActivity:BaseActivity<ActivityFeedbackBinding,FeedbackViewModel>() {

    private val feedbackViewModel : FeedbackViewModel by viewModels()

    override fun getBind(): ActivityFeedbackBinding = ActivityFeedbackBinding.inflate(layoutInflater)

    override fun getVM(): FeedbackViewModel = feedbackViewModel

    override fun initViews() {
           setToolBarTitle("用户反馈")
    }

    override fun initData() {

    }
}