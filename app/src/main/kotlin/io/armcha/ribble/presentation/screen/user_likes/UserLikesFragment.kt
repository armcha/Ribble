package io.armcha.ribble.presentation.screen.user_likes


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.armcha.ribble.domain.entity.Like
import io.armcha.ribble.presentation.adapter.RibbleAdapter
import io.armcha.ribble.presentation.base_mvp.base.BaseFragment
import io.armcha.ribble.presentation.screen.shot_detail.ShotDetailFragment
import io.armcha.ribble.presentation.utils.C
import io.armcha.ribble.presentation.utils.L
import io.armcha.ribble.presentation.utils.S
import io.armcha.ribble.presentation.utils.extensions.takeColor
import io.armcha.ribble.presentation.utils.glide.load
import io.armcha.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_user_likes.*
import kotlinx.android.synthetic.main.liked_shot_item.view.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject

class UserLikesFragment : BaseFragment<UserLikeContract.View, UserLikeContract.Presenter>(),
        UserLikeContract.View {

    @Inject
    protected lateinit var userLikePresenter: UserLikePresenter

    private var recyclerAdapter: RibbleAdapter<Like>? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.backgroundCircleColor = takeColor(C.colorPrimary)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override val layoutResId = L.fragment_user_likes

    override fun initPresenter() = userLikePresenter

    override fun onDataReceive(likeList: List<Like>) {
        updateAdapter(likeList)
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

    private fun updateAdapter(likeList: List<Like>) {
        recyclerAdapter?.update(likeList) ?: this setUpRecyclerView likeList
    }

    private infix fun setUpRecyclerView(likeList: List<Like>) {
        likesRecyclerView.apply {
            recyclerAdapter = RibbleAdapter(likeList, L.liked_shot_item, {
                it.shot?.apply {
                    shotTitle.text = title
                    shotAuthor.text = user.name
                    likeCount.text = likesCount.toString()
                    shotImage.load(image.normal)
                }
            }, {
                goTo<ShotDetailFragment>(keepState = false, withCustomAnimation = true,
                        arg = ShotDetailFragment.getBundle(shot))
            })
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    override fun getTitle() = NavigationId.USER_LIKES.name
}
