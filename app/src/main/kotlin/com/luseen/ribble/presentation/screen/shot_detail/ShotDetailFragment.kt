package com.luseen.ribble.presentation.screen.shot_detail


import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import javax.inject.Inject


class ShotDetailFragment : BaseFragment<ShotDetailContract.View, ShotDetailContract.Presenter>(), ShotDetailContract.View {

    @Inject
    protected lateinit var shotDetailPresenter: ShotDetailPresenter

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_shot_detail

    override fun initPresenter() = shotDetailPresenter

}
