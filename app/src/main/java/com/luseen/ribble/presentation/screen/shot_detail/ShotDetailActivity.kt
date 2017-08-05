package com.luseen.ribble.presentation.screen.shot_detail

import android.os.Bundle
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import javax.inject.Inject

class ShotDetailActivity : BaseActivity<ShotDetailContract.View, ShotDetailContract.Presenter>(), ShotDetailContract.View {

    @Inject
    protected lateinit var shotDetailPresenter: ShotDetailPresenter

    override fun initPresenter(): ShotDetailContract.Presenter = shotDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shot_detail)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }
}
