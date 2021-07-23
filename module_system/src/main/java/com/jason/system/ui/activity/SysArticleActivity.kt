package com.jason.system.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.jason.common.base.BaseActivity
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.router.Routes
import com.jason.system.databinding.ActivitySysArticleBinding
import com.jason.system.model.Children
import com.jason.system.ui.adapter.ArtIndexAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Routes.SYS_ARTICLE)
class SysArticleActivity : BaseActivity<ActivitySysArticleBinding,Nothing>() {


    override fun getBind(): ActivitySysArticleBinding = ActivitySysArticleBinding.inflate(layoutInflater)


    override fun getVM(): Nothing? = null

    override fun initViews() {
        intent.extras?.let {
            val index = it.getInt(BundleKey.SYS_INDEX)
            val title = it.getString(BundleKey.SYS_TITLE,"")
            val children = it.getParcelableArrayList<Children>(BundleKey.SYS_CHILD)!!
            setToolBarTitle(title)
            binding.vpSysArt.adapter = ArtIndexAdapter(this,children)
            MyLog.e(children[0].name)
            TabLayoutMediator(binding.tbSysArt,binding.vpSysArt){
                tab,pos-> tab.text = children[pos].name
            }.attach()
            binding.tbSysArt.apply {
                selectTab(getTabAt(index))
            }

        }
    }

    override fun initData() {

    }

}