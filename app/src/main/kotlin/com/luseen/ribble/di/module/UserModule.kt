package com.luseen.ribble.di.module

import com.luseen.ribble.data.mapper.UserMapper
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.data.network.TokenApiService
import com.luseen.ribble.di.scope.PerUser
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Chatikyan on 10.08.2017.
 */
@Module
class UserModule {

    @PerUser
    @Provides
    @Named("authEndpoint")
    fun authEndpoint(): String {
        return ApiConstants.AUTH_ENDPOINT
    }

    @PerUser
    @Provides
    @Named("authRetrofit")
    fun provideUserRetrofit(retrofitBuilder: Retrofit.Builder,
                            @Named("authEndpoint") baseUrl: String): Retrofit {
        return retrofitBuilder
                .baseUrl(baseUrl)
                .build()
    }

    @PerUser
    @Provides
    fun provideAuthApiService(@Named("authRetrofit") retrofit: Retrofit): TokenApiService {
        return retrofit.create(TokenApiService::class.java)
    }

    @PerUser
    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()
}