package com.luseen.ribble.presentation.base_mvp.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.luseen.arch.BaseMVPActivity
import com.luseen.ribble.App
import com.luseen.ribble.di.component.ActivityComponent
import com.luseen.ribble.di.component.ApplicationComponent
import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.presentation.navigation.Navigation
import com.luseen.ribble.presentation.navigation.Navigator
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : BaseMVPActivity<V, P>(), Navigation {

    @Inject
    protected lateinit var navigator: Navigator

    val activityComponent: ActivityComponent by lazy {
        getAppComponent().plus(ActivityModule(this))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (navigator.hasBackStack())
            navigator.goBack()
        else
            super.onBackPressed()

    }

    protected abstract fun injectDependencies()

    private fun getAppComponent(): ApplicationComponent {
        return App.instance.applicationComponent
    }

    final override fun goTo(kClass: KClass<out Fragment>) {
        navigator.goTo(kClass)
    }

    final override fun hasBackStack() = navigator.hasBackStack()

    final override fun goBack() = navigator.goBack()

    final override fun goToFirst() {
        //NO-OP
    }
}
