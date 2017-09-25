package com.luseen.ribble.presentation.widget.navigation_view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.AbsSavedState
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.luseen.ribble.R
import com.luseen.ribble.presentation.utils.C
import com.luseen.ribble.presentation.utils.extensions.delay
import com.luseen.ribble.presentation.utils.extensions.nonSafeLazy
import com.luseen.ribble.presentation.utils.extensions.takeColor

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationDrawerView : NavigationView, ItemClickListener {

    private var itemList = mutableListOf(
            NavigationItem(NavigationId.SHOT, R.drawable.ic_menu_camera,
                    isSelected = true, itemIconColor = R.color.error_color_material),
            NavigationItem(NavigationId.USER_LIKES, R.drawable.heart_full,
                    itemIconColor = R.color.colorPrimary),
            NavigationItem(NavigationId.FOLLOWING, R.drawable.following,
                    itemIconColor = R.color.green),
            NavigationItem(NavigationId.ABOUT, R.drawable.about,
                    itemIconColor = R.color.cyan),
            NavigationItem(NavigationId.LOG_OUT, R.drawable.logout,
                    itemIconColor = R.color.blue_gray))

    private var attr: AttributeSet? = null
    private var currentSelectedItem: Int = 0
    private val adapter by nonSafeLazy {
        NavigationViewAdapter(itemList,this)
    }
    private val recyclerView by nonSafeLazy {
        RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    var navigationItemSelectListener: NavigationItemSelectedListener? = null
    val header: View = getHeaderView(0)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.attr = attr
    }

    init {
        setBackgroundColor(context.takeColor(C.bg_color))
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.topMargin = context.resources.getDimension(R.dimen.nav_header_height).toInt()
        recyclerView.layoutParams = layoutParams
        recyclerView.adapter = adapter

        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        addView(recyclerView)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val state = State(superState)
        state.currentPosition = currentSelectedItem
        return state
    }

    override fun onRestoreInstanceState(savedState: Parcelable?) {
        if (savedState !is State) {
            super.onRestoreInstanceState(savedState)
            return
        }
        super.onRestoreInstanceState(savedState.superState)
        this setCurrentSelected savedState.currentPosition
    }

    override fun onNavigationItemClick(item: NavigationItem, position: Int) {
        this setCurrentSelected position
        navigationItemSelectListener?.onNavigationItemSelected(item)
    }

    private infix fun setCurrentSelected(position: Int) {
        itemList[currentSelectedItem].isSelected = false
        currentSelectedItem = position
        itemList[currentSelectedItem].isSelected = true
    }

    fun setChecked(position: Int) {
        setCurrentSelected(position)
        //FIXME
        delay(250) {
            adapter.notifyDataSetChanged()
        }
    }

    private class State : AbsSavedState {
        var currentPosition: Int = 0

        private constructor(parcel: Parcel) : super(parcel) {
            currentPosition = parcel.readInt()
        }

        constructor(parcelable: Parcelable) : super(parcelable)

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            super.writeToParcel(dest, flags)
            dest?.writeInt(currentPosition)
        }

        companion object CREATOR : Parcelable.Creator<State> {
            override fun createFromParcel(parcel: Parcel): State {
                return State(parcel)
            }

            override fun newArray(size: Int): Array<State?> {
                return arrayOfNulls(size)
            }
        }
    }
}