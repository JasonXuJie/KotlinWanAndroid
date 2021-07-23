package com.jason.my.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.base.BaseFragment
import com.jason.common.utils.MyLog
import com.jason.my.R
import com.jason.my.databinding.FragmentMyBinding
import com.jason.my.vm.MyViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = Routes.MY)
class MyFragment : BaseFragment<FragmentMyBinding,MyViewModel>(){

    private val myViewModel : MyViewModel by viewModels()
    private val menus = mutableListOf<String>("常用网站","收藏","反馈","设置")

    override fun getBind(inflater: LayoutInflater, container: ViewGroup?): FragmentMyBinding {
        return FragmentMyBinding.inflate(inflater,container,false)
    }

    override fun getVM(): MyViewModel = myViewModel

    override fun initViews() {
        buildMenuList()
    }

    override fun initData() {

    }


    private fun buildMenuList(){
        for((index,title) in menus.withIndex()){
            val itemView = View.inflate(context, R.layout.item_menu, null)
            val linear_menu = itemView.findViewById<LinearLayout>(R.id.linear_menu)
            val iv_menu = itemView.findViewById<ImageView>(R.id.iv_menu)
            val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
            tv_title.text = title
            when(index){
                0 ->{
                   iv_menu.setImageResource(R.drawable.menu_list_one)
                    linear_menu.setOnClickListener { push(Routes.USED_WEB) }
                }
                1 ->{
                    iv_menu.setImageResource(R.drawable.menu_list_six)
                    linear_menu.setOnClickListener { push(Routes.COLLECT) }
                }
                2->{
                    iv_menu.setImageResource(R.drawable.menu_list_two)
                    linear_menu.setOnClickListener { push(Routes.FEEDBACK) }
                }
                3->{
                    iv_menu.setImageResource(R.drawable.menu_list_seven)
                    linear_menu.setOnClickListener { push(Routes.SETTING) }
                }
            }
            binding.linearMenu.addView(itemView)
        }
    }
}