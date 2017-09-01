package com.luseen.ribble.presentation.screen.user_likes


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.UserLikesRecyclerAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.shot_detail.SHOT_EXTRA_KEY
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_user_likes.*
import javax.inject.Inject

class UserLikesFragment : BaseFragment<UserLikeContract.View, UserLikeContract.Presenter>(),
        UserLikeContract.View, ShotClickListener {

    @Inject
    protected lateinit var userLikePresenter: UserLikePresenter

    private var recyclerAdapter: UserLikesRecyclerAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_user_likes

    override fun initPresenter() = userLikePresenter

    override fun onDataReceive(likeList: List<Like>) {
        updateAdapter(likeList)
    }

    override fun onShotClicked(shot: Shot) {
//        val fragment = ShotDetailFragment.newInstance(shot)
//        fragmentManager.inTransaction {
//            setCustomAnimations(R.anim.slide_in_start, R.anim.slide_in_finish, R.anim.slide_out_start, R.anim.slide_out_finish)
//            addToBackStack(null)
//            add(R.id.container,fragment, "android") //FIXME
//        }
//        navigator.nonRegistryFragmentListener.onNonRegistryFragmentOpen(NavigationId.SHOT_DETAIL)
////        val bundle = Bundle()
////        bundle.putParcelable(SHOT_EXTRA_KEY, shot)
////        goTo(ShotDetailFragment::class, bundle)
        val b = Bundle()
        b.putParcelable(SHOT_EXTRA_KEY,shot)
        goTo(ShotDetailFragment::class, withCustomAnimation = true, args = b)
    }

    private fun updateAdapter(likeList: List<Like>) {
        recyclerAdapter?.update(likeList) ?: this setUpRecyclerView likeList
    }

    private infix fun setUpRecyclerView(likeList: List<Like>) {
        val recyclerAdapter = UserLikesRecyclerAdapter(likeList, this)
        likesRecyclerView.layoutManager = LinearLayoutManager(activity)
        likesRecyclerView.adapter = recyclerAdapter
    }

    override fun getTitle(): String {
        return NavigationId.USER_LIKES.name
    }
}
