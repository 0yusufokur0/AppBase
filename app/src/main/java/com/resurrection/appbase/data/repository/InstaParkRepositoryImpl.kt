package com.resurrection.appbase.data.repository

import com.resurrection.base.core.BaseRepository
import com.veripark.instapark.data.remote.InstaParkApiService
import com.veripark.instapark.data.repository.InstaParkRepository
import javax.inject.Inject

class InstaParkRepositoryImpl @Inject constructor(private val instaParkApiService: InstaParkApiService)
    : InstaParkRepository, BaseRepository() {

    override suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    override suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    override suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    override suspend fun getPhotos() = getData { instaParkApiService.getPhotos() }

}