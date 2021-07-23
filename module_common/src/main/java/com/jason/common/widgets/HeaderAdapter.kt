package com.jason.common.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.R

class HeaderAdapter(val refresh:()->Unit):LoadStateAdapter<HeaderAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header,parent,false))
    }


    class ViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){

    }


}