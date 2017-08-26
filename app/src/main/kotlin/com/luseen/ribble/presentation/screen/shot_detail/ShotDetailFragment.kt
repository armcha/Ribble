package com.luseen.ribble.presentation.screen.shot_detail


import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.luseen.ribble.R
import com.luseen.ribble.domain.entity.Comment
import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.adapter.CommentRecyclerAdapter
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.utils.getExtra
import kotlinx.android.synthetic.main.fragment_shot_detail.*
import javax.inject.Inject


class ShotDetailFragment : BaseFragment<ShotDetailContract.View, ShotDetailContract.Presenter>(), ShotDetailContract.View {

    companion object {
        const val SHOT_EXTRA_KEY = "shot_extra_key"
    }

    @Inject
    protected lateinit var shotDetailPresenter: ShotDetailPresenter

    private var recyclerAdapter: CommentRecyclerAdapter? = null
    private lateinit var shot: Shot

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_shot_detail

    override fun initPresenter() = shotDetailPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        shot = this getExtra SHOT_EXTRA_KEY
    }

    override fun onDataReceive(commentList: List<Comment>) {
        updateAdapter(commentList)
    }

    override fun getShotId(): String? {
        return shot.id
    }

    private fun updateAdapter(commentList: List<Comment>) {
        recyclerAdapter?.update(commentList) ?: this setUpRecyclerView commentList
    }

    private infix fun setUpRecyclerView(commentList: List<Comment>) {
        recyclerAdapter = CommentRecyclerAdapter(commentList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerAdapter
    }

}
