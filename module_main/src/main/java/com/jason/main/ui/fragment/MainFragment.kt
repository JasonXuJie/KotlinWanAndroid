package com.jason.main.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.jason.common.base.BaseFragment
import com.jason.main.R
import com.jason.main.databinding.FragmentMainBinding
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment:BaseFragment<FragmentMainBinding,Nothing>() {

    private val fragmentList = arrayListOf<Fragment>()
    private var mPreFragmentFlag = 0
    lateinit var baseFragment : Fragment
    private var exitTime = 0L

    //private lateinit var navController: NavController
    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater,container,false)
    }

    override fun getVM(): Nothing? = null

    override fun initViews() {
        initPage()
        //binding.navBottom.setupWithNavController()
        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //navController = navHostFragment.navController
        //binding.navBottom.setupWithNavController(navController)
        binding.navBottom.menu.getItem(0).isChecked = true
        binding.navBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_home -> {
                    //navController.navigate(R.id.home_fragment)
                    showAndHide(fragmentList[0],fragmentList[mPreFragmentFlag])
                    mPreFragmentFlag = 0
                }
                R.id.item_qa -> {
                    //navController.navigate(R.id.qa_fragment)
                    showAndHide(fragmentList[1],fragmentList[mPreFragmentFlag])
                    mPreFragmentFlag = 1
                }
                R.id.item_sys -> {
                    //navController.navigate(R.id.sys_fragment)
                    showAndHide(fragmentList[2],fragmentList[mPreFragmentFlag])
                    mPreFragmentFlag = 2
                }
                R.id.item_project -> {
                    //navController.navigate(R.id.pro_fragment)
                    showAndHide(fragmentList[3],fragmentList[mPreFragmentFlag])
                    mPreFragmentFlag = 3
                }
                R.id.item_my -> {
                    //navController.navigate(R.id.my_fragment)
                    showAndHide(fragmentList[4],fragmentList[mPreFragmentFlag])
                    mPreFragmentFlag = 4
                }
            }
            true
        }
    }


    private fun initPage(){
        baseFragment = ARouter.getInstance().build(Routes.HOME).navigation() as Fragment
        val qaFragment = ARouter.getInstance().build(Routes.QA).navigation() as Fragment
        val sysFragment = ARouter.getInstance().build(Routes.SYSTEM).navigation() as  Fragment
        val proFragment = ARouter.getInstance().build(Routes.PROJECT).navigation() as Fragment
        val myFragment = ARouter.getInstance().build(Routes.MY).navigation() as Fragment
        fragmentList.add(baseFragment)
        fragmentList.add(qaFragment)
        fragmentList.add(sysFragment)
        fragmentList.add(proFragment)
        fragmentList.add(myFragment)
        val transaction = childFragmentManager.beginTransaction()
        for ((index,fragment) in fragmentList.withIndex()){
            transaction.add(R.id.fg_container,fragment,fragment.javaClass.simpleName)
            if (index != 0){
                transaction.hide(fragment)
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun showAndHide(show:Fragment,hide:Fragment){
        val transaction = childFragmentManager.beginTransaction()
        if (show != hide){
            transaction.show(show).hide(hide).commitAllowingStateLoss()
        }
    }

    fun backToHome(){
        showAndHide(fragmentList[0],fragmentList[mPreFragmentFlag])
        mPreFragmentFlag = 0
        binding.navBottom.menu.getItem(0).isChecked = true
    }


    override fun initData() {

    }

}