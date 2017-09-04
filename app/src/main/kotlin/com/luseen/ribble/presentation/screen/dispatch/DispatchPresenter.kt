package com.luseen.ribble.presentation.screen.dispatch

import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.domain.interactor.UserInteractor
import com.luseen.ribble.presentation.base_mvp.base.BasePresenter
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