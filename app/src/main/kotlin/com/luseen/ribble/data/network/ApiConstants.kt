package com.luseen.ribble.data.network

import com.luseen.ribble.BuildConfig

/**
 * Created by Chatikyan on 30.07.2017.
 */
object ApiConstants {

    const val TYPE_POPULAR = "views"

    const val TYPE_RECENT = "recent"

    const val SHOT_ENDPOINT = "https://api.dribbble.com/v1/"

    const val AUTH_ENDPOINT = "https://dribbble.com/oauth/"

    const val TOKEN = BuildConfig.DRIBBBLE_CLIENT_ACCESS_TOKEN

    const val CLIENT_ID = BuildConfig.DRIBBBLE_CLIENT_ID

    const val CLIENT_SECRET = BuildConfig.DRIBBBLE_CLIENT_SECRET

//    const val TOKEN = "<YOUR TOKEN>"
//
//    const val CLIENT_ID = "<YOUR CLIENT ID>"
//
//    const val CLIENT_SECRET = "<YOUR SECRET>"
}