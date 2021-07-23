package com.jason.project.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.common.utils.MyLog
import com.jason.project.R
import com.jason.project.model.Project
import com.jason.router.Navigator
import com.jason.router.Routes

class ProjectListAdapter:PagingDataAdapter<Project,ProjectListAdapter.ListViewHolder>(diffCallback) {

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<Project>(){

            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem == newItem
            }
        }
    }



    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val project = getItem(position) ?: return
        holder.tv_author.text = "作者:${project.author}"
        holder.tv_content.text = project.desc
        holder.tv_likenum.text = project.zan.toString()
        holder.iv_cover.load(project.envelopePic){
            placeholder(R.drawable.img_placeholder)
        }
        holder.rl_item.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT,WebContent(project.author,project.projectLink))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project,parent,false))
    }



     class ListViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
          val iv_cover = itemView.findViewById<ImageView>(R.id.iv_cover)
          val tv_author = itemView.findViewById<TextView>(R.id.tv_author)
          val tv_content = itemView.findViewById<TextView>(R.id.tv_content)
          val tv_likenum = itemView.findViewById<TextView>(R.id.tv_like_num)
          val rl_item = itemView.findViewById<RelativeLayout>(R.id.rl_item)
    }
}