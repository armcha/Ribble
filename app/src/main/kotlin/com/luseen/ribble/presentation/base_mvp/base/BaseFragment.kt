package com.luseen.ribble.presentation.base_mvp.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luseen.arch.BaseMVPFragment
import com.luseen.ribble.di.component.ActivityComponent
import com.luseen.ribble.presentation.navigation.Navigator
import kotlin.reflect.KClass

/**
 * Created by Chatikyan on 01.08.2017.
 */
abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseMVPFragment<V, P>() {

    protected lateinit var activityComponent: ActivityComponent
    protected lateinit var activity: BaseActivity<*, *>
    protected lateinit var navigator: Navigator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.activity = context
            navigator = activity.navigator
            activityComponent = activity.activityComponent
            injectDependencies()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layoutResId(), container, false)
    }

    fun goTo(kClass: KClass<out Fragment>, args: Bundle = Bundle.EMPTY) {
        navigator.goTo(kClass, args)
    }

    protected abstract fun injectDependencies()

    protected abstract fun layoutResId(): Int
}