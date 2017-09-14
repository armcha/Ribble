package com.luseen.ribble.presentation.screen.about

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 06.08.2017.
 */
@PerActivity
class AboutPresenter @Inject constructor() : BasePresenter<AboutContract.View>(),
        AboutContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {

    }
}