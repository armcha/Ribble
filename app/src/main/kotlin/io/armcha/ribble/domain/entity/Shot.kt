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
                            var bucketCount: Int? = null) : Parcelable