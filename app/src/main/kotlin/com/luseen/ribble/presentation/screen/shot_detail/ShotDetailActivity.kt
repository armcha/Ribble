package com.luseen.ribble.presentation.screen.shot_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.base_mvp.base.BaseActivity
import com.luseen.ribble.presentation.screen.popular_shot.PopularShotFragment
import javax.inject.Inject

class ShotDetailActivity : BaseActivity<ShotDetailContract.View, ShotDetailContract.Presenter>(), ShotDetailContract.View {

    @Inject
    protected lateinit var shotDetailPresenter: ShotDetailPresenter

    private lateinit var shot: Shot

    companion object {
        fun startIntent(context: Context, shot: Shot): Intent {
            val intent = Intent(context, ShotDetailActivity::class.java)
            intent.putExtra(PopularShotFragment.SHOT_EXTRA_ID, shot)
            return intent
        }
    }

    override fun initPresenter(): ShotDetailContract.Presenter = shotDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shot_detail)
        shot = intent.getParcelableExtra(PopularShotFragment.SHOT_EXTRA_ID)
        presenter.fetchLikes(shot.id)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }


}
