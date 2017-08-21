package com.luseen.ribble.presentation.screen.home

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.screen.TESTFragment
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import com.luseen.ribble.presentation.screen.recent_shot.RecentShotFragment
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import com.luseen.ribble.presentation.screen.user_likes.UserLikesFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationItem
import com.luseen.ribble.presentation.widget.navigation_view.NavigationItemSelectedListener
import com.luseen.ribble.utils.showToast
import com.luseen.ribble.utils.start
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId as id

class HomeActivity : BaseActivity<HomeContract.View, HomeContract.Presenter>(), HomeContract.View,
        NavigationItemSelectedListener {

    @Inject
    protected lateinit var homePresenter: HomePresenter

    @Inject
    protected lateinit var fragmentManager: FragmentManager

    override fun initPresenter(): HomeContract.Presenter = homePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()

        val navigatorState = presenter.getNavigatorState()
        if (navigatorState != null)
            navigator.restore(navigatorState)
    }

    override fun onDestroy() {
        presenter.saveNavigatorState(navigator.getState())
        super.onDestroy()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navView.navigationItemSelectListener = this
        arcView.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun openShotFragment() {

        goTo(ShotRootFragment::class)
    }

    override fun openLoginActivity() {
        start {
            AuthActivity::class.java
        }
        showToast("Logged out")
        finish()
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerOpen(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: NavigationItem) {
        when (item.id) {
            id.SHOT -> {
                goTo(ShotRootFragment::class)
            }
            id.USER_LIKES -> {
                goTo(UserLikesFragment::class)
            }
            id.TEST_1 -> {
                goTo(RecentShotFragment::class)
            }
            id.TEST_2 -> {
                goTo(TESTFragment::class)
            }
            id.LOG_OUT -> {
                presenter.logOut()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
    }
}
