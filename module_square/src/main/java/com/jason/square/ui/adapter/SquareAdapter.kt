package com.jason.square.ui.adapter

import android.os.Bundle
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
import com.alibaba.android.arouter.launcher.ARouter
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.router.Navigator
import com.jason.router.Routes
import com.jason.square.R
import com.jason.square.model.SquareItem

class SquareAdapter: PagingDataAdapter<SquareItem,SquareAdapter.ViewHolder>(diffCallback) {


    companion object{
        val diffCallback = object: DiffUtil.ItemCallback<SquareItem>(){
            override fun areItemsTheSame(oldItem: SquareItem, newItem: SquareItem): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SquareItem, newItem: SquareItem): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = getItem(position) ?: return
       holder.tv_new.isVisible = item.fresh
       holder.tv_share_one.text = item.shareUser
       holder.tv_time.text = item.niceDate
       holder.tv_title.text = item.title
       holder.tv_tag.text = "${item.superChapterName}.${item.chapterName}"
       holder.tv_share_one.setOnClickListener {
           val bundle = Bundle()
           bundle.putInt(BundleKey.USER_ID,item.userId)
           Navigator.pushByParams(Routes.SHARE_ONE,bundle)
       }
       holder.rl_item.setOnClickListener {
           val bundle = bundleOf()
           bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(item.shareUser,item.link))
           Navigator.pushByParams(Routes.WEB,bundle)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_square,parent,false))
    }


    class ViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_new = itemView.findViewById<TextView>(R.id.tv_new)
        val tv_share_one  = itemView.findViewById<TextView>(R.id.tv_share_one)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title  = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_tag =  itemView.findViewById<TextView>(R.id.tv_tag)
        val rl_item = itemView.findViewById<RelativeLayout>(R.id.rl_item)
    }


}