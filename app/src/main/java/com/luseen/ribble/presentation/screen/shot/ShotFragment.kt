package com.luseen.ribble.presentation.screen.shot


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luseen.logger.Logger
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.model.Shot
import kotlinx.android.synthetic.main.fragment_shot.*
import javax.inject.Inject


class ShotFragment : BaseFragment<ShotContract.View, ShotContract.Presenter>(), ShotContract.View {

    @Inject
    protected lateinit var shotPresenter: ShotPresenter

    override fun initPresenter(): ShotContract.Presenter = shotPresenter

    private var shotList: MutableList<Shot> = arrayListOf()

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    companion object {
        fun newInstance(): ShotFragment {
            return ShotFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: MutableList<Shot>){
        recyclerAdapter?.update(shotList) ?: setUpRecyclerView(shotList)
    }

    private fun setUpRecyclerView(shotList: MutableList<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList)
        shotRecyclerView.layoutManager = LinearLayoutManager(activity)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
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
        Logger.log("onShotListReceive ${shotList.size}")
        this.shotList = shotList
        updateAdapter(shotList)
    }
}
