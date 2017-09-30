package io.armcha.ribble.presentation.screen.shot_root


import android.os.Bundle
import android.view.View
import io.armcha.ribble.R
import io.armcha.ribble.presentation.adapter.ShotPagerAdapter
import io.armcha.ribble.presentation.base_mvp.base.BaseFragment
import io.armcha.ribble.presentation.screen.shot.ShotRootContract
import io.armcha.ribble.presentation.utils.extensions.toPx
import io.armcha.ribble.presentation.widget.CustomTabLayout
import io.armcha.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_shot_root.*
import javax.inject.Inject


class ShotRootFragment : BaseFragment<ShotRootContract.View, ShotRootContract.Presenter>(), ShotRootContract.View {

    @Inject
    protected lateinit var shotRootPresenter: ShotRootPresenter

    @Inject
    protected lateinit var shotPagerAdapter: ShotPagerAdapter

    override fun initPresenter() = shotRootPresenter

    override fun layoutResId() = io.armcha.ribble.R.layout.fragment_shot_root

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
            setSelectedTabIndicatorHeight(5.toPx(context))
        }
    }

    override fun getTitle(): String {
        return NavigationId.SHOT.name
    }
}
