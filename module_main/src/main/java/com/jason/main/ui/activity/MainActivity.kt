package com.jason.main.ui.activity

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jason.common.base.BaseActivity
import com.jason.common.utils.MyToast
import com.jason.main.databinding.ActivityMainBinding
import com.jason.main.ui.adapter.MainIndexAdapter
import com.jason.main.ui.fragment.MainFragment
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess


@AndroidEntryPoint
@Route(path = Routes.MAIN)
class MainActivity:BaseActivity<ActivityMainBinding,Nothing>() {


    private val fragmentList = arrayListOf<Fragment>()
    private var exitTime = 0L
    private val mainIndex = 1
    private lateinit var mainFragment: MainFragment

    override fun getBind(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getVM(): Nothing? = null

    override fun initViews() {
        val squareFragment = ARouter.getInstance().build(Routes.SQUARE).navigation() as Fragment
        mainFragment = MainFragment()
        fragmentList.add(squareFragment)
        fragmentList.add(mainFragment)
        binding.vpMain.adapter = MainIndexAdapter(this,fragmentList)
        binding.vpMain.setCurrentItem(mainIndex,false)
    }

    override fun initData() {

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (binding.vpMain.currentItem != mainIndex){
                binding.vpMain.setCurrentItem(mainIndex,true)
            }else{
                if (!mainFragment.baseFragment.isVisible){
                   mainFragment.backToHome()
                }else{
                    if (System.currentTimeMillis()-exitTime>2000){
                        MyToast.show("再按一次返回键退出程序")
                        exitTime = System.currentTimeMillis()
                    }else{
                        finish()
                        exitProcess(0)
                    }
                }
            }
        }
        return true
    }

}