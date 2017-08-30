package com.luseen.ribble.presentation.widget.navigation_view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.AbsSavedState
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.luseen.ribble.R

/**
 * Created by Chatikyan on 20.08.2017.
 */
class NavigationDrawerView : NavigationView, ItemClickListener {

    private var itemList = mutableListOf(
            NavigationItem(NavigationId.SHOT, R.drawable.ic_menu_camera,
                    isSelected = true, itemIconColor = R.color.error_color_material),
            NavigationItem(NavigationId.USER_LIKES, R.drawable.heart_full,
                    itemIconColor = R.color.colorPrimary),
            NavigationItem(NavigationId.FOLLOWING, R.drawable.ic_menu_manage,
                    itemIconColor = R.color.green),
            NavigationItem(NavigationId.ABOUT, R.drawable.ic_menu_send),
            NavigationItem(NavigationId.LOG_OUT, R.drawable.ic_menu_share))

    private var attr: AttributeSet? = null
    private var currentSelectedItem: Int = 0
    private val adapter by lazy {
        NavigationViewAdapter(itemList)
    }
    private val recyclerView by lazy(LazyThreadSafetyMode.NONE) {
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
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.topMargin = context.resources.getDimension(R.dimen.nav_header_height).toInt()
        recyclerView.layoutParams = layoutParams
        adapter.itemClickListener = this
        recyclerView.adapter = adapter

        addView(recyclerView)
    }

    @SuppressLint("RestrictedApi") //FIXME
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val drawer = parent as DrawerLayout?
        drawer?.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerClosed(drawerView: View?) {
                adapter.notifyDataSetChanged()
            }
        })
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