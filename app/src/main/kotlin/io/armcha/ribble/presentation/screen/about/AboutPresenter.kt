package io.armcha.ribble.presentation.screen.about

import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.presentation.base_mvp.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 06.08.2017.
 */
@PerActivity
class AboutPresenter @Inject constructor() : BasePresenter<AboutContract.View>(),
        AboutContract.Presenter {
}