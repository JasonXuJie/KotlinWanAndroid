package com.jason.system.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jason.system.model.Children
import com.jason.system.ui.fragment.SysArtFragment

class ArtIndexAdapter(activity: FragmentActivity,val list:ArrayList<Children>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment  = SysArtFragment.newInstance(list[position].id)
}