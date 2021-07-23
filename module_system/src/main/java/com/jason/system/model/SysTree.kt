package com.jason.system.model

import android.os.Parcel
import android.os.Parcelable

data class SysTree(
    val children: List<Children>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class Children(
    //val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable {

    constructor(parcel: Parcel) : this(
        //parcel.readByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toBoolean(),
        parcel.readInt()) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeString(userControlSetTop.toString())
        parcel.writeInt(visible)
    }

    companion object CREATOR : Parcelable.Creator<Children> {
        override fun createFromParcel(parcel: Parcel): Children {
            return Children(parcel)
        }

        override fun newArray(size: Int): Array<Children?> {
            return arrayOfNulls(size)
        }
    }
}