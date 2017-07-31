package com.luseen.ribble.di.module

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.luseen.ribble.di.scope.PerActivity
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
}