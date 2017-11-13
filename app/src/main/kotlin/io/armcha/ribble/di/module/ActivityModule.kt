package io.armcha.ribble.di.module

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.armcha.ribble.data.cache.MemoryCache
import io.armcha.ribble.data.mapper.Mapper
import io.armcha.ribble.data.network.AuthApiService
import io.armcha.ribble.data.network.ShotApiService
import io.armcha.ribble.data.network.UserApiService
import io.armcha.ribble.data.pref.Preferences
import io.armcha.ribble.data.repository.ShotDataRepository
import io.armcha.ribble.data.repository.UserDataRepository
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.repository.ShotRepository
import io.armcha.ribble.domain.repository.UserRepository

/**
 * Created by Chatikyan on 31.07.2017.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @PerActivity
    @Provides
    fun providesActivity(): AppCompatActivity = activity

    @PerActivity
    @Provides
    fun providesLayoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

    @PerActivity
    @Provides
    fun providesFragmentManager(activity: AppCompatActivity): FragmentManager =
            activity.supportFragmentManager

    @PerActivity
    @Provides
    fun provideShotRepository(shotApiService: ShotApiService, memoryCache: MemoryCache, mapper: Mapper): ShotRepository
            = ShotDataRepository(shotApiService, memoryCache, mapper)

    @PerActivity
    @Provides
    fun provideUserRepository(authApiService: AuthApiService,
                              userApiService: UserApiService,
                              preferences: Preferences,
                              memoryCache: MemoryCache,
                              mapper: Mapper): UserRepository
            = UserDataRepository(authApiService, userApiService, preferences, memoryCache, mapper)

}