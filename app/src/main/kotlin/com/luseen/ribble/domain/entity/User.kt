package com.luseen.ribble.domain.entity

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.luseen.ribble.utils.emptyString
import kotlinx.android.parcel.Parcelize

/**
 * Created by Chatikyan on 10.08.2017.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class User constructor(val name: String?,
                       val avatarUrl: String = emptyString(),
                       val username: String?,
                       val location: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())
}