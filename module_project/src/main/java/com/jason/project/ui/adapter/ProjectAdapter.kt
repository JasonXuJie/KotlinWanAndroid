package com.jason.project.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jason.project.api.ProjectTab
import com.jason.project.ui.fragment.ProjectListFragment

class ProjectAdapter(private val list:List<ProjectTab>,private val fm :Fragment) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = ProjectListFragment.newInstance(list[position].id)


}