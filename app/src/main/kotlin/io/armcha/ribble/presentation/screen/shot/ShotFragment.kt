package io.armcha.ribble.presentation.screen.shot

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
import io.armcha.ribble.presentation.utils.extensions.extraWithKey
import io.armcha.ribble.presentation.utils.extensions.isPortrait
import io.armcha.ribble.presentation.utils.extensions.takeColor
import kotlinx.android.synthetic.main.fragment_shot.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject

class ShotFragment : BaseFragment<ShotContract.View, ShotContract.Presenter>(),
        ShotContract.View, ShotClickListener {

    companion object {
        private const val SHOT_TYPE = "shot_type"

        fun newInstance(shotFragmentType: String) = ShotFragment().apply {
            arguments = Bundle().apply { putString(SHOT_TYPE, shotFragmentType) }
        }
    }

    @Inject
    protected lateinit var shotPresenter: ShotPresenter
    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    override fun initPresenter() = shotPresenter

    override val layoutResId = R.layout.fragment_shot

    override fun injectDependencies() = activityComponent.inject(this)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.backgroundCircleColor = takeColor(R.color.colorPrimary)
    }

    override fun getShotType() = extraWithKey<String>(SHOT_TYPE)

    private fun updateAdapter(shotList: List<Shot>) {
        recyclerAdapter?.update(shotList) ?: this setUpRecyclerView shotList
    }

    private infix fun setUpRecyclerView(shotList: List<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)

        with(shotRecyclerView) {
            layoutManager = GridLayoutManager(activity, if (isPortrait()) 2 else 3)
            setHasFixedSize(true)
            adapter = recyclerAdapter
            scheduleLayoutAnimation()
        }
    }

    override fun onShotClicked(shot: Shot) {
        val bundle = ShotDetailFragment.getBundle(shot)
        goTo<ShotDetailFragment>(
                keepState = false,
                withCustomAnimation = true,
                arg = bundle)
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
}
