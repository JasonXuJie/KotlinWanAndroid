package com.jason.common.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.R

class FooterAdapter(val retry:()->Unit):LoadStateAdapter<FooterAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.run {
            pb_loading.isVisible = loadState is LoadState.Loading
            btn_retry.isVisible = loadState is LoadState.Error
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer,parent,false)
        val holder = ViewHolder(view)
        holder.btn_retry.setOnClickListener { retry() }
        return holder
    }


    class ViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
        val pb_loading = itemView.findViewById<ProgressBar>(R.id.pb_loading)
        val btn_retry  = itemView.findViewById<Button>(R.id.btn_retry)
    }


}