package com.luseen.ribble.data.repository.factory

import com.luseen.ribble.data.repository.data_store.ApiDataStore
import com.luseen.ribble.data.repository.data_store.ShotDataStore
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chatikyan on 29.07.2017.
 */
@Singleton
class ShotDataStoreFactory @Inject constructor(private val apiDataStore: ApiDataStore){

    fun create(): ShotDataStore {
        //TODO() Add local
        return apiDataStore
    }
}