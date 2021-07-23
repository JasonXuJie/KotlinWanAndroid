package com.jason.system.ui.adapter

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.jason.common.config.BundleKey
import com.jason.router.Navigator
import com.jason.router.Routes
import com.jason.system.R
import com.jason.system.model.Children
import com.jason.system.model.SysTree
import java.util.ArrayList
import javax.inject.Inject


class SysAdapter (var list:List<SysTree>):RecyclerView.Adapter<SysAdapter.SysViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SysViewHolder {
       return SysViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sys,parent,false))
    }

    override fun onBindViewHolder(holder: SysViewHolder, position: Int) {
        val data = list[position]
        holder.tv_title.text = data.name
        holder.addItem(data.children,data.name)

    }

    override fun getItemCount(): Int = list.size


    class SysViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val fl_tags = itemView.findViewById<FlexboxLayout>(R.id.fl_tags)


        fun addItem(data:List<Children>,title:String){
            for ((index,itemData) in data.withIndex()){
                val textView  = TextView(itemView.context)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textView.setTextColor(itemView.context.getColor(R.color.black))
                }else{
                    textView.setTextColor(itemView.context.resources.getColor(R.color.black))
                }
                val layoutParams = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.marginEnd = 5
                layoutParams.bottomMargin = 10
                textView.text = itemData.name
                textView.textSize = 12f
                textView.layoutParams = layoutParams
                textView.setBackgroundResource(R.drawable.shape_tag)
                textView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt(BundleKey.SYS_INDEX,index)
                    bundle.putString(BundleKey.SYS_TITLE,title)
                    bundle.putParcelableArrayList(BundleKey.SYS_CHILD,data as ArrayList<out Parcelable>)
                    Navigator.pushByParams(Routes.SYS_ARTICLE,bundle)
                }
                fl_tags.addView(textView)
            }
        }
    }


}