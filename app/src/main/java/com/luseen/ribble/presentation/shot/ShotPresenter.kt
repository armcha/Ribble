package com.luseen.ribble.presentation.shot

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.logger.Logger
import com.luseen.ribble.data.network.ApiService
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 01.08.2017.
 */
@PerActivity
class ShotPresenter private constructor() : BasePresenter<ShotContract.View>(), ShotContract.Presenter {

    private lateinit var apiService: ApiService

    @Inject
    constructor(apiService: ApiService) : this() {
        this.apiService = apiService
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun create() {
        Logger.log(apiService)
    }
}