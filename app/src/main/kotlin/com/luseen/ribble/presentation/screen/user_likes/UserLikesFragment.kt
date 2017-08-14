package com.luseen.ribble.presentation.screen.user_likes


import android.os.Bundle
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import javax.inject.Inject

class UserLikesFragment : BaseFragment<UserLikeContract.View, UserLikeContract.Presenter>(), UserLikeContract.View {

    @Inject
    protected lateinit var userLikePresenter: UserLikePresenter

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
}
