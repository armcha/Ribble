package com.luseen.ribble.presentation.screen.shot

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailFragment
import com.luseen.ribble.utils.getExtraWithKey
import com.luseen.ribble.utils.isPortrait
import com.luseen.ribble.utils.takeColor
import kotlinx.android.synthetic.main.fragment_shot.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject

class ShotFragment : BaseFragment<ShotContract.View, ShotContract.Presenter>(),
        ShotContract.View, ShotClickListener {

    companion object {
        private const val SHOT_TYPE = "shot_type"

        fun newInstance(shotFragmentType: String): ShotFragment {
            val bundle = Bundle()
            bundle.putString(SHOT_TYPE, shotFragmentType)
            val shotFragment = ShotFragment()
            shotFragment.arguments = bundle
            return shotFragment
        }
    }

    @Inject
    protected lateinit var shotPresenter: ShotPresenter
    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    override fun initPresenter() = shotPresenter

    override fun layoutResId() = R.layout.fragment_shot

    override fun injectDependencies() = activityComponent.inject(this)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.backgroundCircleColor = takeColor(R.color.colorPrimary)
    }

    override fun getShotType() = getExtraWithKey<String>(SHOT_TYPE)

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)
        shotRecyclerView.layoutManager = GridLayoutManager(activity, if (isPortrait()) 2 else 3)
        shotRecyclerView.adapter = recyclerAdapter
    }

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

    override fun onShotListReceive(shotList: List<Shot>) {
        updateAdapter(shotList)
    }
}
