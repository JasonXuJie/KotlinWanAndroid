package com.jason.main.ui.activity

import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.main.R
import com.jason.main.databinding.ActivitySplashBinding
import com.jason.main.vm.SplashViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.SPLASH)
class SplashActivity :BaseActivity<ActivitySplashBinding,SplashViewModel>() {

    private lateinit var animation:Animation
    private val splashViewModel : SplashViewModel by viewModels()

    override fun getBind(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun getVM(): SplashViewModel = splashViewModel

    override fun initViews() {
        animation = AnimationUtils.loadAnimation(this,R.anim.anim_rotate)
        binding.ivIcon.startAnimation(animation)
        animation.setAnimationListener(object:Animation.AnimationListener{

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                viewModel!!.openToGuide()
                finish()

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.ivIcon.clearAnimation()
        animation.cancel()
    }

}