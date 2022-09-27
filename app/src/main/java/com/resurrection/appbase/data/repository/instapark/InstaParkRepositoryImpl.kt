package com.resurrection.appbase.data.repository.instapark

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.components.network.getData
import javax.inject.Inject


class InstaParkRepositoryImpl @Inject constructor(
    private val instaParkApiService: InstaParkApiService,
    private val okHttpClientManager: OkHttpClientManager
):InstaParkRepository {

    override suspend fun getUsers() = getData { instaParkApiService.getUsers() }

    override suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }

    override suspend fun getPosts() = getData { instaParkApiService.getPosts() }

    override suspend fun getPhotos() = okHttpClientManager.newRequest(
        path = "/photos",
        responseType = PhotoModel::class.java,
    )

}