package com.luseen.ribble.presentation.screen.shot_root

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
import com.luseen.ribble.presentation.screen.popular_shot.ShotRootContract
import javax.inject.Inject

/**
 * Created by Chatikyan on 06.08.2017.
 */
@PerActivity
class ShotRootPresenter @Inject constructor() : BasePresenter<ShotRootContract.View>(),
        ShotRootContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {

    }
}