package com.luseen.ribble.presentation.screen.popular_shot


import android.support.v7.widget.GridLayoutManager
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import com.luseen.ribble.utils.inTransaction
import com.luseen.ribble.utils.isPortrait
import kotlinx.android.synthetic.main.fragment_shot.*
import javax.inject.Inject


class PopularShotFragment : BaseFragment<PopularShotContract.View, PopularShotContract.Presenter>(),
        PopularShotContract.View, ShotClickListener {

    companion object {
        fun newInstance(): PopularShotFragment {
            return PopularShotFragment()
        }
    }

    @Inject
    protected lateinit var popularShotPresenter: PopularShotPresenter

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    override fun initPresenter(): PopularShotContract.Presenter = popularShotPresenter

    override fun layoutResId(): Int = R.layout.fragment_shot

    override fun injectDependencies() = activityComponent.inject(this)

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        shotRecyclerView.layoutManager = GridLayoutManager(activity, if (isPortrait()) 2 else 3)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun onShotClicked(shot: Shot) {
        val fragment = ShotDetailFragment.newInstance(shot)
        fragmentManager.inTransaction {
            setCustomAnimations(R.anim.slide_in_start, R.anim.slide_in_finish, R.anim.slide_out_start, R.anim.slide_out_finish)
            addToBackStack(null)
            add(R.id.container, fragment, "android") //FIXME
        }
        navigator.nonRegistryFragmentListener.onNonRegistryFragmentOpen(NavigationId.SHOT_DETAIL)
    }

    override fun showLoading() {
        //TODO("not implemented")
    }

    override fun hideLoading() {
        //TODO("not implemented")
    }

    override fun showError() {
        ///TODO("not implemented")
    }

    override fun onShotListReceive(shotList: List<Shot>) {
        updateAdapter(shotList)
    }
}
