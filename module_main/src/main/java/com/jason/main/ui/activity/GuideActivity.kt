package com.jason.main.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.R
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyLog
import com.jason.main.databinding.ActivityGuideBinding
import com.jason.main.ui.adapter.GuideAdapter
import com.jason.main.vm.GuideViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.GUIDE)
class GuideActivity:BaseActivity<ActivityGuideBinding,GuideViewModel>() {

    private val guideViewModel:GuideViewModel by viewModels()
    private val pics = mutableListOf(R.drawable.img_guide_one,R.drawable.img_guide_two,R.drawable.img_guide_three,R.drawable.img_guide_four)


    override fun getBind(): ActivityGuideBinding = ActivityGuideBinding.inflate(layoutInflater)

    override fun getVM(): GuideViewModel = guideViewModel

    override fun initViews() {
        binding.vpGuide.adapter = GuideAdapter(pics)
        binding.vpGuide.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               if (position == pics.size -1){
                   binding.btnSure.visibility = View.VISIBLE
               }else{
                   binding.btnSure.visibility = View.GONE
               }
            }

        })
        binding.btnSure.setOnClickListener { viewModel?.jumpToMain() }
    }

    override fun initData() {

    }
}