package com.luseen.ribble.presentation.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.logger.Logger
import com.luseen.ribble.data.network.ApiService
import com.luseen.ribble.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 31.07.2017.
 */
class HomePresenter private constructor() : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private lateinit var apiService: ApiService

    @Inject
    constructor(apiService: ApiService) : this() {
        this.apiService = apiService
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Logger.log(apiService)
    }

}