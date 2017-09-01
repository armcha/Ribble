package com.luseen.ribble.presentation.screen.user_following


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.shot_detail.SHOT_EXTRA_KEY
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_shot.*
import javax.inject.Inject


class UserFollowingFragment : BaseFragment<UserFollowingContract.View, UserFollowingContract.Presenter>(),
        UserFollowingContract.View, ShotClickListener {

    @Inject
    protected lateinit var followingPresenter: UserFollowPresenter

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_user_following

    override fun initPresenter() = followingPresenter

    override fun onShotClicked(shot: Shot) {
//        val fragment = ShotDetailFragment.newInstance(shot)
//        fragmentManager.inTransaction {
//            setCustomAnimations(R.anim.slide_in_start, R.anim.slide_in_finish, R.anim.slide_out_start, R.anim.slide_out_finish)
//            addToBackStack(null)
//            add(R.id.container,fragment, "android") //FIXME
//        }
//        navigator.nonRegistryFragmentListener.onNonRegistryFragmentOpen(NavigationId.SHOT_DETAIL)
        val b = Bundle()
        b.putParcelable(SHOT_EXTRA_KEY,shot)
        goTo(ShotDetailFragment::class, withCustomAnimation = true, args = b)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError() {

    }

    override fun onShotListReceive(shotList: List<Shot>) {
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        shotRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun getTitle(): String {
        return NavigationId.FOLLOWING.name
    }
}
