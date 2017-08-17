package com.luseen.ribble.presentation.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.luseen.ribble.R
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.utils.replaceValue
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Chatikyan on 15.08.2017.
 */
@PerActivity
class Navigator @Inject constructor(private val activity: AppCompatActivity) : Navigation {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager
    private val fragmentMap: MutableMap<String, Fragment?> = mutableMapOf() //FIXME ?
    private val registryMap: MutableMap<String, Boolean> = mutableMapOf()

    private val stackSize get() = fragmentMap.size
    private val containerId = R.id.container //TODO change
    private var activeTag: String? = null
    private var firstTag: String? = null

    private fun isRegistered(tag: String): Boolean {
        return registryMap[tag] ?: false
    }

    private fun openFragment(fragment: Fragment?, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        with(transaction) {
            if (!isRegistered(tag)) {
                if (stackSize == 1) {
                    firstTag = tag
                }
                add(containerId, fragment, tag)
            }
            fragmentMap.entries
                    .filter { it.key != tag }
                    .forEach {
                        hide(it.value)
                    }
            show(fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }

        val currentFragment: Fragment? = fragmentMap[tag]
        fragmentMap.replaceValue(tag, currentFragment)

        if (!isRegistered(tag)) {
            registryMap.replaceValue(tag, true)
        }
    }

    override fun goTo(kClass: KClass<out Fragment>) {
        val tag = kClass.java.name
        if (!fragmentMap.containsKey(tag)) {
            val localeFragment = Fragment.instantiate(activity, tag)
            fragmentMap.put(tag, localeFragment)
            registryMap.put(tag, false)
        }

        openFragment(fragmentMap[tag], tag)
        activeTag = tag
    }

    override fun hasBackStack(): Boolean {
        return stackSize > 1 && activeTag != firstTag
    }

    override fun goBack() {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragmentMap[activeTag])
        fragmentMap.remove(activeTag)
        registryMap.remove(activeTag)

        val currentTag = fragmentMap.keys.elementAt(stackSize - 1)
        with(transaction) {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            show(fragmentMap[currentTag])
            commit()
        }
        activeTag = currentTag
    }

    override fun goToFirst() {
        TODO()
    }
}
