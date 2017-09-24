package com.luseen.ribble.domain.entity

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.luseen.ribble.presentation.utils.extensions.emptyString
import kotlinx.android.parcel.Parcelize

/**
 * Created by Chatikyan on 28.08.2017.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Image(val small: String = emptyString,
                 val normal: String = emptyString,
                 val big: String = emptyString) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())
}