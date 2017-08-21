package com.luseen.ribble.presentation.screen.user_likes


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Like
import com.luseen.ribble.presentation.adapter.UserLikesRecyclerAdapter
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_likes.*
import javax.inject.Inject

class UserLikesFragment : BaseFragment<UserLikeContract.View, UserLikeContract.Presenter>(),
        UserLikeContract.View {

    @Inject
    protected lateinit var userLikePresenter: UserLikePresenter

    // private var recyclerAdapter: UserLikesRecyclerAdapter? = null

    companion object {
        fun newInstance(): UserLikesFragment {
            return UserLikesFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_user_likes

    override fun initPresenter() = userLikePresenter

    override fun onDataReceive(likeList: List<Like>) {
        // updateAdapter(likeList)
        setUpRecyclerView(likeList)
    }

    private fun updateAdapter(likeList: List<Like>) {
        // recyclerAdapter?.update(likeList) ?: this setUpRecyclerView likeList
    }

    private infix fun setUpRecyclerView(likeList: List<Like>) {
        val recyclerAdapter = UserLikesRecyclerAdapter(likeList)
        likesRecyclerView.layoutManager = LinearLayoutManager(activity)
        likesRecyclerView.adapter = recyclerAdapter
    }
}
