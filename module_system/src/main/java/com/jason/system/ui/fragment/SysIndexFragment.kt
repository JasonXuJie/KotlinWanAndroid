package com.jason.system.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jason.common.base.BaseFragment
import com.jason.common.utils.MyLog
import com.jason.router.Routes
import com.jason.system.databinding.FragmentSystemIndexBinding
import com.jason.system.ui.adapter.IndexAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = Routes.SYSTEM)
class SysIndexFragment : BaseFragment<FragmentSystemIndexBinding,Nothing>() {

    private val tabs = listOf("体系","导航")

    override fun getBind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSystemIndexBinding {
        return FragmentSystemIndexBinding.inflate(inflater,container,false)
    }

    override fun getVM(): Nothing? = null

    override fun initViews() {
        binding.vpSys.adapter = IndexAdapter(this,tabs.size)
        TabLayoutMediator(binding.tlSys,binding.vpSys
        ) { tab, pos ->
            tab.text = tabs[pos]

            //binding.imgSearch.isVisible = (pos == 0)
        }.attach()
        binding.tlSys.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                 binding.imgSearch.isVisible = (tab!!.position == 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        binding.vpSys.registerOnPageChangeCallback(callBack)
    }

    val callBack = object : ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.imgSearch.isVisible = (position == 0)
        }
    }

    override fun initData() {

    }

    override fun onDestroyView() {
        binding.vpSys.unregisterOnPageChangeCallback(callBack)
        super.onDestroyView()
    }
}