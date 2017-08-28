package com.luseen.ribble.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.luseen.ribble.utils.emptyString

/**
 * Created by Chatikyan on 28.08.2017.
 */
data class Image(val small: String = emptyString(),
                 val normal: String = emptyString(),
                 val big: String = emptyString()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(small)
        parcel.writeString(normal)
        parcel.writeString(big)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}