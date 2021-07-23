package com.jason.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainIndexAdapter(activity:FragmentActivity,val list:ArrayList<Fragment>) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int  = list.size

    override fun createFragment(position: Int): Fragment  = list[position]

}