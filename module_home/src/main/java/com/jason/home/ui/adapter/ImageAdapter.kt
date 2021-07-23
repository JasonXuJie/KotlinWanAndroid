package com.jason.home.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.home.model.BannerData
import com.jason.router.Navigator
import com.jason.router.Routes
import com.youth.banner.adapter.BannerAdapter

class ImageAdapter(private val list:List<BannerData>) : BannerAdapter<BannerData,ImageAdapter.ViewHolder>(list) {


    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val img_banner = ImageView(parent.context)
        img_banner.setScaleType(ImageView.ScaleType.FIT_XY)
        img_banner.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        return ViewHolder(img_banner)
    }

    override fun onBindView(holder: ViewHolder, data: BannerData?, position: Int, size: Int) {
        val itemData = list[position]
        holder.img_banner.load(itemData.imagePath)
        holder.img_banner.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(null,itemData.url))
            Navigator.pushByParams(Routes.WEB,bundle)
        }
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val img_banner:ImageView = itemView as ImageView
    }


}