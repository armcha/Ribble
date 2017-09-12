package com.luseen.ribble.presentation.screen.user_following


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import com.luseen.ribble.utils.isPortrait
import com.luseen.ribble.utils.takeColor
import kotlinx.android.synthetic.main.fragment_shot.*
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

    override fun layoutResId() = R.layout.fragment_user_following

    override fun initPresenter() = followingPresenter

    override fun onShotClicked(shot: Shot) {
        val bundle = ShotDetailFragment.getBundle(shot)
        goTo<ShotDetailFragment>(withCustomAnimation = true, arg = bundle)
    }

    override fun showLoading() {
        progressBar.start()
    }

    override fun hideLoading() {
        progressBar.stop()
    }

    override fun showError(message: String?) {

    }

    override fun onShotListReceive(shotList: List<Shot>) {
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        shotRecyclerView.layoutManager = GridLayoutManager(activity, if (isPortrait()) 2 else 3)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun getTitle(): String {
        return NavigationId.FOLLOWING.name
    }
}
