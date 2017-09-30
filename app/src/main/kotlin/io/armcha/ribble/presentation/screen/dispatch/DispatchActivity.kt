package io.armcha.ribble.presentation.screen.dispatch

import io.armcha.ribble.presentation.base_mvp.base.BaseActivity
import io.armcha.ribble.presentation.screen.auth.AuthActivity
import io.armcha.ribble.presentation.screen.home.HomeActivity
import io.armcha.ribble.presentation.utils.extensions.start
import javax.inject.Inject

class DispatchActivity : BaseActivity<DispatchContract.View, DispatchContract.Presenter>(), DispatchContract.View {

    @Inject
    protected lateinit var dispatchPresenter: DispatchPresenter

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun initPresenter() = dispatchPresenter

    override fun openHomeActivity() {
        start<HomeActivity>()
        finish()
    }

    override fun openLoginActivity() {
        start<AuthActivity>()
        finish()
    }
}
