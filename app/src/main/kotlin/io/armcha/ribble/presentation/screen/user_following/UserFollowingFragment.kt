package io.armcha.ribble.presentation.screen.user_following


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import io.armcha.ribble.R
import io.armcha.ribble.domain.entity.Shot
import io.armcha.ribble.presentation.adapter.ShotRecyclerViewAdapter
import io.armcha.ribble.presentation.adapter.listener.ShotClickListener
import io.armcha.ribble.presentation.base_mvp.base.BaseFragment
import io.armcha.ribble.presentation.screen.shot_detail.ShotDetailFragment
import io.armcha.ribble.presentation.utils.S
import io.armcha.ribble.presentation.utils.extensions.isPortrait
import io.armcha.ribble.presentation.utils.extensions.takeColor
import io.armcha.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_user_following.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class UserFollowingFragment : BaseFragment<UserFollowingContract.View, UserFollowingContract.Presenter>(),
        UserFollowingContract.View, ShotClickListener {

    @Inject
    protected lateinit var followingPresenter: UserFollowPresenter

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.backgroundCircleColor = takeColor(R.color.colorPrimary)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override val layoutResId = R.layout.fragment_user_following

    override fun initPresenter() = followingPresenter

    override fun onShotClicked(shot: Shot) {
        val bundle = ShotDetailFragment.getBundle(shot)
        goTo<ShotDetailFragment>(keepState = false, withCustomAnimation = true, arg = bundle)
    }

    override fun showLoading() {
        progressBar.start()
    }

    override fun hideLoading() {
        progressBar.stop()
    }

    override fun showError(message: String?) {
        showErrorDialog(message)
    }

    override fun showNoShots() {
        noShotsText.setAnimatedText(getString(S.no_shots))
    }

    override fun onShotListReceive(shotList: List<Shot>) {
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: setUpRecyclerView(shotList)
    }

    private fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        with(shotRecyclerView) {
            layoutManager = GridLayoutManager(activity, if (isPortrait()) 2 else 3)
            setHasFixedSize(true)
            adapter = recyclerAdapter
            scheduleLayoutAnimation()
        }
    }

    override fun getTitle() = NavigationId.FOLLOWING.name
}
