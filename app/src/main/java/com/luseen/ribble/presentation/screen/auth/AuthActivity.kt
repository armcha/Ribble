package com.luseen.ribble.presentation.screen.auth

import android.content.Intent
import android.os.Bundle
import com.luseen.ribble.R
import com.luseen.ribble.data.network.ApiConstants
import com.luseen.ribble.di.component.UserComponent
import com.luseen.ribble.di.module.UserModule
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.utils.log
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : BaseActivity<AuthContract.View, AuthContract.Presenter>(), AuthContract.View {

    @Inject
    protected lateinit var authPresenter: AuthPresenter

    protected var userComponent: UserComponent? = null

    override fun initPresenter(): AuthContract.Presenter = authPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        login.setOnClickListener {
            presenter.makeLogin()
        }

        val LOGIN_URL = ApiConstants.AUTH_ENDPOINT + "/authorize?client_id " + ApiConstants.CLIENT_ID +
                "&redirect_uri=ribbble%3A%2F%2Fdribbble-auth-callback&scope=public+write+comment+upload"

        //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LOGIN_URL)))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log("Result")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        log("onNewIntent$intent")
    }

    override fun injectDependencies() {
        userComponent = activityComponent.plus(UserModule())
        userComponent?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            userComponent = null
        }
    }
}
