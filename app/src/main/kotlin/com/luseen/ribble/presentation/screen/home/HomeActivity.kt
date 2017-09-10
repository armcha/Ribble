package com.luseen.ribble.presentation.screen.home

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.User
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.screen.auth.AuthActivity
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import com.luseen.ribble.presentation.screen.user_following.UserFollowingFragment
import com.luseen.ribble.presentation.screen.user_likes.UserLikesFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationItem
import com.luseen.ribble.presentation.widget.navigation_view.NavigationItemSelectedListener
import com.luseen.ribble.utils.glide.TransformationType
import com.luseen.ribble.utils.glide.load
import com.luseen.ribble.utils.lock
import com.luseen.ribble.utils.showToast
import com.luseen.ribble.utils.start
import com.luseen.ribble.utils.unlock
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import javax.inject.Inject
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId as Id

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

        presenter.getNavigatorState()?.let {
            navigator.restore(it)
        }
    }

    override fun onDestroy() {
        presenter.saveNavigatorState(navigator.getState())
        super.onDestroy()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navView.navigationItemSelectListener = this
        navView.header.userName
    }

    override fun lockDrawer() {
        drawerLayout.lock()
        arcImage.setImageResource(R.drawable.arrow_left)
        arcView.setOnClickListener {
            super.onBackPressed()
        }
    }

    override fun unlockDrawer() {
        drawerLayout?.let {
            drawerLayout.unlock()
            arcImage.setImageResource(R.drawable.equal)
            arcView.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun openShotFragment() {
        goTo(ShotRootFragment::class)
    }

    override fun openLoginActivity() {
        start<AuthActivity>()
        showToast("Logged out")
        finish()
    }

    override fun setToolBarTitle(title: String) {
        toolbarTitle?.setAnimatedText(title)
    }

    override fun onFragmentChanged(tag: String) {
        presenter.handleFragmentChanges(tag)
    }

    override fun updateDrawerInfo(user: User) {
        val header = navView.header
        with(header) {
            userName.text = user.name
            userInfo.text = user.location
            userAvatar.load(user.avatarUrl, TransformationType.CIRCLE)
        }
    }

    override fun checkNavigationItem(position: Int) {
        navView?.let {
            navView.setChecked(position)
        }
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerOpen(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: NavigationItem) {
        when (item.id) {
            Id.SHOT -> {
                goTo(ShotRootFragment::class)
            }
            Id.USER_LIKES -> {
                goTo(UserLikesFragment::class)
            }
            Id.FOLLOWING -> {
                goTo(UserFollowingFragment::class)
            }
            Id.ABOUT -> {

            }
            Id.LOG_OUT -> {
                presenter.logOut()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}
