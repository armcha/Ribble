package com.luseen.ribble.presentation.screen.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.navigation.Navigator
import com.luseen.ribble.presentation.screen.TESTFragment
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import com.luseen.ribble.presentation.screen.recent_shot.RecentShotFragment
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import com.luseen.ribble.presentation.screen.user_likes.UserLikesFragment
import com.luseen.ribble.utils.log
import com.luseen.ribble.utils.showToast
import com.luseen.ribble.utils.start
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeContract.View, HomeContract.Presenter>(), HomeContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    @Inject
    protected lateinit var homePresenter: HomePresenter

    @Inject
    protected lateinit var fragmentManager: FragmentManager

    @Inject
    protected lateinit var navigator: Navigator

    override fun initPresenter(): HomeContract.Presenter = homePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navView.setNavigationItemSelectedListener(this)
        arcView.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun openShotFragment() {
        navigator.registerScreen(ShotRootFragment.newInstance(), "SHOT")
        navigator.goTo("SHOT")
    }

    override fun openLoginActivity() {
        start {
            AuthActivity::class.java
        }
        showToast("Logged out")
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            log {
                fragmentManager.backStackEntryCount
            }
            if(navigator.has()){
                navigator.goBack()
            }else
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                navigator.goTo("SHOT")
            }
            R.id.nav_gallery -> {
                if (!navigator.isRegistered("USER")) {
                    navigator.registerScreen(UserLikesFragment.newInstance(), "USER")
                }
                navigator.goTo("USER")
            }
            R.id.nav_slideshow -> {
                if (!navigator.isRegistered("REC")) {
                    navigator.registerScreen(RecentShotFragment(), "REC")
                }
                navigator.goTo("REC")
            }
            R.id.nav_manage -> {
                if (!navigator.isRegistered("TEST")) {
                    navigator.registerScreen(TESTFragment(), "TEST")
                }
                navigator.goTo("TEST")
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {
                presenter.logOut()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
