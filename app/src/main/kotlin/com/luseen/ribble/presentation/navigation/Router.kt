package com.luseen.ribble.presentation.navigation

import android.support.v4.app.Fragment
import kotlin.reflect.KClass

/**
 * Created by Chatikyan on 17.08.2017.
 */
interface Router {

    fun goTo(kClass: KClass<out Fragment>)

    fun hasBackStack(): Boolean

    fun goBack()

    fun goToFirst()
}