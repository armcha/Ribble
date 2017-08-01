package com.luseen.ribble.presentation.shot


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luseen.logger.Logger
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base.BaseFragment
import javax.inject.Inject


class ShotFragment : BaseFragment<ShotContract.View, ShotContract.Presenter>(), ShotContract.View {

    @Inject
    protected lateinit var inflater: LayoutInflater

    @Inject
    protected lateinit var shotPresenter:ShotPresenter

    override fun initPresenter(): ShotContract.Presenter = shotPresenter

    companion object {
        fun newInstance():ShotFragment{
            return ShotFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.log(inflater)
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }
}
