package io.armcha.ribble.data.repository

import io.armcha.ribble.data.cache.MemoryCache
import io.armcha.ribble.data.mapper.Mapper
import javax.inject.Inject

/**
 * Created by arman.chatikyan on 10/30/2017.
 */
abstract class Repository {

    @Inject
    protected lateinit var mapper: Mapper
    @Inject
    protected lateinit var memoryCache: MemoryCache
}