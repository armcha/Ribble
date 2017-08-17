package com.luseen.ribble.presentation.screen.popular_shot


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.model.Shot
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailActivity
import kotlinx.android.synthetic.main.fragment_shot.*
import javax.inject.Inject


class PopularShotFragment : BaseFragment<PopularShotContract.View, PopularShotContract.Presenter>(),
        PopularShotContract.View, ShotClickListener {

    @Inject
    protected lateinit var popularShotPresenter: PopularShotPresenter

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null
    private var shotList: MutableList<Shot> = mutableListOf<Shot>()

    companion object {
        const val SHOT_EXTRA_ID = "shot_extra_id"

        fun newInstance(): PopularShotFragment {
            val fragment = PopularShotFragment()
            return fragment
        }
    }

    override fun initPresenter(): PopularShotContract.Presenter = popularShotPresenter

    override fun layoutResId(): Int = R.layout.fragment_shot

    override fun injectDependencies() = activityComponent.inject(this)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: MutableList<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: MutableList<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        shotRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun onShotClicked(shot: Shot) {
        val startIntent = ShotDetailActivity.startIntent(activity, shot)
        startActivity(startIntent)
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

    override fun onShotListReceive(shotList: MutableList<Shot>) {
        this.shotList = shotList
        updateAdapter(shotList)
    }
}
