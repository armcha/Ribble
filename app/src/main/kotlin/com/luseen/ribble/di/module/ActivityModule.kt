package com.luseen.ribble.di.module

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.fetcher.Fetcher
import dagger.Module
import dagger.Provides

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
    fun providesLayoutInflater(activity: AppCompatActivity): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @PerActivity
    @Provides
    fun providesFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @PerActivity
    @Provides
    fun provideFetcher(): Fetcher = Fetcher()
}