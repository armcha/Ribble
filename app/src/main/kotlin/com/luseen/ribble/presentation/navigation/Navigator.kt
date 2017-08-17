package com.luseen.ribble.presentation.navigation

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.luseen.ribble.R
import com.luseen.ribble.di.scope.PerActivity
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Chatikyan on 15.08.2017.
 */
@PerActivity
class Navigator @Inject constructor(private val fragmentManager: FragmentManager, private val activity: Activity) {

    private val fragmentMap: MutableMap<String, Fragment?> = mutableMapOf() //FIXME ?
    private val registryMap: MutableMap<String, Boolean> = mutableMapOf()

    private val containerId = R.id.container //TODO change
    private var activeTag: String? = null
    private var firstTag: String? = null

    fun registerScreen(fragment: Fragment, tag: String) {
        fragmentMap.put(tag, fragment)
        registryMap.put(tag, false)
    }

    fun isRegistered(tag: String): Boolean {
        return registryMap[tag] ?: false
    }

    private fun openFragment(fragment: Fragment?, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        with(transaction) {
            if (!isRegistered(tag)) {

                if (fragmentMap.size == 1) {
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
        fragmentMap.remove(tag)
        fragmentMap.put(tag, currentFragment)

        val isRegistered: Boolean = registryMap[tag] ?: false
        if (!isRegistered) {
            //registryMap.replaceValue(tag, true)
            registryMap.remove(tag)
            registryMap.put(tag, true)
        }
    }

    fun goTo(tag: String) {
        openFragment(fragmentMap[tag], tag)
        activeTag = tag
    }

    fun goTo(kClass: KClass<out Fragment>) {

    }

    fun has(): Boolean {
        return fragmentMap.size > 1 && activeTag != firstTag
    }

    fun goBack() {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragmentMap[activeTag])
        fragmentMap.remove(activeTag)
        registryMap.remove(activeTag)

        val currentTag = fragmentMap.keys.elementAt(fragmentMap.size - 1)
        with(transaction) {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            show(fragmentMap[currentTag])
            commit()
        }
        activeTag = currentTag
    }
//
//    fun goToFirst() {
//        currentScreen = 0
//        if (screenList.isNotEmpty())
//            openFragment(screenList[0].fragment(), FragmentOpenType.ADD, screenList[0].fragmentTag())
//        else
//
//            log {
//                "Screen list is empty"
//            }
//    }
//
//    fun goToLast() {
//        currentScreen = screenList.size
//        openFragment(screenList[screenList.size].fragment(), FragmentOpenType.REPLACE, screenList[screenList.size].fragmentTag())
//    }
//
//    fun stackCount(): Int {
//        return fragmentStack.size
//    }
}
