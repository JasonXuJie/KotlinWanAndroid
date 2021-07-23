package com.jason.square.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.router.Navigator
import com.jason.router.Routes
import com.jason.square.R
import com.jason.square.model.ShareArticle

class ShareOneAdapter:PagingDataAdapter<ShareArticle,ShareOneAdapter.ShareOneViewHolder>(differCallback) {

    companion object{
        val differCallback = object : DiffUtil.ItemCallback<ShareArticle>(){
            override fun areItemsTheSame(oldItem: ShareArticle, newItem: ShareArticle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShareArticle, newItem: ShareArticle): Boolean {
               return oldItem  == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ShareOneViewHolder, position: Int) {
        val itemData = getItem(position) ?: return
        holder.tv_sharer.text = itemData.shareUser
        holder.tv_new.isVisible  = itemData.fresh
        holder.tv_time.text = itemData.niceDate
        holder.tv_title.text = itemData.title
        holder.tv_tag.text = "${itemData.superChapterName}.${itemData.chapterName}"
        holder.rl_sharer.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(itemData.shareUser,itemData.link))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareOneViewHolder {
       return ShareOneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_share_one,parent,false))
    }





    class ShareOneViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_sharer = itemView.findViewById<TextView>(R.id.tv_sharer)
        val tv_new = itemView.findViewById<TextView>(R.id.tv_new)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_tag = itemView.findViewById<TextView>(R.id.tv_tag)
        val rl_sharer = itemView.findViewById<RelativeLayout>(R.id.rl_sharer)
    }


}