package com.luseen.ribble.presentation.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Chatikyan on 02.08.2017.
 */
data class Shot constructor(val title: String?, var id: String?) : Parcelable {

    var imageUrl: String? = null
    var description: String? = null
    var likesCount: Int? = null

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
        imageUrl = parcel.readString()
        description = parcel.readString()
        likesCount = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(id)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeValue(likesCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shot> {
        override fun createFromParcel(parcel: Parcel): Shot {
            return Shot(parcel)
        }

        override fun newArray(size: Int): Array<Shot?> {
            return arrayOfNulls(size)
        }
    }

}