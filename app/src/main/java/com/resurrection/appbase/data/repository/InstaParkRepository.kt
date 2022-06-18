package com.resurrection.appbase.data.repository

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.components.network.getData
import javax.inject.Inject


class InstaParkRepository @Inject constructor(
    private val instaParkApiService: InstaParkApiService,
    private val okHttpClientManager: OkHttpClientManager
) {

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }

    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }

    suspend fun getPosts() = getData { instaParkApiService.getPosts() }

    fun getPhotos() = okHttpClientManager.newRequest(
        path = "/photos",
        responseType = PhotoModel::class.java,
    )


}