package com.luseen.ribble.presentation.screen.shot_root


import android.os.Bundle
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.ShotPagerAdapter
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.popular_shot.ShotRootContract
import com.luseen.ribble.presentation.widget.CustomTabLayout
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_shot_root.*
import javax.inject.Inject


class ShotRootFragment constructor(): BaseFragment<ShotRootContract.View, ShotRootContract.Presenter>(), ShotRootContract.View {

    @Inject
    protected lateinit var shotRootPresenter: ShotRootPresenter

    @Inject
    protected lateinit var shotPagerAdapter: ShotPagerAdapter

    override fun initPresenter() = shotRootPresenter

    override fun layoutResId() = R.layout.fragment_shot_root

    override fun injectDependencies() = activityComponent.inject(this)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPagerAdapter()
    }

    private fun setUpPagerAdapter() {
        shotViewPager.adapter = shotPagerAdapter
        with(tabLayout) {
            setupWithViewPager(shotViewPager)
            tabMode = CustomTabLayout.MODE_FIXED
            setSelectedTabIndicatorHeight(20)
        }
    }

    override fun getTitle(): String {
        return NavigationId.SHOT.name
    }
}
