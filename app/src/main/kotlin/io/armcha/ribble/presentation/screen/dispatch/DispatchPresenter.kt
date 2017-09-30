package io.armcha.ribble.presentation.screen.dispatch

import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.interactor.UserInteractor
import io.armcha.ribble.presentation.base_mvp.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 11.08.2017.
 */
@PerActivity
class DispatchPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : BasePresenter<DispatchContract.View>(), DispatchContract.Presenter {

    override fun onPresenterCreate() {
        super.onPresenterCreate()
        if (userInteractor.isUserLoggedIn()) {
            view?.openHomeActivity()
        } else {
            view?.openLoginActivity()
        }
    }
}