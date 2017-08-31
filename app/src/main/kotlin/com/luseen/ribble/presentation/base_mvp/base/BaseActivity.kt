package com.luseen.ribble.presentation.base_mvp.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.luseen.arch.BaseMVPActivity
import com.luseen.ribble.App
import com.luseen.ribble.di.component.ActivityComponent
import com.luseen.ribble.di.component.ApplicationComponent
import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.presentation.navigation.Navigator
import com.luseen.ribble.utils.log
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : BaseMVPActivity<V, P>(), Navigator.NonRegistryFragmentListener,Navigator.TitleChangeListener {

    @Inject
    lateinit var navigator: Navigator

    val activityComponent: ActivityComponent by lazy {
        getAppComponent().plus(ActivityModule(this))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        navigator.nonRegistryFragmentListener = this
        navigator.titleChangeListener = this
        super.onCreate(savedInstanceState)
    }

    override fun onTitleChanged(newTitle: String) {

    }

    override fun onBackPressed() {
        log {

        }
        if (navigator.activeTag == navigator.rootTag) {
            navigator.nonRegistryFragmentListener.onNonRegistryFragmentClose()
        }
        if (navigator.hasBackStack())
            navigator.goBack()
        else
            super.onBackPressed()
    }

    protected abstract fun injectDependencies()

    private fun getAppComponent(): ApplicationComponent {
        return App.instance.applicationComponent
    }

    fun goTo(kClass: KClass<out Fragment>, arg: Bundle = Bundle.EMPTY) {
        navigator.goTo(kClass, arg)
    }

    fun hasBackStack() = navigator.hasBackStack()

    fun goBack() = navigator.goBack()
}
