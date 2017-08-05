package com.luseen.ribble.domain.interactor

import com.luseen.ribble.data.ShotDataRepository
import com.luseen.ribble.di.scope.PerActivity
import javax.inject.Inject

/**
 * Created by Chatikyan on 03.08.2017.
 */
@PerActivity
class ShotDetailInteractor @Inject constructor(private val shotRepository: ShotDataRepository){

    fun getShotDetail(shotId:Int){
        shotRepository
    }

}