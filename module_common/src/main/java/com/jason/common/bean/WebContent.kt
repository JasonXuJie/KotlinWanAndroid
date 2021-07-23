package com.jason.common.bean

import android.os.Parcel
import android.os.Parcelable

data class WebContent(val author:String?,val url:String?):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(author)
        parcel.writeString(url)
    }

    companion object CREATOR : Parcelable.Creator<WebContent> {
        override fun createFromParcel(parcel: Parcel): WebContent {
            return WebContent(parcel)
        }

        override fun newArray(size: Int): Array<WebContent?> {
            return arrayOfNulls(size)
        }
    }
}
