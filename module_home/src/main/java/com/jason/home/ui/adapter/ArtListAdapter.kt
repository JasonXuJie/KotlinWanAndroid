package com.jason.home.ui.adapter

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
import com.jason.home.R
import com.jason.home.model.Article
import com.jason.router.Navigator
import com.jason.router.Routes

class ArtListAdapter:PagingDataAdapter<Article,ArtListAdapter.ArticleViewHolder>(differCallback) {


    companion object{
        val differCallback = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val itemData = getItem(position) ?: return
        holder.tv_new.visibility = if (itemData.fresh) View.VISIBLE else View.GONE
        holder.tv_author.text = itemData.shareUser
        holder.tv_time.text = itemData.niceDate
        holder.tv_title.text  = itemData.title
        holder.tv_tag.text = "${itemData.superChapterName}.${itemData.chapterName}"
        holder.item_art.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(itemData.shareUser,itemData.link))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_art_list ,parent,false))
    }


    class ArticleViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_new = itemView.findViewById<TextView>(R.id.tv_new)
        val tv_author = itemView.findViewById<TextView>(R.id.tv_author)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_tag = itemView.findViewById<TextView>(R.id.tv_tag)
        val item_art = itemView.findViewById<RelativeLayout>(R.id.item_art)
    }


}