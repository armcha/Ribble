package io.armcha.ribble.presentation.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import io.armcha.ribble.R
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.presentation.utils.Experimental
import io.armcha.ribble.presentation.utils.extensions.log
import javax.inject.Inject

/**
 * Created by Chatikyan on 15.08.2017.
 */

@PerActivity
class Navigator @Inject constructor(private val activity: AppCompatActivity,
                                    private val fragmentManager: FragmentManager) {

    interface FragmentChangeListener {
        fun onFragmentChanged(currentTag: String, currentFragment: Fragment) {}
    }

    private var fragmentMap: LinkedHashMap<String, Screen> = linkedMapOf()
    lateinit var fragmentChangeListener: FragmentChangeListener

    private val containerId = R.id.container //TODO add to builder
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
            transaction.setCustomAnimations(io.armcha.ribble.R.anim.slide_in_start, 0)
        } else {
            isCustomAnimationUsed = false
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    private fun invokeFragmentChangeListener(tag: String?) {
        tag?.let {
            val screen = fragmentMap[it]
            screen?.let {
                fragmentChangeListener.onFragmentChanged(tag, it.fragment)
            }
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
//        fragmentManager.fragments.forEach {
//            log {
//                "Fragment manager fragment - ${it::class.java.simpleName}"
//            }
//        }
        fragmentManager.fragments
                .filter { it.tag.contains(activity.applicationContext.packageName) }
                .forEach {
                    fragmentMap.put(it.tag, Screen(it, BackStrategy.KEEP)) //FIXME
                }

        fragmentManager.inTransaction {
            fragmentMap
                    .filter { it.key != activeTag }
                    .forEach {
                        hide(it.value.fragment)
                    }
            show(fragmentMap[activeTag]?.fragment)
        }
        invokeFragmentChangeListener(activeTag)
        runDebugLog()
    }

    inline fun <reified T : Fragment> goTo(keepState: Boolean = true,
                                           withCustomAnimation: Boolean = false,
                                           arg: Bundle = Bundle.EMPTY,
                                           @Experimental
                                           backStrategy: BackStrategy = BackStrategy.KEEP) {
        val tag = T::class.java.name
        goTo(tag, keepState, withCustomAnimation, arg, backStrategy)
    }

    @PublishedApi
    internal fun goTo(tag: String,
                      keepState: Boolean,
                      withCustomAnimation: Boolean,
                      arg: Bundle,
                      backStrategy: BackStrategy) {
        if (activeTag == tag)
            return

        if (!fragmentMap.containsKey(tag) || !keepState) {
            val fragment = Fragment.instantiate(activity, tag)
            if (!arg.isEmpty) {
                fragment.arguments = arg
            }

            if (!keepState) {
                val weakFragment = fragmentManager.findFragmentByTag(tag)
                weakFragment?.let {
                    fragmentManager.inTransaction {
                        remove(weakFragment)
                    }
                }
            }
            fragmentManager.inTransaction {
                addOpenTransition(this, withCustomAnimation)
                add(containerId, fragment, tag)
            }


            fragmentMap.put(tag, Screen(fragment, backStrategy))

            if (activeTag == null) {
                rootTag = tag
            }
        }

        fragmentManager.inTransaction {
            addOpenTransition(this, withCustomAnimation)
            fragmentMap
                    .filter { it.key != tag }
                    .forEach {
                        hide(it.value.fragment)
                    }
            show(fragmentMap[tag]?.fragment)
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
        val screen = fragmentMap[activeTag]
        val backStrategy = screen?.backStrategy
        val isKeep = backStrategy is BackStrategy.KEEP
        fragmentManager.inTransaction {
            if (isCustomAnimationUsed)
                setCustomAnimations(0, io.armcha.ribble.R.anim.slide_out_finish)
            if (isKeep) {
                hide(screen?.fragment)
            } else if (backStrategy is BackStrategy.DESTROY) {
                remove(screen.fragment)
            }
        }

        val keys = fragmentMap.keys
        val currentTag = if (isKeep) {
            val index = keys.indexOf(activeTag)
            keys.elementAt(index - 1)
        } else {
            fragmentMap.remove(activeTag)
            keys.last()
        }

        fragmentManager.inTransaction {
            if (!isCustomAnimationUsed) {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
            show(fragmentMap[currentTag]?.fragment)
        }
        isCustomAnimationUsed = false
        activeTag = currentTag
        invokeFragmentChangeListener(currentTag)
        runDebugLog()
    }

    private inline fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = this.beginTransaction()
        fragmentTransaction.transaction()
        fragmentTransaction.commitNow()
    }

    private fun <K, V> MutableMap<K, V>.replaceValue(key: K, value: V?) {
        this.remove(key)
        value?.let {
            this.put(key, value)
        }
    }
}
