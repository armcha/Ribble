package com.luseen.ribble.presentation.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.luseen.ribble.R
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.utils.log
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Chatikyan on 15.08.2017.
 */
@PerActivity
class Navigator @Inject constructor(private val activity: AppCompatActivity) : Router {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager
    private var fragmentMap: MutableMap<String, Fragment?> = mutableMapOf() //FIXME ?

    private val containerId = R.id.container //TODO change
    private var activeTag: String? = null
    private var rootTag: String? = null


    fun getState(): NavigationState {
        return NavigationState(activeTag, rootTag)
    }

    fun restore(state: NavigationState) {
        activeTag = state.activeTag
        rootTag = state.firstTag

        val beginTransaction = fragmentManager.beginTransaction()
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

        fragmentMap.clear()
        fragmentManager.fragments
                .filter { !it.tag.contains("android") } //FiXME not the best solution
                .forEach {
                    fragmentMap.put(it.tag, it)
                }

        fragmentMap
                .filter { it.key != activeTag }
                .forEach {
                    log {
                        "HIDING $it"
                    }
                    beginTransaction.hide(it.value)
                }
        beginTransaction.show(fragmentMap[activeTag])

        beginTransaction.commit()
    }

    override fun goTo(kClass: KClass<out Fragment>) {

        log {
            "MANAGER FRAGMENTS ${fragmentManager.fragments}"
        }

        log {
            "MANAGER FRAGMENTS SIZE ${fragmentManager.fragments.size}"
        }
        val tag = kClass.java.name
        if (activeTag == tag) {
            log {
                "Fragment already opened"
            }
            return
        }
        if (!fragmentMap.containsKey(tag)) {
            val beginTransaction = fragmentManager.beginTransaction()
            beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

            val fragment = Fragment.instantiate(activity, tag)
            beginTransaction.add(containerId, fragment, tag)
            if (activeTag == null) {
                rootTag = tag
            }

            beginTransaction.commit()
            fragmentMap.put(tag, fragment)
            log {
                "ADDING to map ${fragmentMap[tag]}"
            }
        }
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentMap
                .filter { it.key != tag }
                .forEach {
                    log {
                        "HIDING ${it}"
                    }
                    transaction.hide(it.value)
                }
        log {
            "SHOWING from map ${fragmentMap[tag]}"
        }
        transaction.show(fragmentMap[tag])
        transaction.commit()

        activeTag = tag
    }

    override fun hasBackStack(): Boolean {
        return fragmentMap.size > 1 && activeTag != rootTag
    }

    override fun goBack() {
        log {
            "goBack active tag $activeTag"
        }
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragmentMap[activeTag])
        fragmentMap.remove(activeTag)

        val currentTag = fragmentMap.keys.elementAt(fragmentMap.size - 1)
        log {
            "goBack currentTag tag $currentTag"
        }
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
