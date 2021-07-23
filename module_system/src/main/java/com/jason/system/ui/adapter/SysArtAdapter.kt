package com.jason.system.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.router.Navigator
import com.jason.router.Routes
import com.jason.system.R
import com.jason.system.model.SysArticle

class SysArtAdapter : PagingDataAdapter<SysArticle,SysArtAdapter.SysArtViewHolder>(differCallback) {



    companion object{
        val differCallback = object :  DiffUtil.ItemCallback<SysArticle>(){

            override fun areItemsTheSame(oldItem: SysArticle, newItem: SysArticle): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SysArticle, newItem: SysArticle): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: SysArtViewHolder, position: Int) {
          val itemData = getItem(position) ?: return
          holder.tv_author.text = itemData.shareUser
          holder.tv_time.text = itemData.niceDate
          holder.tv_title.text = itemData.title
          holder.tv_tag.text = "${itemData.superChapterName}.${itemData.chapterName}"
          holder.rl_sys_art.setOnClickListener {
              val bundle = bundleOf()
              bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(itemData.shareUser,itemData.link))
              Navigator.pushByParams(Routes.WEB,bundle)
          }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SysArtViewHolder {
        return SysArtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sys_art,parent,false))
    }


    class SysArtViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_author = itemView.findViewById<TextView>(R.id.tv_author)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_tag = itemView.findViewById<TextView>(R.id.tv_tag)
        val rl_sys_art = itemView.findViewById<RelativeLayout>(R.id.rl_sys_art)
    }


}