package com.luseen.ribble.presentation.screen.popular_shot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luseen.ribble.R


/**
 * A simple [Fragment] subclass.
 */
class RecentShotFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recent_shot, container, false)
    }

}
