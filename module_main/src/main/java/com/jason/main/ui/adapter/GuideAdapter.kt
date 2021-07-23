package com.jason.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jason.main.R

class GuideAdapter(private val list:List<Int>) : RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        return GuideViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_guide,parent,false))
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.iv_bg.setImageResource(list[position])
    }

    override fun getItemCount(): Int = list.size



     class GuideViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
         val iv_bg = itemView.findViewById<ImageView>(R.id.iv_bg)

     }




}