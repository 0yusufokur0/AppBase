package com.resurrection.appbase.data.repository

import com.resurrection.base.core.repository.BaseRepository
import com.resurrection.appbase.data.remote.InstaParkApiService
import javax.inject.Inject

class InstaParkRepository @Inject constructor(val instaParkApiService: InstaParkApiService) : BaseRepository() {

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    suspend fun getPhotos() = getData { instaParkApiService.getPhotos() }

}