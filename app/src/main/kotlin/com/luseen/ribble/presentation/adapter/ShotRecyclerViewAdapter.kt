package com.luseen.ribble.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.holder.ShotRecyclerViewHolder
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.model.Shot

/**
 * Created by Chatikyan on 04.08.2017.
 */
class ShotRecyclerViewAdapter(var shotList: MutableList<Shot>, val shotClickListener: ShotClickListener) : RecyclerView.Adapter<ShotRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotRecyclerViewHolder {
        val shotView = LayoutInflater.from(parent?.context).inflate(R.layout.shot_item, parent, false)
        val holder = ShotRecyclerViewHolder(shotView)
        shotView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION){
                val shot = shotList[holder.adapterPosition]
                shotClickListener.onShotClicked(shot)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ShotRecyclerViewHolder, position: Int) {
        val shot = shotList[position]
        holder.bind(shot)
    }

    override fun getItemCount(): Int = shotList.size

    fun update(shotList: MutableList<Shot>) {
        this.shotList = shotList
        notifyDataSetChanged()
    }
}