package io.armcha.ribble.presentation.base_mvp.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import io.armcha.arch.BaseMVPActivity
import io.armcha.ribble.App
import io.armcha.ribble.di.component.ActivityComponent
import io.armcha.ribble.di.module.ActivityModule
import io.armcha.ribble.presentation.navigation.Navigator
import io.armcha.ribble.presentation.utils.S
import io.armcha.ribble.presentation.utils.extensions.emptyString
import io.armcha.ribble.presentation.utils.extensions.unSafeLazy
import io.armcha.ribble.presentation.widget.MaterialDialog
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import javax.inject.Inject

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : BaseMVPActivity<V, P>(), Navigator.FragmentChangeListener {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var inflater: LayoutInflater

    private var dialog: MaterialDialog? = null

    val activityComponent: ActivityComponent by unSafeLazy {
        getAppComponent() + ActivityModule(this)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        navigator.fragmentChangeListener = this
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onDestroy() {
        dialog?.dismiss()
        super.onDestroy()
    }

    @CallSuper
    override fun onBackPressed() {
        if (navigator.hasBackStack())
            navigator.goBack()
        else
            super.onBackPressed()
    }

    protected abstract fun injectDependencies()

    private fun getAppComponent() = App.instance.applicationComponent

    inline protected fun <reified T : Fragment> goTo(keepState: Boolean = true,
                                                     withCustomAnimation: Boolean = false,
                                                     arg: Bundle = Bundle.EMPTY) {
        navigator.goTo<T>(keepState = keepState,
                withCustomAnimation = withCustomAnimation,
                arg = arg)
    }

    fun showDialog(title: String, message: String, buttonText: String = "Close") {
        dialog = MaterialDialog(this).apply {
            message(message)
                    .title(title)
                    .addPositiveButton(buttonText) {
                        hide()
                    }
                    .show()
        }
    }

    fun showErrorDialog(message: String?, buttonText: String = "Close") {
        showDialog(getString(S.error_title), message ?: emptyString, buttonText)
    }
}
