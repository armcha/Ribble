package com.luseen.ribble.presentation.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.luseen.ribble.presentation.screen.popular_shot.PopularShotFragment
import com.luseen.ribble.presentation.screen.recent_shot.RecentShotFragment
import com.luseen.ribble.presentation.screen.user_likes.UserLikesFragment

/**
 * Created by Chatikyan on 15.08.2017.
 */
abstract class TestActivity : AppCompatActivity() {

    private val navigator = Navigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        navigator.registerScreen(PopularShotFragment.newInstance(),"POP")
        navigator.registerScreen(RecentShotFragment(),"REC")
        navigator.registerScreen(UserLikesFragment.newInstance(),"USE")
    }

    fun open(){
        navigator.goTo("")
    }
}