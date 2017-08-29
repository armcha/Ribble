package com.luseen.ribble.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.luseen.ribble.utils.emptyString

/**
 * Created by Chatikyan on 10.08.2017.
 */
class User constructor(val name: String?, val avatarUrl: String = emptyString(), val username: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(avatarUrl)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}