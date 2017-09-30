package io.armcha.ribble.presentation.screen.shot_root

import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.presentation.base_mvp.base.BasePresenter
import io.armcha.ribble.presentation.screen.shot.ShotRootContract
import javax.inject.Inject

/**
 * Created by Chatikyan on 06.08.2017.
 */
@PerActivity
class ShotRootPresenter @Inject constructor() : BasePresenter<ShotRootContract.View>(),
        ShotRootContract.Presenter