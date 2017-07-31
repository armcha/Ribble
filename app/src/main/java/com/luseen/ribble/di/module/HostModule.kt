package com.luseen.ribble.di.module

import com.luseen.ribble.data.network.ApiConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chatikyan on 31.07.2017.
 */

@Module
class HostModule {

    @Singleton
    @Provides
    fun baseUrl():String{
        return ApiConstants.ENDPOINT
    }
}