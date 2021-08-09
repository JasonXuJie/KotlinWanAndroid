package com.jason.my.model

data class WebData(
    val category: String,
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)



data class Classify(val category:String,val list:List<WebData>)