package com.jason.home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jason.common.utils.MyLog
import com.jason.home.R
import com.jason.home.model.Article
import com.jason.home.model.BannerData
import com.jason.home.model.TopData
import com.youth.banner.Banner
import kotlin.properties.Delegates

class HomeAdapter(var bannerList:List<BannerData>,var topList:List<TopData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val BANNER_TYPE = 0
        const val TOP_TYPE = 1
        const val ART_TYPE = 2
    }

    var curType by Delegates.notNull<Int>()

    val artListAdapter = ArtListAdapter()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(position){
            BANNER_TYPE->{
                val bannerHolder = holder as BannerViewHolder
                bannerHolder.iv_scan.setOnClickListener {  }
                bannerHolder.iv_search.setOnClickListener {  }
                bannerHolder.banner.setAdapter(ImageAdapter(bannerList))
            }
            TOP_TYPE->{
                val topHolder  = holder as TopViewHolder
                topHolder.rv_top.adapter = TopListAdapter(topList)
            }
            ART_TYPE->{
                val artHolder = holder  as ArtViewHolder
                artHolder.rv_recommend.adapter = artListAdapter
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            BANNER_TYPE->{
               return BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner,parent,false))
            }
            TOP_TYPE->{
               return TopViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top,parent,false))
            }
            ART_TYPE->{
               return ArtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_art,parent,false))

            }
        }
        throw RuntimeException("viewType is not found")
    }


    override fun getItemViewType(position: Int): Int {
        when(position){
            BANNER_TYPE -> curType = BANNER_TYPE
            TOP_TYPE -> curType = TOP_TYPE
            ART_TYPE -> curType = ART_TYPE
        }
        return curType
    }

    override fun getItemCount(): Int = 3




    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iv_scan = itemView.findViewById<ImageView>(R.id.iv_scan)
        val iv_search = itemView.findViewById<ImageView>(R.id.iv_search)
        val banner = itemView.findViewById<Banner<BannerData,ImageAdapter>>(R.id.banner)

    }

    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       val rv_top = itemView.findViewById<RecyclerView>(R.id.rv_top)

       init {
           rv_top.apply {
               layoutManager = LinearLayoutManager(itemView.context)
               setHasFixedSize(true)
           }
       }
    }

    class ArtViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val rv_recommend = itemView.findViewById<RecyclerView>(R.id.rv_recommend)

        init {
            rv_recommend.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                setHasFixedSize(true)
            }
        }
    }

    fun submitBannerList(data:List<BannerData>){
        bannerList = data
        notifyItemChanged(BANNER_TYPE)
    }

    fun submitTopList(data:List<TopData>){
        topList = data
        notifyItemChanged(TOP_TYPE)
    }

    suspend fun submit(data:PagingData<Article>){
        artListAdapter.submitData(data)
        notifyItemChanged(ART_TYPE)
    }




}