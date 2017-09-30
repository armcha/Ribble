package io.armcha.ribble.presentation.utils.extensions

import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Chatikyan on 30.09.2017.
 */
object BundleDelegate {

    inline fun <reified V> arguments(): ReadWriteProperty<Fragment, V> {
        return object : ReadWriteProperty<Fragment, V> {
            override fun getValue(thisRef: Fragment, property: KProperty<*>)
                    = thisRef.arguments[property.name] as V

            override fun setValue(thisRef: Fragment, property: KProperty<*>, value: V) {
                val key = property.name
                val arg = thisRef.arguments
                when (value) {
                    is Int -> arg.putInt(key, value)
                    is Long -> arg.putLong(key, value)
                    is String -> arg.putString(key, value)
                    is Boolean -> arg.putBoolean(key, value)
                    is Parcelable -> arg.putParcelable(key, value)
                    is Serializable -> arg.putSerializable(key, value)
                    else -> throw UnsupportedOperationException("${property} type not supported yet!!!")
                }
            }
        }
    }
}