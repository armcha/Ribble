package com.luseen.ribble.presentation.screen.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.screen.shot_root.ShotRootFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeContract.View, HomeContract.Presenter>(), HomeContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    @Inject
    protected lateinit var homePresenter: HomePresenter

    @Inject
    protected lateinit var fragmentManager: FragmentManager

    override fun initPresenter(): HomeContract.Presenter = homePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
//        val toggle = ActionBarDrawerToggle(
//                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        //drawerLayout.addDrawerListener(toggle)
        //toggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun openShotFragment() {
        fragmentManager
                .beginTransaction()
                .add(R.id.container, ShotRootFragment.newInstance())
                .commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
