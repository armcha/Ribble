package io.armcha.ribble.domain.entity

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Chatikyan on 02.08.2017.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Shot constructor(val title: String?,
                            var id: String?,
                            var image: Image,
                            var user: User,
                            var description: String? = null,
                            var likesCount: Int? = null,
                            var viewsCount: Int? = null,
                            var bucketCount: Int? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Image::class.java.classLoader),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int)
}