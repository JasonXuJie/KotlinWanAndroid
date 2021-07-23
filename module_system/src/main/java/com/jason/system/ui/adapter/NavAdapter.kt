package com.jason.system.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.router.Navigator
import com.jason.router.Routes
import com.jason.system.R
import com.jason.system.model.Article
import com.jason.system.model.NavData

class NavAdapter(val list:List<NavData>):RecyclerView.Adapter<NavAdapter.NavViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        return NavViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nav,parent,false))
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        val itemData = list[position]
        holder.tv_title.text = itemData.name
        holder.addItem(itemData.articles)
    }

    override fun getItemCount(): Int = list.size


    class NavViewHolder(private val itemView:View) : RecyclerView.ViewHolder(itemView){
       val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
       val fl_tags = itemView.findViewById<FlexboxLayout>(R.id.fl_tags)

        fun addItem(data:List<Article>){
            for (itemData in data){
                val textView  = TextView(itemView.context)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textView.setTextColor(itemView.context.getColor(R.color.black))
                }else{
                    textView.setTextColor(itemView.context.resources.getColor(R.color.black))
                }
                val layoutParams = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.marginEnd = 5
                layoutParams.bottomMargin = 10
                textView.text = itemData.title
                textView.textSize = 12f
                textView.layoutParams = layoutParams
                textView.setBackgroundResource(R.drawable.shape_tag)
                textView.setOnClickListener {
                    val bundle = bundleOf()
                    bundle.putParcelable(BundleKey.WEB_CONTENT,WebContent(itemData.author,itemData.link))
                    Navigator.pushByParams(Routes.WEB,bundle)
                }
                fl_tags.addView(textView)
            }
        }
    }


}