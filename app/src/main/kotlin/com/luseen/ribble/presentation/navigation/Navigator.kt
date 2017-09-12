package com.luseen.ribble.presentation.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.luseen.ribble.R
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.utils.inTransaction
import com.luseen.ribble.utils.log
import com.luseen.ribble.utils.replaceValue
import javax.inject.Inject

/**
 * Created by Chatikyan on 15.08.2017.
 */
@PerActivity
class Navigator @Inject constructor(private val activity: AppCompatActivity,
                                    private val fragmentManager: FragmentManager) {

    interface FragmentChangeListener {
        fun onFragmentChanged(tag: String) {}
    }

    private var fragmentMap: LinkedHashMap<String, Fragment> = linkedMapOf()
    lateinit var fragmentChangeListener: FragmentChangeListener

    private val containerId = R.id.container //TODO add builder
    private var activeTag: String? = null
    private var rootTag: String? = null
    private var isCustomAnimationUsed = false

    private fun runDebugLog() {
        log {
            "Chain [${fragmentMap.size}] - ${fragmentMap.keys.joinToString(" -> ") {
                val split: List<String> = it.split(".")
                split[split.size - 1]
            }}"
        }
    }

    private fun addOpenTransition(transaction: FragmentTransaction, withCustomAnimation: Boolean) {
        if (withCustomAnimation) {
            isCustomAnimationUsed = true
            transaction.setCustomAnimations(R.anim.slide_in_start, 0)
        } else {
            isCustomAnimationUsed = false
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    private fun invokeFragmentChangeListener(tag: String?) {
        val fragment = fragmentMap[tag]
        if (fragment is BaseFragment<*, *>) {
            fragmentChangeListener.onFragmentChanged(fragment.getTitle())
        }
    }

    fun getState(): NavigationState {
        return NavigationState(activeTag, rootTag, isCustomAnimationUsed)
    }

    fun restore(state: NavigationState) {
        activeTag = state.activeTag
        rootTag = state.firstTag
        isCustomAnimationUsed = state.isCustomAnimationUsed
        state.clear()

        fragmentMap.clear()
        fragmentManager.fragments
                .filter { it.tag.contains("ribble") } //FiXME not the best solution
                .forEach {
                    fragmentMap.put(it.tag, it)
                }

        fragmentManager.inTransaction {
            fragmentMap
                    .filter { it.key != activeTag }
                    .forEach {
                        hide(it.value)
                    }
            show(fragmentMap[activeTag])
        }
        invokeFragmentChangeListener(activeTag)
        runDebugLog()
    }

    inline fun <reified T : Fragment> goTo(keepState: Boolean = true, withCustomAnimation: Boolean = false, arg: Bundle = Bundle.EMPTY) {
        val tag = T::class.java.name
        goTo(tag, keepState, withCustomAnimation, arg)
    }

    @PublishedApi
    internal fun goTo(tag: String, keepState: Boolean, withCustomAnimation: Boolean, arg: Bundle) {
        if (activeTag == tag)
            return

        if (!fragmentMap.containsKey(tag)) {
            val fragment = Fragment.instantiate(activity, tag)
            if (!arg.isEmpty) {
                fragment.arguments = arg
            }
            fragmentManager.inTransaction {
                addOpenTransition(this, withCustomAnimation)
                add(containerId, fragment, tag)
            }
            fragmentMap.put(tag, fragment)

            if (activeTag == null) {
                rootTag = tag
            }
        }

        fragmentManager.inTransaction {
            addOpenTransition(this, withCustomAnimation)
            fragmentMap
                    .filter { it.key != tag }
                    .forEach {
                        hide(it.value)
                    }
            show(fragmentMap[tag])
        }
        activeTag = tag
        invokeFragmentChangeListener(tag)

        fragmentMap.replaceValue(tag, fragmentMap[tag])

        runDebugLog()
    }

    fun hasBackStack(): Boolean {
        return fragmentMap.size > 1 && activeTag != rootTag
    }

    fun goBack() {
        fragmentManager.inTransaction {
            if (isCustomAnimationUsed)
                setCustomAnimations(0, R.anim.slide_out_finish)
            remove(fragmentMap[activeTag])
        }
        fragmentMap.remove(activeTag)
        val currentTag = fragmentMap.keys.last()
        fragmentManager.inTransaction {
            if (!isCustomAnimationUsed) {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            }
            show(fragmentMap[currentTag])
        }
        isCustomAnimationUsed = false
        activeTag = currentTag
        invokeFragmentChangeListener(currentTag)
        runDebugLog()
    }

    fun goToFirst() {
        TODO()
    }
}
