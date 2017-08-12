package com.luseen.ribble.presentation.screen.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.screen.home.HomeActivity
import com.luseen.ribble.utils.log
import com.luseen.ribble.utils.showToast
import com.luseen.ribble.utils.start
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : BaseActivity<AuthContract.View, AuthContract.Presenter>(), AuthContract.View {

    @Inject
    protected lateinit var authPresenter: AuthPresenter

    override fun initPresenter(): AuthContract.Presenter = authPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        login.setOnClickListener {
            presenter.makeLogin()
        }
    }

    override fun startOAuthIntent(uri: Uri) {
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    override fun openHomeActivity() {
        start {
            HomeActivity::class.java
        }
        finish()
        showToast("Logged in!!")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        presenter.checkLogin(intent)
        log("onNewIntent$intent")
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }
}
