package com.jason.home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.home.R
import com.jason.home.model.TopData
import com.jason.router.Navigator
import com.jason.router.Routes

class TopListAdapter(private val list:List<TopData>):RecyclerView.Adapter<TopListAdapter.TopListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListViewHolder {
       return TopListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_list,parent,false))
    }

    override fun onBindViewHolder(holder: TopListViewHolder, position: Int) {
        val itemData = list[position]
        holder.tv_new.visibility = if (itemData.fresh) View.VISIBLE else View.GONE
        holder.tv_author.text = itemData.author
        holder.tv_site.text = if (itemData.tags.isEmpty()) "" else itemData.tags[0].name
        holder.tv_time.text = itemData.niceDate
        holder.tv_title.text = itemData.title
        holder.tv_tag.text = "${itemData.superChapterName}.${itemData.chapterName}"
        holder.item_top.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(itemData.shareUser,itemData.link))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }

    override fun getItemCount(): Int = list.size



    class TopListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_new = itemView.findViewById<TextView>(R.id.tv_new)
        val tv_author = itemView.findViewById<TextView>(R.id.tv_author)
        val tv_site = itemView.findViewById<TextView>(R.id.tv_site)
        val tv_time  = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_tag = itemView.findViewById<TextView>(R.id.tv_tag)
        val item_top = itemView.findViewById<RelativeLayout>(R.id.item_top)
    }


}