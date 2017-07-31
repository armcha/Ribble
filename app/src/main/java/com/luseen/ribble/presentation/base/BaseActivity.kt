package com.luseen.ribble.presentation.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luseen.ribble.App
import com.luseen.ribble.di.component.ActivityComponent
import com.luseen.ribble.di.component.AppComponent
import com.luseen.ribble.di.component.DaggerActivityComponent
import com.luseen.ribble.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent:ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityComponent()
    }

    private fun getAppComponent(): AppComponent {
        return App.instance.appComponent
    }

    private fun initActivityComponent(){
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(getAppComponent())
                .build()
    }

}
