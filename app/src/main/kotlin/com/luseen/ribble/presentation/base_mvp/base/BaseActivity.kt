package com.luseen.ribble.presentation.base_mvp.base

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import com.luseen.arch.BaseMVPActivity
import com.luseen.ribble.App
import com.luseen.ribble.R
import com.luseen.ribble.di.component.ActivityComponent
import com.luseen.ribble.di.component.ApplicationComponent
import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.presentation.navigation.Navigator
import javax.inject.Inject

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : BaseMVPActivity<V, P>(), Navigator.FragmentChangeListener {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var inflater: LayoutInflater

    val activityComponent: ActivityComponent by lazy(LazyThreadSafetyMode.NONE) {
        getAppComponent().plus(ActivityModule(this))
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        navigator.fragmentChangeListener = this
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

    inline fun <reified T : Fragment> goTo(withCustomAnimation: Boolean = false, arg: Bundle = Bundle.EMPTY) {
        navigator.goTo<T>(withCustomAnimation = withCustomAnimation, arg = arg)
    }

    fun hasBackStack() = navigator.hasBackStack()

    fun goBack() = navigator.goBack()

    fun showDialog(message: String) {
        val dialog1 = Dialog(this, R.style.MaterialDialogSheet)
        dialog1.setContentView(R.layout.dialog_item)
        //dialog1.show()
//        val dialog = Dialog.Builder(this)
//
//        dialog.setView(inflater.inflate(R.layout.dialog_item,null))
//
//        dialog.setTitle("Title")
//        dialog.create().show()
    }
}
