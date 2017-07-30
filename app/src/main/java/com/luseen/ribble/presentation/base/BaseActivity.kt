package com.luseen.ribble.presentation.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luseen.ribble.App
import com.luseen.ribble.di.component.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun getAppComponent():ApplicationComponent{
        return App.instance.applicationComponent
    }
}
