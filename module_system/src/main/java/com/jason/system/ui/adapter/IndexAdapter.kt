package com.jason.system.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jason.system.ui.fragment.NavFragment
import com.jason.system.ui.fragment.SystemFragment

class IndexAdapter(private val fm:Fragment,val size:Int) : FragmentStateAdapter(fm){

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment = if (position ==0) SystemFragment() else NavFragment()
}