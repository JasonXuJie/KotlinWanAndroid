package com.jason.qa.ui.adapter

import android.os.Build
import android.text.Html
import android.text.Spanned
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
import com.jason.qa.R
import com.jason.qa.model.Data
import com.jason.router.Navigator
import com.jason.router.Routes

class QaAdapter : PagingDataAdapter<Data,QaAdapter.ViewHolder>(diffCallback) {

    companion object{
        val diffCallback =  object : DiffUtil.ItemCallback<Data>(){

            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
               return oldItem == newItem
            }

        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.tv_author.text = data.author
        holder.tv_new.isVisible = data.fresh
        holder.tv_site.text = data.tags[0].name
        holder.tv_time.text = data.niceDate
        holder.tv_title.text = data.title
        holder.tv_content.text = fromHtml(data.desc,Html.FROM_HTML_MODE_LEGACY)
        holder.tv_tag.text = "${data.superChapterName}.${data.chapterName}"
        holder.rl_item.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(data.author,data.link))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }


    private fun fromHtml(str:String,flags:Int): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(str, flags)
        } else {
            Html.fromHtml(str)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_qa,parent,false))
    }

    class ViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
        val rl_item = itemView.findViewById<RelativeLayout>(R.id.rl_item)
        val tv_new = itemView.findViewById<TextView>(R.id.tv_new)
        val tv_author = itemView.findViewById<TextView>(R.id.tv_author)
        val tv_site = itemView.findViewById<TextView>(R.id.tv_site)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_content = itemView.findViewById<TextView>(R.id.tv_content)
        val tv_tag = itemView.findViewById<TextView>(R.id.tv_tag)
    }
}